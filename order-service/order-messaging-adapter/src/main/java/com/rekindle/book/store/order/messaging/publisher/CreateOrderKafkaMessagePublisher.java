package com.rekindle.book.store.order.messaging.publisher;


import com.rekindle.book.store.domain.order.event.OrderCreatedEvent;
import com.rekindle.book.store.kafka.avro.model.PaymentRequestAvroModel;
import com.rekindle.book.store.kafka.producer.KafkaMessageHelper;
import com.rekindle.book.store.kafka.producer.service.KafkaProducer;
import com.rekindle.book.store.order.application.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.rekindle.book.store.order.application.service.domain.config.OrderServiceConfigData;
import com.rekindle.book.store.order.messaging.mapper.OrderMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateOrderKafkaMessagePublisher implements
    OrderCreatedPaymentRequestMessagePublisher {

  private final OrderMessagingDataMapper orderMessagingDataMapper;
  private final OrderServiceConfigData orderServiceConfigData;
  private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;
  private final KafkaMessageHelper orderKafkaMessageHelper;

  public CreateOrderKafkaMessagePublisher(
      OrderMessagingDataMapper orderMessagingDataMapper,
      OrderServiceConfigData orderServiceConfigData,
      KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer,
      KafkaMessageHelper kafkaMessageHelper
  ) {
    this.orderMessagingDataMapper = orderMessagingDataMapper;
    this.orderServiceConfigData = orderServiceConfigData;
    this.kafkaProducer = kafkaProducer;
    this.orderKafkaMessageHelper = kafkaMessageHelper;
  }

  @Override
  public void publish(OrderCreatedEvent domainEvent) {
    String orderId = domainEvent.getOrder().getId().getValue().toString();
    log.info("Received OrderCreatedEvent for order id: {}", orderId);

    try {
      PaymentRequestAvroModel paymentRequestAvroModel = orderMessagingDataMapper
          .orderCreatedEventToPaymentRequestAvroModel(domainEvent);

      kafkaProducer.send(orderServiceConfigData.getPaymentRequestTopicName(),
          orderId,
          paymentRequestAvroModel,
          orderKafkaMessageHelper
              .getKafkaCallback(orderServiceConfigData.getPaymentResponseTopicName(),
                  paymentRequestAvroModel,
                  orderId,
                  "PaymentRequestAvroModel"));

      log.info("PaymentRequestAvroModel sent to Kafka for order id: {}",
          paymentRequestAvroModel.getOrderId());
    } catch (Exception e) {
      log.error("Error while sending PaymentRequestAvroModel message" +
          " to kafka with order id: {}, error: {}", orderId, e.getMessage());
    }
  }
}
