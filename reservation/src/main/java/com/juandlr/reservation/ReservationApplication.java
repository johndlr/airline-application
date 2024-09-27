package com.juandlr.reservation;

import com.juandlr.reservation.dto.ReservationConctactDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {ReservationConctactDto.class})
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "Reservation microservice REST API Documentation",
				description = "Reservation microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Juan de la Rosa",
						email = "apexjohn67@gmail.com",
						url = "https://github.com/johndlr"
				)
		)
)
public class ReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationApplication.class, args);
	}

}
