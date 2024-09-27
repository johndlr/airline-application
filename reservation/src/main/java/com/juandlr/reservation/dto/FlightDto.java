package com.juandlr.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor  @NoArgsConstructor @Getter @Setter
@Schema(
        name = "Flight",
        description = "Schema to hold Flight information"
)
public class FlightDto {

    @Schema(
            description = "Represents the flight number", example = "FL1A2B3C"
    )
    @NotEmpty(message = "Flight number can not be a null or empty")
    @Size(min = 8, max = 8, message = "The length of the flight number should be exactly 8 characters")
    private String flightNumber;

    @Schema(
            description = "Represents the airport of origin", example = "JFK"
    )
    @NotEmpty(message = "Origin cannot be null or empty")
    private String origin;

    @Schema(
            description = "Represents the airport of destination", example = "LAX"
    )
    @NotEmpty(message = "Destination cannot be null or empty")
    private String destination;

    @Schema(
            description = "Represents the departure time", example = "2024-11-02T10:30:00Z"
    )
    @NotNull(message = "Departure time cannot be null")
    @Future(message = "Departure time must be in the future")
    private Date departureTime;

    @Schema(
            description = "Represents the arrival time", example = "2024-11-02T14:30:00Z"
    )
    @NotNull(message = "Arrival time cannot be null")
    @Future(message = "Arrival time must be in the future")
    private Date arrivalTime;

    @Schema(
            description = "Represents the aircraft model", example = "Airbus A320"
    )
    @NotEmpty(message = "Aircraft model cannot be null or empty")
    private String aircraftModel;

}
