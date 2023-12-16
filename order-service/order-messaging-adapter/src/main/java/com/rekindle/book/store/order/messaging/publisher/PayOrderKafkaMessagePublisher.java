package com.rekindle.book.store.order.messaging.publisher;


import com.rekindle.book.store.domain.order.event.OrderPaidEvent;
import com.rekindle.book.store.kafka.avro.model.BookstoreApprovalRequestAvroModel;
import com.rekindle.book.store.kafka.producer.KafkaMessageHelper;
import com.rekindle.book.store.kafka.producer.service.KafkaProducer;
import com.rekindle.book.store.order.application.service.domain.config.OrderServiceConfigData;
import com.rekindle.book.store.order.application.service.domain.ports.output.message.publisher.bookstoreapproval.OrderPaidBookstoreRequestMessagePublisher;
import com.rekindle.book.store.order.messaging.mapper.OrderMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PayOrderKafkaMessagePublisher implements OrderPaidBookstoreRequestMessagePublisher {

  private final OrderMessagingDataMapper orderMessagingDataMapper;
  private final OrderServiceConfigData orderServiceConfigData;
  private final KafkaProducer<String, BookstoreApprovalRequestAvroModel> kafkaProducer;
  private final KafkaMessageHelper orderKafkaMessageHelper;

  public PayOrderKafkaMessagePublisher(
      OrderMessagingDataMapper orderMessagingDataMapper,
      OrderServiceConfigData orderServiceConfigData,
      KafkaProducer<String, BookstoreApprovalRequestAvroModel> kafkaProducer,
      KafkaMessageHelper orderKafkaMessageHelper
  ) {
    this.orderMessagingDataMapper = orderMessagingDataMapper;
    this.orderServiceConfigData = orderServiceConfigData;
    this.kafkaProducer = kafkaProducer;
    this.orderKafkaMessageHelper = orderKafkaMessageHelper;
  }

  @Override
  public void publish(OrderPaidEvent domainEvent) {
    String orderId = domainEvent.getOrder().getId().getValue().toString();

    try {
      BookstoreApprovalRequestAvroModel bookstoreApprovalRequestAvroModel =
          orderMessagingDataMapper.orderPaidEventToBookstoreApprovalRequestAvroModel(domainEvent);

      kafkaProducer.send(orderServiceConfigData.getBookstoreApprovalRequestTopicName(),
          orderId,
          bookstoreApprovalRequestAvroModel,
          orderKafkaMessageHelper
              .getKafkaCallback(orderServiceConfigData.getBookstoreApprovalRequestTopicName(),
                  bookstoreApprovalRequestAvroModel,
                  orderId,
                  "BookstoreApprovalRequestAvroModel"));

      log.info("BookstoreApprovalRequestAvroModel sent to kafka for order id: {}", orderId);
    } catch (Exception e) {
      log.error("Error while sending BookstoreApprovalRequestAvroModel message" +
          " to kafka with order id: {}, error: {}", orderId, e.getMessage());
    }
  }
}
