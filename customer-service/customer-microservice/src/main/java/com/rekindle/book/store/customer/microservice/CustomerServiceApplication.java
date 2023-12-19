package com.rekindle.book.store.customer.microservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = {"com.rekindle.book.store.customer.orm"})
@EntityScan(basePackages = {"com.rekindle.book.store.customer.orm"})
@SpringBootApplication(scanBasePackages = "com.rekindle.book.store")
public class CustomerServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CustomerServiceApplication.class, args);
  }
}