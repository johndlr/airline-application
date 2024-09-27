package com.juandlr.reservation.exception;

public class FlightNotExistsException extends RuntimeException{

    public FlightNotExistsException(String message) {
        super(message);
    }
}
