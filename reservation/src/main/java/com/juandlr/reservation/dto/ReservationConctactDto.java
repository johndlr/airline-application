package com.juandlr.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "flight")
@Getter
@Setter
public class ReservationConctactDto {
}
