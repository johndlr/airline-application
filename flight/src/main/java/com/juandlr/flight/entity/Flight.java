package com.juandlr.flight.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "flights")
public class Flight extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long flightId;

    @Column(name = "flight_number", updatable = false)
    private String flightNumber;

    private String origin;

    private String destination;

    @Column(name = "departure_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;

    @Column(name = "arrival_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;

    @ManyToOne
    @JoinColumn(name = "aircraft_type_id")
    private AircraftType aircraftType;

    public Flight(String flightNumber, String origin, String destination, Date departureTime, Date arrivalTime, AircraftType aircraftType) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.aircraftType = aircraftType;
    }
}
