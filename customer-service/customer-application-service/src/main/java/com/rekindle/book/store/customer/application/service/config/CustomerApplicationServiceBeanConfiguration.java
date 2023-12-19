package com.rekindle.book.store.customer.application.service.config;

import com.rekindle.book.store.customer.application.service.ports.output.message.publisher.CustomerMessagePublisher;
import com.rekindle.book.store.domain.customer.CustomerDomainService;
import com.rekindle.book.store.domain.customer.CustomerDomainServiceImpl;
import com.rekindle.book.store.domain.customer.event.CustomerCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CustomerApplicationServiceBeanConfiguration {

  @Bean
  public CustomerDomainService customerDomainService() {
    return new CustomerDomainServiceImpl();
  }

  @Bean
  public CustomerMessagePublisher customerMessagePublisher() {
    return new CustomerMessagePublisher() {
      @Override
      public void publish(CustomerCreatedEvent customerCreatedEvent) {
        log.info("Publishing customer event...");
      }
    };
  }


}
