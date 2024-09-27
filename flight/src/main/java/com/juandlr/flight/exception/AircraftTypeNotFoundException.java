package com.juandlr.flight.exception;

public class AircraftTypeNotFoundException extends RuntimeException {
    public AircraftTypeNotFoundException(String message) {
        super(message);
    }
}
