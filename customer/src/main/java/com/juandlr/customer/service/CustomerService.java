package com.juandlr.customer.service;

import com.juandlr.customer.dto.CustomerDto;

public interface CustomerService {
    void createCustomer(CustomerDto customerDto);

    CustomerDto fetchCustomer(String mobileNumber);

    void updateCustomer(CustomerDto customerDto);

    void deleteCustomer(String mobileNumber);
}
