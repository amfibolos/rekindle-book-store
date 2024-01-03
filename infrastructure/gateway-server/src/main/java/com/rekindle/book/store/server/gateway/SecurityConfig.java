package com.rekindle.book.store.server.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CorsSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class SecurityConfig {

  @Bean
  @Order(1)
  public GlobalFilter customPreFilter() {
    return (exchange, chain) -> {
      log.info("Intercepting request to {}", exchange.getRequest().getPath());
      return chain.filter(exchange);
    };
  }

//  @Bean
//  @Order(0)
//  public GlobalFilter customPostFilter() {
//    return (exchange, chain) -> {
//      return chain.filter(exchange)
//          .then(Mono.fromRunnable(() -> {
//            exchange.getRequest();
//          }));
//    };
//  }

  @Bean
  public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) {
    httpSecurity.csrf(CsrfSpec::disable)
        .cors(CorsSpec::disable)
        .authorizeExchange(spec -> {
          spec.pathMatchers("/actuator/**").permitAll();
          spec.anyExchange().authenticated();
        })
        .oauth2ResourceServer(customizer -> customizer.jwt(Customizer.withDefaults()));
    return httpSecurity.build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring().requestMatchers("/v3/api-docs/**",
        "/swagger-ui/**",
        "/v2/api-docs/**",
        "/swagger-resources/**"
    );
  }

}
