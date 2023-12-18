package com.rekindle.book.store.server.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class AuthorizationFilter implements GlobalFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    log.info("CHECKING AUTHORIZATION TOKEN");
    ServerHttpRequest request = exchange.getRequest();
    String authorizationHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
      return exchange.getResponse().setComplete();
    }
    return chain.filter(exchange);
  }
}
