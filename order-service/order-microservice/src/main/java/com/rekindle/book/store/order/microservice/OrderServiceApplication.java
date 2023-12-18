package com.rekindle.book.store.order.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = {"com.rekindle.book.store.order.orm"})
@EntityScan(basePackages = {"com.rekindle.book.store.order.orm"})
@SpringBootApplication(scanBasePackages = "com.rekindle.book.store")
public class OrderServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrderServiceApplication.class, args);
  }
}
