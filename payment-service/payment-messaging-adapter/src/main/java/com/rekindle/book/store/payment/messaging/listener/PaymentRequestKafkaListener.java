package com.rekindle.book.store.payment.messaging.listener;


import com.rekindle.book.store.kafka.avro.model.PaymentOrderStatus;
import com.rekindle.book.store.kafka.avro.model.PaymentRequestAvroModel;
import com.rekindle.book.store.kafka.consumer.KafkaConsumer;
import com.rekindle.book.store.payment.application.service.ports.input.message.listener.PaymentRequestMessageListener;
import com.rekindle.book.store.payment.messaging.mapper.PaymentMessagingDataMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentRequestKafkaListener implements KafkaConsumer<PaymentRequestAvroModel> {

  private final PaymentRequestMessageListener paymentRequestMessageListener;
  private final PaymentMessagingDataMapper paymentMessagingDataMapper;

  public PaymentRequestKafkaListener(
      PaymentRequestMessageListener paymentRequestMessageListener,
      PaymentMessagingDataMapper paymentMessagingDataMapper
  ) {
    this.paymentRequestMessageListener = paymentRequestMessageListener;
    this.paymentMessagingDataMapper = paymentMessagingDataMapper;
  }

  @Override
  @KafkaListener(id = "${kafka-consumer-config.payment-consumer-group-id}",
      topics = "${payment-service-topics.payment-request-topic-name}")
  public void receive(
      @Payload List<PaymentRequestAvroModel> messages,
      @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
      @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
      @Header(KafkaHeaders.OFFSET) List<Long> offsets
  ) {
    log.info("{} number of payment requests received with keys:{}, partitions:{} and offsets: {}",
        messages.size(),
        keys.toString(),
        partitions.toString(),
        offsets.toString());

    messages.forEach(paymentRequestAvroModel -> {
      if (PaymentOrderStatus.PENDING == paymentRequestAvroModel.getPaymentOrderStatus()) {
        log.info("Processing payment for order id: {}", paymentRequestAvroModel.getOrderId());
        paymentRequestMessageListener.completePayment(paymentMessagingDataMapper
            .paymentRequestAvroModelToPaymentRequest(paymentRequestAvroModel));
      } else if (PaymentOrderStatus.CANCELLED == paymentRequestAvroModel.getPaymentOrderStatus()) {
        log.info("Cancelling payment for order id: {}", paymentRequestAvroModel.getOrderId());
        paymentRequestMessageListener.cancelPayment(paymentMessagingDataMapper
            .paymentRequestAvroModelToPaymentRequest(paymentRequestAvroModel));
      }
    });

  }
}
