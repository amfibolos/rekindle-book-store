package com.rekindle.book.store.payment.messaging.mapper;


import com.rekindle.book.store.domain.core.valueobject.PaymentOrderStatus;
import com.rekindle.book.store.domain.payment.event.PaymentCancelledEvent;
import com.rekindle.book.store.domain.payment.event.PaymentCompletedEvent;
import com.rekindle.book.store.domain.payment.event.PaymentFailedEvent;
import com.rekindle.book.store.kafka.avro.model.PaymentRequestAvroModel;
import com.rekindle.book.store.kafka.avro.model.PaymentResponseAvroModel;
import com.rekindle.book.store.kafka.avro.model.PaymentStatus;
import com.rekindle.book.store.payment.application.service.dto.PaymentRequest;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class PaymentMessagingDataMapper {

  public PaymentResponseAvroModel
  paymentCompletedEventToPaymentResponseAvroModel(PaymentCompletedEvent paymentCompletedEvent) {
    return PaymentResponseAvroModel.newBuilder()
        .setId(UUID.randomUUID().toString())
        .setSagaId("")
        .setPaymentId(paymentCompletedEvent.getPayment().getId().getValue().toString())
        .setCustomerId(paymentCompletedEvent.getPayment().getCustomerId().getValue().toString())
        .setOrderId(paymentCompletedEvent.getPayment().getOrderId().getValue().toString())
        .setPrice(paymentCompletedEvent.getPayment().getPrice().getAmount())
        .setCreatedAt(paymentCompletedEvent.getCreatedAt().toInstant())
        .setPaymentStatus(
            PaymentStatus.valueOf(paymentCompletedEvent.getPayment().getPaymentStatus().name()))
        .setFailureMessages(paymentCompletedEvent.getFailureMessages())
        .build();
  }

  public PaymentResponseAvroModel
  paymentCancelledEventToPaymentResponseAvroModel(PaymentCancelledEvent paymentCancelledEvent) {
    return PaymentResponseAvroModel.newBuilder()
        .setId(UUID.randomUUID().toString())
        .setSagaId("")
        .setPaymentId(paymentCancelledEvent.getPayment().getId().getValue().toString())
        .setCustomerId(paymentCancelledEvent.getPayment().getCustomerId().getValue().toString())
        .setOrderId(paymentCancelledEvent.getPayment().getOrderId().getValue().toString())
        .setPrice(paymentCancelledEvent.getPayment().getPrice().getAmount())
        .setCreatedAt(paymentCancelledEvent.getCreatedAt().toInstant())
        .setPaymentStatus(
            PaymentStatus.valueOf(paymentCancelledEvent.getPayment().getPaymentStatus().name()))
        .setFailureMessages(paymentCancelledEvent.getFailureMessages())
        .build();
  }

  public PaymentResponseAvroModel
  paymentFailedEventToPaymentResponseAvroModel(PaymentFailedEvent paymentFailedEvent) {
    return PaymentResponseAvroModel.newBuilder()
        .setId(UUID.randomUUID().toString())
        .setSagaId("")
        .setPaymentId(paymentFailedEvent.getPayment().getId().getValue().toString())
        .setCustomerId(paymentFailedEvent.getPayment().getCustomerId().getValue().toString())
        .setOrderId(paymentFailedEvent.getPayment().getOrderId().getValue().toString())
        .setPrice(paymentFailedEvent.getPayment().getPrice().getAmount())
        .setCreatedAt(paymentFailedEvent.getCreatedAt().toInstant())
        .setPaymentStatus(
            PaymentStatus.valueOf(paymentFailedEvent.getPayment().getPaymentStatus().name()))
        .setFailureMessages(paymentFailedEvent.getFailureMessages())
        .build();
  }

  public PaymentRequest paymentRequestAvroModelToPaymentRequest(
      PaymentRequestAvroModel paymentRequestAvroModel
  ) {
    return PaymentRequest.builder()
        .id(paymentRequestAvroModel.getId())
        .sagaId(paymentRequestAvroModel.getSagaId())
        .customerId(paymentRequestAvroModel.getCustomerId())
        .orderId(paymentRequestAvroModel.getOrderId())
        .price(paymentRequestAvroModel.getPrice())
        .createdAt(paymentRequestAvroModel.getCreatedAt())
        .paymentOrderStatus(
            PaymentOrderStatus.valueOf(paymentRequestAvroModel.getPaymentOrderStatus().name()))
        .build();
  }
}
