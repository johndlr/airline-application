package com.juandlr.flight.controller;

import com.juandlr.flight.constants.HttpConstants;
import com.juandlr.flight.dto.*;
import com.juandlr.flight.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class FlightController {

    private final FlightService flightService;

    private final FlightConctactDto flightConctactDto;

    @Operation(
            summary = "Create Flight REST API",
            description = "REST API to create new flight"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<String> createFlight(@Valid @RequestBody FlightCreateDto flightCreateAndUpdateDto){
        String flightNumber = flightService.createFlight(flightCreateAndUpdateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body("The flight number associated with your request is: " + flightNumber);
    }

    @Operation(
            summary = "Fetch flight Details REST API",
            description = "REST API to fetch flight"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<FlightDto> fetchFlight(
            @Valid
            @NotEmpty(message = "Flight number can not be a null or empty")
            @Size(min = 8, max = 8, message = "The length of the flight number should exactly 8 characters")
            @RequestParam String flightNumber){
        FlightDto flightDto = flightService.fetchFlight(flightNumber);
        return ResponseEntity.status(HttpStatus.OK.value()).body(flightDto);
    }

    @Operation(
            summary = "Update flight Details REST API",
            description = "REST API to update flight"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateFlight(@Valid @RequestBody FlightDto flightDto){
        flightService.updateFlight(flightDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(new ResponseDto(HttpConstants.STATUS_200,HttpConstants.MESSAGE_200));
    }

    @Operation(
            summary = "Delete flight Details REST API",
            description = "REST API to delete flight based on a flight number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteFlight(
            @Valid
            @NotEmpty(message = "Flight number can not be a null or empty")
            @Size(min = 8, max = 8, message = "The length of the reservation number should exactly 8 characters")
            @RequestParam String flightNumber){
        flightService.deleteFlight(flightNumber);
        return ResponseEntity.status(HttpStatus.OK.value()).body(new ResponseDto(HttpConstants.STATUS_200,HttpConstants.MESSAGE_200));
    }

    @Operation(
            summary = "Get Contact Info",
            description = "Contact Info details that can be reached out in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/contact-info")
    public ResponseEntity<FlightConctactDto> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(flightConctactDto);
    }
}
