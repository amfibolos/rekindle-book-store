package com.rekindle.book.store.bookstore.application.service.domain.config;

import com.rekindle.book.store.domain.bookstore.services.BookstoreDomainService;
import com.rekindle.book.store.domain.bookstore.services.BookstoreDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookstoreApplicationServiceBeanConfiguration {

  @Bean
  public BookstoreDomainService restaurantDomainService() {
    return new BookstoreDomainServiceImpl();
  }
}
