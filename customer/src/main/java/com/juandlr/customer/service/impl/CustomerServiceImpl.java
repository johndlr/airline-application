package com.juandlr.customer.service.impl;

import com.juandlr.customer.dto.CustomerDto;
import com.juandlr.customer.entity.Customer;
import com.juandlr.customer.exception.CustomerAlreadyExistsException;
import com.juandlr.customer.exception.CustomerNotFoundException;
import com.juandlr.customer.mapper.CustomerMapper;
import com.juandlr.customer.repository.CustomerRepository;
import com.juandlr.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void createCustomer(CustomerDto customerDto) {
        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (customerOptional.isPresent()){
            throw new CustomerAlreadyExistsException("The client you are trying to register already exists.");
        }
        customerRepository.save(CustomerMapper.mapToCustomer(new Customer(), customerDto));
    }

    @Override
    public CustomerDto fetchCustomer(String mobileNumber) {
        Customer customerFromDB = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new CustomerNotFoundException("The customer with the given mobile number: " + mobileNumber + " does not exist, please correct your information."));
        return CustomerMapper.mapToCustomerDto(customerFromDB, new CustomerDto());
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        Customer customerFromDB = customerRepository.findByMobileNumber(customerDto.getMobileNumber())
                .orElseThrow(() -> new CustomerNotFoundException("The client you are trying to update does not exist, please correct your information."));
        CustomerMapper.mapToCustomer(customerFromDB, customerDto);
        customerRepository.save(customerFromDB);
    }

    @Override
    public void deleteCustomer(String mobileNumber) {
        Customer customerFromDB = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new CustomerNotFoundException("The customer with the given mobile number: " + mobileNumber + " does not exist, please correct your information."));
        customerRepository.delete(customerFromDB);
    }
}
