package com.juandlr.customer.mapper;

import com.juandlr.customer.dto.AddressDto;
import com.juandlr.customer.dto.CustomerDto;
import com.juandlr.customer.entity.Address;
import com.juandlr.customer.entity.Customer;

public class CustomerMapper {


    public static Customer mapToCustomer(Customer customer, CustomerDto customerDto){
        customer.setName(customerDto.getName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setAddress(AddressMapper.mapToAddress(new Address(),customerDto.getAddressDto()));
        return customer;
    }

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto){
        customerDto.setName(customer.getName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        customerDto.setAddressDto(AddressMapper.mapToAddressDto(customer.getAddress(),new AddressDto()));
        return customerDto;
    }
}
