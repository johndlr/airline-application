package com.juandlr.reservation.service.client;

import com.juandlr.reservation.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "customer", fallback = CustomerFallback.class)
public interface CustomerClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<CustomerDto> fetchCustomer(@RequestParam String mobileNumber);
}
