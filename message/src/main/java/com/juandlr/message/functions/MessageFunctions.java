package com.juandlr.message.functions;

import com.juandlr.message.dto.ReservationMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<ReservationMsgDto, ReservationMsgDto> email(){
        return reservationMsgDto -> {
            log.info("Sending email with details: " + reservationMsgDto.toString());
          return reservationMsgDto;
        };
    }

    @Bean
    public Function<ReservationMsgDto, String> sms(){
        return reservationMsgDto -> {
            log.info("Sending sms with details: " + reservationMsgDto.toString());
            return reservationMsgDto.reservationNumber();
        };
    }


}
