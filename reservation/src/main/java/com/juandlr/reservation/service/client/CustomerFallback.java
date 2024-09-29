package com.juandlr.reservation.service.client;

import com.juandlr.reservation.dto.CustomerDto;
import com.juandlr.reservation.exception.CustomerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerFallback implements CustomerClient{
    @Override
    public ResponseEntity<CustomerDto> fetchCustomer(String mobileNumber) {
        throw new CustomerNotFoundException("The customer with the given mobile number does not exist, please verify your information.");
    }
}
