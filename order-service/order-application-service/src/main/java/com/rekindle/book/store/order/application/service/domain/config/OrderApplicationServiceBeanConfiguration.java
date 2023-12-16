package com.rekindle.book.store.order.application.service.domain.config;

import com.rekindle.book.store.domain.order.services.OrderDomainService;
import com.rekindle.book.store.domain.order.services.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderApplicationServiceBeanConfiguration {

  @Bean
  public OrderDomainService orderDomainService() {
    return new OrderDomainServiceImpl();
  }
}
