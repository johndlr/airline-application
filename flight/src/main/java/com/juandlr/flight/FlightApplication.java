package com.juandlr.flight;


import com.juandlr.flight.dto.FlightConctactDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {FlightConctactDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Flight microservice REST API Documentation",
				description = "Flight microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Juan de la Rosa",
						email = "apexjohn67@gmail.com",
						url = "https://github.com/johndlr"
				)
		)
)
public class FlightApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightApplication.class, args);
	}

}
