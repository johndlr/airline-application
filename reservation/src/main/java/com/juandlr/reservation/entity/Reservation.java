package com.juandlr.reservation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "reservations")
@NoArgsConstructor @Getter @Setter
public class Reservation extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name = "reservation_number")
    private String reservationNumber;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "customer_mobile_number")
    private String customerMobileNumber;

    @Column(name = "seat_number")
    private String seatNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reservation_date")
    private Date reservationDate;

    @Column(name = "communication_sw")
    private Boolean communicationSw;

    public Reservation(String reservationNumber, String flightNumber, String customerMobileNumber, String seatNumber, Date reservationDate, Boolean communicationSw) {
        this.reservationNumber = reservationNumber;
        this.flightNumber = flightNumber;
        this.customerMobileNumber = customerMobileNumber;
        this.seatNumber = seatNumber;
        this.reservationDate = reservationDate;
        this.communicationSw = communicationSw;
    }

}
