package com.juandlr.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "Reservation Details", description = "Schema to hold reservation details, including customer and flight information")
public class ReservationDetailsDto {

    @Schema(description = "Represents the reservation number")
    private String reservationNumber;

    @Schema(description = "Represents the flight information")
    private FlightDto flightDto;

    @Schema(description = "Represents the customer information")
    private CustomerDto customerDto;

    @Schema(description = "Represents the seat number")
    private String seatNumber;

    @Schema(description = "Represents the reservation date")
    private Date reservationDate;

}
