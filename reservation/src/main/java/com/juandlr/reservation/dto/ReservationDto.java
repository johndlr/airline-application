package com.juandlr.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Schema(name = "Reservation", description = "Schema to hold reservation information")
public class ReservationDto {

    @Schema(description = "Represents the reservation number", example = "RES12345")
    @NotEmpty(message = "Reservation number cannot be null or empty")
    @Size(min = 8, max = 8, message = "The length of the flight number should be exactly 8 characters")
    private String reservationNumber;

    @Schema(description = "Represents the flight number", example = "FL4D5E6F")
    @NotEmpty(message = "Flight number cannot be null or empty")
    @Size(min = 8, max = 8, message = "The length of the flight number should be exactly 8 characters")
    private String flightNumber;

    @Schema(description = "Represents the mobile number of the customer", example = "5512131415")
    @Size(min = 10, max = 10, message = "The length of the mobile number should be exactly 10 digits")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be only digits and length of 10")
    private String customerMobileNumber;

    @Schema(description = "Represents the seat number", example = "A12")
    @NotEmpty(message = "Seat number cannot be null or empty")
    @Pattern(regexp = "^[A-Z][0-9]+$", message = "Seat number must start with a letter followed by numbers")
    private String seatNumber;

    @Schema(description = "Represents the reservation date", example = "2023-10-01T15:30:00Z")
    @NotNull(message = "Reservation date cannot be null")
    @PastOrPresent(message = "Reservation date must be in the past or present")
    private Date reservationDate;

}
