package com.juandlr.reservation.service;

import com.juandlr.reservation.dto.ReservationCreateDto;
import com.juandlr.reservation.dto.ReservationDetailsDto;
import com.juandlr.reservation.dto.ReservationDto;

public interface ReservationService {
    String createReservation(ReservationCreateDto reservationCreateDto);
    ReservationDetailsDto fetchReservation(String reservationNumber);
    void updateReservation(ReservationDto reservationDto);

    void deleteReservation(String reservationNumber);
}
