package com.rekindle.book.store.server.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableDiscoveryClient
@EnableWebFluxSecurity
@SpringBootApplication(scanBasePackages = "com.rekindle.book.store")
public class GatewayServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayServerApplication.class, args);
  }

  @RequestMapping("/circuitbreakerfallback")
  public String circuitBreakerFallback() {
    return "This is a fallback";
  }

  @Bean
  public RouteLocator rekindleGlobalReroute(RouteLocatorBuilder builder) {
    return builder
        .routes()
        .route("bookstore_route_1", r -> r.path("/rekindle/bookstore")
            .filters(f -> f.rewritePath("/rekindle/bookstore",
                "/api/v1/bookstore"))
            .uri("lb://BOOKSTORE-MICROSERVICE"))
        .route("bookstore_route_2", r -> r.path("/rekindle/bookstore/**")
            .filters(f -> f.rewritePath("/rekindle/bookstore/(?<segment>.*)",
                "/api/v1/bookstore/${segment}"))
            .uri("lb://BOOKSTORE-MICROSERVICE"))
        .route("payment_route_1", r -> r.path("/rekindle/payment")
            .filters(f -> f.rewritePath("/rekindle/payment",
                "/api/v1/payment"))
            .uri("lb://PAYMENT-MICROSERVICE"))
        .route("payment_route_2", r -> r.path("/rekindle/payment/**")
            .filters(f -> f.rewritePath("/rekindle/payment/(?<segment>.*)",
                "/api/v1/payment/${segment}"))
            .uri("lb://PAYMENT-MICROSERVICE"))
        .route("order_route_1", r -> r.path("/rekindle/order")
            .filters(f -> f.rewritePath("/rekindle/order",
                "/api/v1/order"))
            .uri("lb://ORDER-MICROSERVICE"))
        .route("order_route_2", r -> r.path("/rekindle/order/**")
            .filters(f -> f.rewritePath("/rekindle/order/(?<segment>.*)",
                "/api/v1/order/${segment}"))
            .uri("lb://ORDER-MICROSERVICE"))
        .route("authorization_route", r -> r.path("/oauth2/token")
            .uri("lb://AUTHORIZATION-SERVER-MS"))
        .route("customer_route_1", r -> r.path("/rekindle/customers")
            .filters(f -> f.rewritePath("/rekindle/customers",
                "/api/v1/customers"))
            .uri("lb://CUSTOMER-MICROSERVICE"))
        .route("customer_route_2", r -> r.path("/rekindle/customers/**")
            .filters(f -> f.rewritePath("/rekindle/customers/(?<segment>.*)",
                "/api/v1/customers/${segment}"))
            .uri("lb://CUSTOMER-MICROSERVICE"))
        .build();
  }
}
