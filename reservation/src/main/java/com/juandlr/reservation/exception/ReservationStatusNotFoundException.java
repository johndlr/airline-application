package com.juandlr.reservation.exception;

public class ReservationStatusNotFoundException extends RuntimeException{
    public ReservationStatusNotFoundException(String message) {
        super(message);
    }
}
