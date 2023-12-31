package com.rekindle.book.store.bookstore.application.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "bookstore-service-topics")
public class BookstoreServiceConfigData {

  private String bookstoreApprovalRequestTopicName;
  private String bookstoreApprovalResponseTopicName;
}
