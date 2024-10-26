package com.juandlr.customer.service;

import com.juandlr.customer.dto.AddressDto;
import com.juandlr.customer.dto.CustomerDto;
import com.juandlr.customer.entity.Address;
import com.juandlr.customer.entity.Customer;
import com.juandlr.customer.exception.CustomerAlreadyExistsException;
import com.juandlr.customer.exception.CustomerNotFoundException;

import com.juandlr.customer.repository.CustomerRepository;
import com.juandlr.customer.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestPropertySource("/application-test.yml")
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl underTest;

    private CustomerDto testCustomerDto;

    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        AddressDto testAddressDto = new AddressDto("123 any street", "Los Angeles", "California", "90001");
        Address testAddress = new Address(1L, "123 any street", "Los Angeles", "California", "90001");
        testCustomerDto = new CustomerDto("Anthony", "Stark", "stark@example.com", "5512345678", testAddressDto);
        testCustomer = new Customer(1L,"Anthony", "Stark", "stark@example.com", "5512345678", testAddress);
    }

    @Test
    void testCreateCustomer_savesNewCustomer_whenMobileNumberDoesNotExist() {
        when(customerRepository.findByMobileNumber(testCustomerDto.getMobileNumber())).thenReturn(Optional.empty());
        underTest.createCustomer(testCustomerDto);
        verify(customerRepository).findByMobileNumber(testCustomerDto.getMobileNumber());
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(customerArgumentCaptor.capture());
        Customer customerToSave = customerArgumentCaptor.getValue();
        assertThat(customerToSave).isNotNull();
        assertThat(customerToSave.getName()).isEqualTo("Anthony");
        assertThat(customerToSave.getLastName()).isEqualTo("Stark");
        assertThat(customerToSave.getEmail()).isEqualTo("stark@example.com");
        assertThat(customerToSave.getMobileNumber()).isEqualTo("5512345678");
        assertThat(customerToSave.getAddress()).isNotNull();
        assertThat(customerToSave.getAddress().getCity()).isEqualTo("Los Angeles");
    }

    @Test
    void testCreateCustomer_throwCustomerAlreadyExistsException_whenMobileNumberExist() {
        when(customerRepository.findByMobileNumber(testCustomerDto.getMobileNumber())).thenReturn(Optional.of(testCustomer));
        assertThatThrownBy(() -> underTest.createCustomer(testCustomerDto)).isInstanceOf(CustomerAlreadyExistsException.class);
        verify(customerRepository, never()).save(any());
    }

    @Test
    void testFetchCustomer_returnCustomerInformation_whenMobileNumberExist() {
        String testMobileNumber = "5512345678";
        when(customerRepository.findByMobileNumber(testMobileNumber)).thenReturn(Optional.of(testCustomer));
        CustomerDto result = underTest.fetchCustomer(testMobileNumber);
        verify(customerRepository).findByMobileNumber(testMobileNumber);
        assertThat(result).isNotNull();
        assertThat(result.getMobileNumber()).isEqualTo(testMobileNumber);
        assertThat(result.getName()).isEqualTo(testCustomer.getName());
        assertThat(result.getLastName()).isEqualTo(testCustomer.getLastName());
        assertThat(result.getAddressDto().getCity()).isEqualTo(testCustomer.getAddress().getCity());
        assertThat(result.getAddressDto().getState()).isEqualTo("California");
    }

    @Test
    void testFetchCustomer_throwCustomerNotFoundException_whenMobileNumberDoesNotExist() {
        String fakeTestMobileNumber = "5512345666";
        when(customerRepository.findByMobileNumber(fakeTestMobileNumber)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> underTest.fetchCustomer(fakeTestMobileNumber)).isInstanceOf(CustomerNotFoundException.class);
    }

    @Test
    void testUpdateCustomer_updateCustomerInformation_whenMobileNumberExist() {
        when(customerRepository.findByMobileNumber(testCustomerDto.getMobileNumber())).thenReturn(Optional.of(testCustomer));
        underTest.updateCustomer(testCustomerDto);
        verify(customerRepository).findByMobileNumber(testCustomerDto.getMobileNumber());
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(customerArgumentCaptor.capture());
        Customer customerToSaveUpdated = customerArgumentCaptor.getValue();
        assertThat(customerToSaveUpdated).isNotNull();
        assertThat(customerToSaveUpdated.getMobileNumber()).isEqualTo(testCustomerDto.getMobileNumber());
        assertThat(customerToSaveUpdated.getName()).isEqualTo(testCustomerDto.getName());
        assertThat(customerToSaveUpdated.getLastName()).isEqualTo(testCustomerDto.getLastName());
        assertThat(customerToSaveUpdated.getEmail()).isEqualTo(testCustomerDto.getEmail());
        assertThat(customerToSaveUpdated.getAddress().getCity()).isEqualTo(testCustomerDto.getAddressDto().getCity());
    }

    @Test
    void testUpdateCustomer_throwCustomerNotFoundException_whenMobileNumberDoesNotExist() {
        when(customerRepository.findByMobileNumber(testCustomerDto.getMobileNumber())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> underTest.updateCustomer(testCustomerDto)).isInstanceOf(CustomerNotFoundException.class);
    }

    @Test
    void testDeleteCustomer_deleteUserInformation_whenMobileNumberExist() {
        String testMobileNumber = "5512345678";
        when(customerRepository.findByMobileNumber(testMobileNumber)).thenReturn(Optional.of(testCustomer));
        underTest.deleteCustomer(testMobileNumber);
        verify(customerRepository).findByMobileNumber(testMobileNumber);
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).delete(customerArgumentCaptor.capture());
        Customer customerToDelete = customerArgumentCaptor.getValue();
        assertThat(customerToDelete).isNotNull();
        assertThat(customerToDelete.getMobileNumber()).isEqualTo(testMobileNumber);
    }

    @Test
    void testDeleteCustomer_throwCustomerNotFoundException_whenMobileNumberDoesNotExist() {
        String fakeTestMobileNumber = "5512345666";
        when(customerRepository.findByMobileNumber(fakeTestMobileNumber)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> underTest.deleteCustomer(fakeTestMobileNumber)).isInstanceOf(CustomerNotFoundException.class);
    }
}
