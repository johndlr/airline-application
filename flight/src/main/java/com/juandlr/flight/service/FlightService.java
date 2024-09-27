package com.juandlr.flight.service;

import com.juandlr.flight.dto.FlightCreateDto;
import com.juandlr.flight.dto.FlightDto;

public interface FlightService {

    String createFlight(FlightCreateDto flightCreateDto);
    FlightDto fetchFlight(String flightNumber);
    void updateFlight(FlightDto flightDto);
    void deleteFlight(String flightNumber);

}
