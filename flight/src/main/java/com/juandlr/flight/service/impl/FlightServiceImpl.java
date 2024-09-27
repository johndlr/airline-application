package com.juandlr.flight.service.impl;

import com.juandlr.flight.dto.FlightCreateDto;
import com.juandlr.flight.dto.FlightDto;
import com.juandlr.flight.entity.AircraftType;
import com.juandlr.flight.entity.Flight;
import com.juandlr.flight.exception.AircraftTypeNotFoundException;
import com.juandlr.flight.exception.FlightNotExistsException;
import com.juandlr.flight.mapper.FlightMapper;
import com.juandlr.flight.repository.AircraftTypeRepository;
import com.juandlr.flight.repository.FlightRepository;
import com.juandlr.flight.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    private final AircraftTypeRepository aircraftTypeRepository;

    @Override
    public String createFlight(FlightCreateDto flightCreateDto) {
        String aircraftModel = flightCreateDto.getAircraftModel();
        AircraftType aircraftType = aircraftTypeRepository.findByModel(aircraftModel)
                .orElseThrow(() -> new AircraftTypeNotFoundException(aircraftModel + " does not exist please try again."));
        String flightNumber = generateFlightNumber();
        Flight flight = FlightMapper.mapToFlightAndSetFlightNumberAndAircraftType(flightCreateDto, new Flight(), flightNumber, aircraftType);
        flightRepository.save(flight);
        return flight.getFlightNumber();
    }

    @Override
    public FlightDto fetchFlight(String flightNumber) {
        Flight flightFromDB =flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(()-> new FlightNotExistsException("The flight with the number: " + flightNumber + " does not exist please try again."));
        String aircraftModel = flightFromDB.getAircraftType().getModel();
        FlightDto flightDto = FlightMapper.mapToFlightDto(new FlightDto(),flightFromDB);
        flightDto.setAircraftModel(aircraftModel);
        return flightDto;
    }

    @Override
    public void updateFlight(FlightDto flightDto) {
        Flight flightFromDB = flightRepository.findByFlightNumber(flightDto.getFlightNumber())
                .orElseThrow(() -> new FlightNotExistsException("The flight you are trying to update does not exist, please correct your information."));
        AircraftType aircraftType = aircraftTypeRepository.findByModel(flightDto.getAircraftModel())
                .orElseThrow(() -> new AircraftTypeNotFoundException(flightDto.getAircraftModel() + " does not exist, please try again"));
        Flight updatedFlight = FlightMapper.mapToFlight(flightDto, flightFromDB);
        updatedFlight.setAircraftType(aircraftType);
        flightRepository.save(updatedFlight);
    }

    @Override
    public void deleteFlight(String flightNumber) {
        Flight flightFromDB = flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new FlightNotExistsException("The flight with the number: " + flightNumber + " does not exist, please try again."));
        flightRepository.delete(flightFromDB);
    }

    private String generateFlightNumber() {
        String flightNumber;
        do {
            flightNumber = "FN" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        } while (flightRepository.existsByFlightNumber(flightNumber));
        return flightNumber;
    }


}
