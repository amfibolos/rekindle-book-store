package com.rekindle.book.store.payment.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.rekindle.book.store.payment.orm"})
@EntityScan(basePackages = {"com.rekindle.book.store.payment.orm"})
@SpringBootApplication(scanBasePackages = "com.rekindle.book.store.payment")
public class PaymentServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(PaymentServiceApplication.class, args);
  }
}
