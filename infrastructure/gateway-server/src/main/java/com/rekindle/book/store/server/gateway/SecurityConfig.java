package com.rekindle.book.store.server.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) {
    httpSecurity.authorizeExchange(
            spec -> spec.anyExchange().authenticated())
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
