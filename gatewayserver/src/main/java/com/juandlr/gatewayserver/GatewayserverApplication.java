package com.juandlr.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator routeConfig(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder.routes()
				.route(predicateSpec -> predicateSpec
						.path("/airline/customer/**")
						.filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/airline/customer/(?<segment>.*)", "/${segment}"))
						.uri("lb://CUSTOMER"))
				.route(predicateSpec -> predicateSpec
						.path("/airline/flight/**")
						.filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/airline/flight/(?<segment>.*)", "/${segment}"))
						.uri("lb://FLIGHT"))
				.route(predicateSpec -> predicateSpec
						.path("/airline/reservation/**")
						.filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/airline/reservation/(?<segment>.*)", "/${segment}"))
						.uri("lb://RESERVATION")).build();
	}

}
