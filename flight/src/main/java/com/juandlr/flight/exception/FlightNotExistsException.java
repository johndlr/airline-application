package com.juandlr.flight.exception;

public class FlightNotExistsException extends RuntimeException{

    public FlightNotExistsException(String message) {
        super(message);
    }
}
