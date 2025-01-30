package com.juandlr.reservation.service.impl;

import com.juandlr.reservation.dto.*;
import com.juandlr.reservation.entity.Reservation;
import com.juandlr.reservation.exception.CustomerNotFoundException;
import com.juandlr.reservation.exception.FlightNotExistsException;
import com.juandlr.reservation.exception.ReservationAlreadyExistsException;
import com.juandlr.reservation.exception.ReservationNotFoundException;
import com.juandlr.reservation.mapper.ReservationMapper;
import com.juandlr.reservation.repository.ReservationRepository;
import com.juandlr.reservation.service.ReservationService;
import com.juandlr.reservation.service.client.CustomerClient;
import com.juandlr.reservation.service.client.FlightClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    private static final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final CustomerClient customerClient;

    private final FlightClient flightClient;

    private final StreamBridge streamBridge;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, @Qualifier("com.juandlr.reservation.service.client.CustomerClient") CustomerClient customerClient, @Qualifier("com.juandlr.reservation.service.client.FlightClient") FlightClient flightClient, StreamBridge streamBridge) {
        this.reservationRepository = reservationRepository;
        this.customerClient = customerClient;
        this.flightClient = flightClient;
        this.streamBridge = streamBridge;
    }

    @Override
    public String createReservation(ReservationCreateDto reservationCreateDto) {
        // Fetch flight details
        FlightDto flightDto = getFlightDtoUsingFeignClient(reservationCreateDto.getFlightNumber());

        // Fetch customer details
        CustomerDto customerDto = getCustomerDtoUsingFeignClient(reservationCreateDto.getCustomerMobileNumber());

        // Check if reservation already exists
        Optional<Reservation> reservationOptional = reservationRepository.findByFlightNumber(flightDto.getFlightNumber());
        if (reservationOptional.isPresent()){
            throw new ReservationAlreadyExistsException("The reservation associated with the flight number:" + flightDto.getFlightNumber() + " already exists");
        }

        //Get a new reservation number
        String reservationNumber = generateReservationNumber();

        // Map ReservationDto to Reservation
        Reservation reservation = ReservationMapper.mapToReservationAndSetReservationNumber(new Reservation(), reservationCreateDto, reservationNumber);

        //Save the new reservation
        reservationRepository.save(reservation);

        //Sending communication
        sendCommunication(reservation, customerDto);

        //Return the reservation number
        return reservation.getReservationNumber();
    }

    private void sendCommunication(Reservation reservation, CustomerDto customerDto){
        ReservationMsgDto reservationMsgDto = new ReservationMsgDto(reservation.getReservationNumber(), reservation.getFlightNumber(),
                customerDto.getName(),customerDto.getLastName(),customerDto.getEmail(),customerDto.getMobileNumber());
        log.info("Sending communication request for the details: " + reservationMsgDto);
        boolean result = streamBridge.send("sendCommunication-out-0", reservationMsgDto);
        log.info("Is the communication request successfully triggered?: " + result);
    }

    @Override
    public ReservationDetailsDto fetchReservation(String reservationNumber) {
        // Check if reservation already exists
        Reservation reservationFromDB = reservationRepository.findByReservationNumber(reservationNumber)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found, please check your information."));
        String flightNumber = reservationFromDB.getFlightNumber();
        String customerMobileNumber = reservationFromDB.getCustomerMobileNumber();

        // Fetch flight details
        FlightDto flightDto = getFlightDtoUsingFeignClient(flightNumber);

        // Fetch customer details
        CustomerDto customerDto = getCustomerDtoUsingFeignClient(customerMobileNumber);

        return ReservationMapper.mapToReservationDetailsDto(reservationFromDB,new ReservationDetailsDto(), customerDto, flightDto);
    }

    @Override
    public void updateReservation(ReservationDto reservationDto) {
        // Check if reservation already exists
        Reservation reservationFromDB = reservationRepository.findByReservationNumber(reservationDto.getReservationNumber())
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found, please check your information."));

        // Ensure the reservation number is not modified
        if (!reservationFromDB.getReservationNumber().equals(reservationDto.getReservationNumber())) {
            throw new IllegalArgumentException("Reservation number cannot be modified");
        }

        // Check if the flight exists
        getFlightDtoUsingFeignClient(reservationDto.getFlightNumber());

        // Check if the customer exists
        getCustomerDtoUsingFeignClient(reservationDto.getCustomerMobileNumber());

        // Update the reservation
        ReservationMapper.mapToReservation(reservationFromDB, reservationDto);
        reservationRepository.save(reservationFromDB);
    }

    @Override
    public void deleteReservation(String reservationNumber) {
        // Check if reservation already exists
        Reservation reservationFromDB = reservationRepository.findByReservationNumber(reservationNumber)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found, please check your information."));
        //Delete reservation from DB
        reservationRepository.delete(reservationFromDB);
    }

    @Override
    public boolean updateCommunicationStatus(String reservationNumber) {
        boolean isUpdated = false;
        if (reservationNumber != null){
            Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber)
                    .orElseThrow(()-> new ReservationNotFoundException("Reservation not found, please check your information."));
            reservation.setCommunicationSw(true);
            reservationRepository.save(reservation);
            isUpdated = true;

        }
        return isUpdated;
    }


    public CustomerDto getCustomerDtoUsingFeignClient(String customerMobileNumber){
        ResponseEntity<CustomerDto> customerResponse = customerClient.fetchCustomer(customerMobileNumber);
        CustomerDto customerDto = customerResponse.getBody();
        if (customerDto == null) {
            throw new CustomerNotFoundException("Customer not found, please check your information.");
        }
        return customerDto;
    }

    public FlightDto getFlightDtoUsingFeignClient(String flightNumber){
        ResponseEntity<FlightDto> flightResponse = flightClient.fetchFlight(flightNumber);
        FlightDto flightDto = flightResponse.getBody();
        if (flightDto == null) {
            throw new FlightNotExistsException("Flight not found, please check your information.");
        }
        return flightDto;
    }

    private String generateReservationNumber() {
        String reservationNumber;
        do {
            reservationNumber = "RES" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        } while (reservationRepository.existsByReservationNumber(reservationNumber));
        return reservationNumber;
    }

}
