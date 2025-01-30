package com.juandlr.flight.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aircraft_types")
@NoArgsConstructor @Getter @Setter
public class AircraftType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aircraft_type_id")
    private Long aircraftTypeId;

    private String model;

    private String manufacturer;

    private int capacity;

    public AircraftType(String model, String manufacturer, int capacity) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.capacity = capacity;
    }
}
