package com.juandlr.reservation.service.client;


import com.juandlr.reservation.dto.FlightDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "flight", fallback = FlightFallback.class)
public interface FlightClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<FlightDto> fetchFlight(@RequestParam String flightNumber);
}
