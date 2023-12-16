package com.rekindle.book.store.bookstore.messaging.publisher.kafka;

import com.rekindle.book.store.bookstore.application.service.domain.config.BookstoreServiceConfigData;
import com.rekindle.book.store.bookstore.application.service.domain.ports.output.message.publisher.OrderRejectedMessagePublisher;
import com.rekindle.book.store.bookstore.messaging.mapper.BookstoreMessagingDataMapper;
import com.rekindle.book.store.domain.bookstore.event.OrderRejectedEvent;
import com.rekindle.book.store.kafka.avro.model.BookstoreApprovalResponseAvroModel;
import com.rekindle.book.store.kafka.producer.KafkaMessageHelper;
import com.rekindle.book.store.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderRejectedKafkaMessagePublisher implements OrderRejectedMessagePublisher {

  private final BookstoreMessagingDataMapper bookstoreMessagingDataMapper;
  private final KafkaProducer<String, BookstoreApprovalResponseAvroModel> kafkaProducer;
  private final BookstoreServiceConfigData bookstoreServiceConfigData;
  private final KafkaMessageHelper kafkaMessageHelper;

  public OrderRejectedKafkaMessagePublisher(
      BookstoreMessagingDataMapper bookstoreMessagingDataMapper,
      KafkaProducer<String, BookstoreApprovalResponseAvroModel> kafkaProducer,
      BookstoreServiceConfigData bookstoreServiceConfigData,
      KafkaMessageHelper kafkaMessageHelper
  ) {
    this.bookstoreMessagingDataMapper = bookstoreMessagingDataMapper;
    this.kafkaProducer = kafkaProducer;
    this.bookstoreServiceConfigData = bookstoreServiceConfigData;
    this.kafkaMessageHelper = kafkaMessageHelper;
  }

  @Override
  public void publish(OrderRejectedEvent orderRejectedEvent) {
    String orderId = orderRejectedEvent.getOrderApproval().getOrderId().getValue().toString();

    log.info("Received OrderRejectedEvent for order id: {}", orderId);

    try {
      BookstoreApprovalResponseAvroModel bookstoreApprovalResponseAvroModel =
          bookstoreMessagingDataMapper
              .orderRejectedEventToBookstoreApprovalResponseAvroModel(orderRejectedEvent);

      kafkaProducer.send(bookstoreServiceConfigData.getBookstoreApprovalResponseTopicName(),
          orderId,
          bookstoreApprovalResponseAvroModel,
          kafkaMessageHelper.getKafkaCallback(bookstoreServiceConfigData
                  .getBookstoreApprovalResponseTopicName(),
              bookstoreApprovalResponseAvroModel,
              orderId,
              "BookstoreApprovalResponseAvroModel"));

      log.info("BookstoreApprovalResponseAvroModel sent to kafka at: {}", System.nanoTime());
    } catch (Exception e) {
      log.error("Error while sending BookstoreApprovalResponseAvroModel message" +
          " to kafka with order id: {}, error: {}", orderId, e.getMessage());
    }
  }

}
