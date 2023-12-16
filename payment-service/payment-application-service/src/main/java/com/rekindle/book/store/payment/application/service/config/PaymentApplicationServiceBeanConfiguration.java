package com.rekindle.book.store.payment.application.service.config;

import com.rekindle.book.store.domain.payment.services.PaymentDomainService;
import com.rekindle.book.store.domain.payment.services.PaymentDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentApplicationServiceBeanConfiguration {

  @Bean
  public PaymentDomainService paymentDomainService() {
    return new PaymentDomainServiceImpl();
  }
}
