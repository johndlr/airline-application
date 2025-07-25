package com.juandlr.message.functions;

import com.juandlr.message.dto.ReservationMsgDto;
import com.juandlr.message.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    private final EmailService emailService;

    public MessageFunctions(EmailService emailService) {
        this.emailService = emailService;
    }

    @Bean
    public Function<ReservationMsgDto, String> email(){
        return reservationMsgDto -> {
            emailService.sendEmail(reservationMsgDto);
            log.info("Sending email with details: " + reservationMsgDto);
          return reservationMsgDto.reservationNumber();
        };
    }


}
