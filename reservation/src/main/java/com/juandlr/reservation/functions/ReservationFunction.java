package com.juandlr.reservation.functions;

import com.juandlr.reservation.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

/**
 * Configuration class for reservation-related functions.
 */
@Configuration
public class ReservationFunction {

  /**
   * Bean that defines a Consumer to update the communication status of a reservation.
   * 
   * @param reservationService the service used to update the communication status
   * @return a Consumer that takes a reservation number as input and updates its communication status
   */
    private static final Logger log = LoggerFactory.getLogger(ReservationFunction.class);

    @Bean
    public Consumer<String> updateCommunication(ReservationService reservationService){
        return reservationNumber -> {
          log.info("Updating Communication Status for the reservation number: " + reservationNumber);
          reservationService.updateCommunicationStatus(reservationNumber);
        };
    }
}
