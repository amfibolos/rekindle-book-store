package com.rekindle.book.store.payment.messaging.publisher.kafka;


import ch.qos.logback.core.util.TimeUtil;
import com.rekindle.book.store.domain.payment.event.PaymentCompletedEvent;
import com.rekindle.book.store.kafka.avro.model.PaymentResponseAvroModel;
import com.rekindle.book.store.kafka.producer.KafkaMessageHelper;
import com.rekindle.book.store.kafka.producer.service.KafkaProducer;
import com.rekindle.book.store.payment.application.service.config.PaymentServiceConfigData;
import com.rekindle.book.store.payment.application.service.ports.output.message.publisher.PaymentCompletedMessagePublisher;
import com.rekindle.book.store.payment.messaging.mapper.PaymentMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentCompletedKafkaMessagePublisher implements PaymentCompletedMessagePublisher {

  private final PaymentMessagingDataMapper paymentMessagingDataMapper;
  private final KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer;
  private final PaymentServiceConfigData paymentServiceConfigData;
  private final KafkaMessageHelper kafkaMessageHelper;

  public PaymentCompletedKafkaMessagePublisher(
      PaymentMessagingDataMapper paymentMessagingDataMapper,
      KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer,
      PaymentServiceConfigData paymentServiceConfigData,
      KafkaMessageHelper kafkaMessageHelper
  ) {
    this.paymentMessagingDataMapper = paymentMessagingDataMapper;
    this.kafkaProducer = kafkaProducer;
    this.paymentServiceConfigData = paymentServiceConfigData;
    this.kafkaMessageHelper = kafkaMessageHelper;
  }

  @Override
  public void publish(PaymentCompletedEvent domainEvent) {
    String orderId = domainEvent.getPayment().getOrderId().getValue().toString();

    log.info("Received PaymentCompletedEvent for order id: {}", orderId);

    try {
      //TODO So I do not forget about it
      //Sleep to simulate heavy traffic and longer response time
      Thread.sleep(7000);
      PaymentResponseAvroModel paymentResponseAvroModel =
          paymentMessagingDataMapper.paymentCompletedEventToPaymentResponseAvroModel(domainEvent);

      kafkaProducer.send(paymentServiceConfigData.getPaymentResponseTopicName(),
          orderId,
          paymentResponseAvroModel,
          kafkaMessageHelper.getKafkaCallback(
              paymentServiceConfigData.getPaymentResponseTopicName(),
              paymentResponseAvroModel,
              orderId,
              "PaymentResponseAvroModel"));

      log.info("PaymentResponseAvroModel sent to kafka for order id: {}", orderId);
    } catch (Exception e) {
      log.error("Error while sending PaymentResponseAvroModel message" +
          " to kafka with order id: {}, error: {}", orderId, e.getMessage());
    }
  }
}
