package com.juandlr.flight.repository;

import com.juandlr.flight.entity.AircraftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AircraftTypeRepository extends JpaRepository<AircraftType,Long> {

    Optional<AircraftType> findByModel(String model);

}
