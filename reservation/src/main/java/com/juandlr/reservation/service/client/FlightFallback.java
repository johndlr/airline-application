package com.juandlr.reservation.service.client;


import com.juandlr.reservation.dto.FlightDto;
import com.juandlr.reservation.exception.FlightNotExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FlightFallback implements FlightClient{
    @Override
    public ResponseEntity<FlightDto> fetchFlight(String flightNumber) {
        throw new FlightNotExistsException("The flight with the given number does not exist, please verify your information.");
    }
}
