package com.juandlr.message.service;

import com.juandlr.message.dto.ReservationMsgDto;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(ReservationMsgDto reservationMsgDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(reservationMsgDto.email());
        message.setSubject("Reservation made");
        message.setText("Dear customer, we confirm that your reservation was made successfully. Reservation Number: " + reservationMsgDto.reservationNumber());
        javaMailSender.send(message);
    }

}
