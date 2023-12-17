package com.rekindle.book.store.bookstore.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.rekindle.book.store.bookstore.orm"})
@EntityScan(basePackages = {"com.rekindle.book.store.bookstore.orm"})
@SpringBootApplication(scanBasePackages = "com.rekindle.book.store")
public class BookstoreServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookstoreServiceApplication.class, args);
  }
}
