package com.juandlr.customer;

import com.juandlr.customer.dto.CustomerContactInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {CustomerContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Customer microservice REST API Documentation",
				description = "Customer microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Juan de la Rosa",
						email = "juandlrdev@gmail.com",
						url = "https://github.com/johndlr"
				)
		)
)
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}
