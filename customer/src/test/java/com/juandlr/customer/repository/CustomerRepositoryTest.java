package com.juandlr.customer.repository;

import com.juandlr.customer.entity.Address;
import com.juandlr.customer.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.yml")
@Transactional
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository underTest;

    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        Address testAddress = new Address(1L, "123 any street", "Los Angeles", "California", "90001");
        testCustomer = new Customer(1L, "Anthony", "Stark", "stark@example.com", "5512345678", testAddress);
    }

    @Test
    void shouldReturnOptionalWithCustomer_whenFindByMobileNumber_withExistingNumber(){
        underTest.save(testCustomer);
        Optional<Customer> customerOptional = underTest.findByMobileNumber("5512345678");
        assertThat(customerOptional).isPresent();
        assertThat(customerOptional.get().getName()).isEqualTo("Anthony");
        assertThat(customerOptional.get().getLastName()).isEqualTo("Stark");
        assertThat(customerOptional.get().getEmail()).isEqualTo("stark@example.com");
        assertThat(customerOptional.get().getMobileNumber()).isEqualTo("5512345678");
        assertThat(customerOptional.get().getAddress()).isNotNull();
        assertThat(customerOptional.get().getAddress().getCity()).isEqualTo("Los Angeles");
    }

}
