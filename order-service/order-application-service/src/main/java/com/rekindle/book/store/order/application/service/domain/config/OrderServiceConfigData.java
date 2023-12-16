package com.rekindle.book.store.order.application.service.domain.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "order-service-topics")
public class OrderServiceConfigData {
    private String paymentRequestTopicName;
    private String paymentResponseTopicName;
    private String bookstoreApprovalRequestTopicName;
    private String bookstoreApprovalResponseTopicName;
}
