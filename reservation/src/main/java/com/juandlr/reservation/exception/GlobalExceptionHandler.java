package com.juandlr.reservation.exception;


import com.juandlr.reservation.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> validationErrors = new ArrayList<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();
        validationErrorList.forEach((error) -> {
            String validationMsg = error.getDefaultMessage();
            validationErrors.add(validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception, WebRequest webRequest) {
       return new ResponseEntity<>(errorResponseDtoGenerator(exception,webRequest), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FlightNotExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(FlightNotExistsException exception, WebRequest webRequest) {
        return new ResponseEntity<>(errorResponseDtoGenerator(exception,webRequest), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(CustomerNotFoundException exception, WebRequest webRequest) {
        return new ResponseEntity<>(errorResponseDtoGenerator(exception,webRequest), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ReservationAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(ReservationAlreadyExistsException exception, WebRequest webRequest) {
        return new ResponseEntity<>(errorResponseDtoGenerator(exception,webRequest), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ReservationStatusNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(ReservationStatusNotFoundException exception, WebRequest webRequest) {
        return new ResponseEntity<>(errorResponseDtoGenerator(exception,webRequest), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(ReservationNotFoundException exception, WebRequest webRequest) {
        return new ResponseEntity<>(errorResponseDtoGenerator(exception,webRequest), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponseDto errorResponseDtoGenerator(Exception exception, WebRequest webRequest){
        return new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
    }

}
