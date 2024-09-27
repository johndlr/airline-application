package com.juandlr.flight.mapper;

import com.juandlr.flight.dto.FlightCreateDto;
import com.juandlr.flight.dto.FlightDto;
import com.juandlr.flight.entity.AircraftType;
import com.juandlr.flight.entity.Flight;

public class FlightMapper {

    public static Flight mapToFlightAndSetFlightNumberAndAircraftType(FlightCreateDto flightCreateDto, Flight flight, String flightNumber, AircraftType aircraftType){

        flight.setFlightNumber(flightNumber);
        flight.setAircraftType(aircraftType);
        flight.setOrigin(flightCreateDto.getOrigin());
        flight.setDestination(flightCreateDto.getDestination());
        flight.setDepartureTime(flightCreateDto.getDepartureTime());
        flight.setArrivalTime(flightCreateDto.getArrivalTime());
        return flight;
    }

    public static Flight mapToFlight(FlightDto flightDto, Flight flight){

        flight.setOrigin(flightDto.getOrigin());
        flight.setDestination(flightDto.getDestination());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setArrivalTime(flightDto.getArrivalTime());
        return flight;
    }

    public static FlightDto mapToFlightDto(FlightDto flightDto, Flight flight){

        flightDto.setFlightNumber(flight.getFlightNumber());
        flightDto.setOrigin(flight.getOrigin());
        flightDto.setDestination(flight.getDestination());
        flightDto.setDepartureTime(flight.getDepartureTime());
        flightDto.setArrivalTime(flight.getArrivalTime());
        return flightDto;
    }
}
