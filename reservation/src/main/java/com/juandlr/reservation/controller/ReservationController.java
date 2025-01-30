package com.juandlr.reservation.controller;

import com.juandlr.reservation.constants.HttpConstants;
import com.juandlr.reservation.dto.*;
import com.juandlr.reservation.service.ReservationService;
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
public class ReservationController {

    private final ReservationService reservationService;

    private final ReservationConctactDto reservationConctactDto;

    @Operation(
            summary = "Create Reservation REST API",
            description = "REST API to create new reservation"
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
    public ResponseEntity<String> createReservation(@Valid @RequestBody ReservationCreateDto reservationCreateDto){
        String reservationNumber = reservationService.createReservation(reservationCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body("The reservation number associated with your request is: " + reservationNumber);
    }

    @Operation(
            summary = "Fetch Reservation Details REST API",
            description = "REST API to fetch reservation"
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
    public ResponseEntity<ReservationDetailsDto> fetchReservation(@Valid @NotEmpty(message = "Reservation number cannot be null or empty" ) @Size(min = 8, max = 8, message = "The length of the reservation number should exactly 8 characters")
                                                                      @RequestParam String reservationNumber){
        ReservationDetailsDto reservationDetailsDto = reservationService.fetchReservation(reservationNumber);
        return ResponseEntity.status(HttpStatus.OK.value()).body(reservationDetailsDto);
    }

    @Operation(
            summary = "Update Reservation Details REST API",
            description = "REST API to update reservation"
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
    public ResponseEntity<ResponseDto> updateReservation(@Valid @RequestBody ReservationDto reservationDto){
        reservationService.updateReservation(reservationDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(new ResponseDto(HttpConstants.STATUS_200,HttpConstants.MESSAGE_200));

    }

    @Operation(
            summary = "Delete Reservation Details REST API",
            description = "REST API to delete reservation"
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
    public ResponseEntity<ResponseDto> deleteReservation(@Valid @NotEmpty(message = "Reservation number cannot be null or empty" ) @Size(min = 8, max = 8, message = "The length of the reservation number should exactly 8 characters")
                                                             @RequestParam String reservationNumber){
        reservationService.deleteReservation(reservationNumber);
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
    public ResponseEntity<ReservationConctactDto> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(reservationConctactDto);
    }

}
