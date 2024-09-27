package com.juandlr.reservation.mapper;

import com.juandlr.reservation.dto.*;
import com.juandlr.reservation.entity.Reservation;

public class ReservationMapper {

    public static Reservation mapToReservationAndSetReservationNumber(Reservation reservation, ReservationCreateDto reservationCreateDto, String reservationNumber){
        reservation.setReservationNumber(reservationNumber);
        reservation.setFlightNumber(reservationCreateDto.getFlightNumber());
        reservation.setCustomerMobileNumber(reservationCreateDto.getCustomerMobileNumber());
        reservation.setSeatNumber(reservationCreateDto.getSeatNumber());
        reservation.setReservationDate(reservationCreateDto.getReservationDate());
        return reservation;
    }

    public static Reservation mapToReservation(Reservation reservation, ReservationDto reservationDto){
        reservation.setReservationNumber(reservationDto.getReservationNumber());
        reservation.setFlightNumber(reservationDto.getFlightNumber());
        reservation.setCustomerMobileNumber(reservationDto.getCustomerMobileNumber());
        reservation.setSeatNumber(reservationDto.getSeatNumber());
        reservation.setReservationDate(reservationDto.getReservationDate());
        return reservation;
    }

    public static ReservationDto mapToReservationDto(Reservation reservation, ReservationDto reservationDto){
        reservationDto.setReservationNumber(reservation.getReservationNumber());
        reservationDto.setFlightNumber(reservation.getFlightNumber());
        reservationDto.setCustomerMobileNumber(reservation.getCustomerMobileNumber());
        reservationDto.setSeatNumber(reservation.getSeatNumber());
        reservationDto.setReservationDate(reservation.getReservationDate());
        return reservationDto;
    }

    public static ReservationDetailsDto mapToReservationDetailsDto(Reservation reservation, ReservationDetailsDto reservationDetailsDto, CustomerDto customerDto, FlightDto flightDto){
        reservationDetailsDto.setReservationNumber(reservation.getReservationNumber());
        reservationDetailsDto.setCustomerDto(customerDto);
        reservationDetailsDto.setFlightDto(flightDto);
        reservationDetailsDto.setSeatNumber(reservation.getSeatNumber());
        reservationDetailsDto.setReservationDate(reservation.getReservationDate());
        return reservationDetailsDto;
    }
}
