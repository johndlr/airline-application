package com.juandlr.reservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "reservations")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
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

}
