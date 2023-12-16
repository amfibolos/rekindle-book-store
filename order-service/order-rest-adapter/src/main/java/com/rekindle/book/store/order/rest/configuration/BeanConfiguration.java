package com.rekindle.book.store.order.rest.configuration;

import com.rekindle.book.store.domain.order.event.OrderCreatedEvent;
import com.rekindle.book.store.domain.order.services.OrderDomainService;
import com.rekindle.book.store.domain.order.services.OrderDomainServiceImpl;
import com.rekindle.book.store.order.application.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  //TODO TO BE REPLACED BY ACTUAL IMPLEMENTATION
  @Bean
  public OrderCreatedPaymentRequestMessagePublisher bookstore() {
    return new OrderCreatedPaymentRequestMessagePublisher() {
      @Override
      public void publish(OrderCreatedEvent domainEvent) {

      }
    };
  }
}
