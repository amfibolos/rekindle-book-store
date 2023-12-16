package com.rekindle.book.store.order.messaging.mapper;

import com.rekindle.book.store.domain.core.valueobject.OrderApprovalStatus;
import com.rekindle.book.store.domain.core.valueobject.PaymentStatus;
import com.rekindle.book.store.domain.order.entity.Order;
import com.rekindle.book.store.domain.order.event.OrderCancelledEvent;
import com.rekindle.book.store.domain.order.event.OrderCreatedEvent;
import com.rekindle.book.store.domain.order.event.OrderPaidEvent;
import com.rekindle.book.store.kafka.avro.model.BookstoreApprovalRequestAvroModel;
import com.rekindle.book.store.kafka.avro.model.BookstoreApprovalResponseAvroModel;
import com.rekindle.book.store.kafka.avro.model.BookstoreOrderStatus;
import com.rekindle.book.store.kafka.avro.model.PaymentOrderStatus;
import com.rekindle.book.store.kafka.avro.model.PaymentRequestAvroModel;
import com.rekindle.book.store.kafka.avro.model.PaymentResponseAvroModel;
import com.rekindle.book.store.kafka.avro.model.Product;
import com.rekindle.book.store.order.application.service.domain.dto.message.BookstoreApprovalResponse;
import com.rekindle.book.store.order.application.service.domain.dto.message.PaymentResponse;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMessagingDataMapper {

  public PaymentRequestAvroModel orderCreatedEventToPaymentRequestAvroModel(
      OrderCreatedEvent orderCreatedEvent
  ) {
    Order order = orderCreatedEvent.getOrder();
    return PaymentRequestAvroModel.newBuilder()
        .setId(UUID.randomUUID().toString())
        .setSagaId("")
        .setCustomerId(order.getCustomerId().getValue().toString())
        .setOrderId(order.getId().getValue().toString())
        .setPrice(order.getPrice().getAmount())
        .setCreatedAt(orderCreatedEvent.getCreatedAt().toInstant())
        .setPaymentOrderStatus(PaymentOrderStatus.PENDING)
        .build();
  }

  public PaymentRequestAvroModel orderCancelledEventToPaymentRequestAvroModel(
      OrderCancelledEvent orderCancelledEvent
  ) {
    Order order = orderCancelledEvent.getOrder();
    return PaymentRequestAvroModel.newBuilder()
        .setId(UUID.randomUUID().toString())
        .setSagaId("")
        .setCustomerId(order.getCustomerId().getValue().toString())
        .setOrderId(order.getId().getValue().toString())
        .setPrice(order.getPrice().getAmount())
        .setCreatedAt(orderCancelledEvent.getCreatedAt().toInstant())
        .setPaymentOrderStatus(PaymentOrderStatus.CANCELLED)
        .build();
  }

  public BookstoreApprovalRequestAvroModel
  orderPaidEventToBookstoreApprovalRequestAvroModel(OrderPaidEvent orderPaidEvent) {
    Order order = orderPaidEvent.getOrder();
    return BookstoreApprovalRequestAvroModel.newBuilder()
        .setId(UUID.randomUUID().toString())
        .setSagaId("")
        .setOrderId(order.getId().getValue().toString())
        .setBookstoreId(order.getBookstoreId().getValue().toString())
        .setOrderId(order.getId().getValue().toString())
        .setBookstoreOrderStatus(BookstoreOrderStatus
            .valueOf(order.getOrderStatus().name()))
        .setProducts(order.getItems().stream().map(orderItem ->
            Product.newBuilder()
                .setId(orderItem.getProduct().getId().getValue().toString())
                .setQuantity(orderItem.getQuantity())
                .build()).collect(Collectors.toList()))
        .setPrice(order.getPrice().getAmount())
        .setCreatedAt(orderPaidEvent.getCreatedAt().toInstant())
        .setBookstoreOrderStatus(BookstoreOrderStatus.PAID)
        .build();
  }

  public PaymentResponse paymentResponseAvroModelToPaymentResponse(
      PaymentResponseAvroModel
          paymentResponseAvroModel
  ) {
    return PaymentResponse.builder()
        .id(paymentResponseAvroModel.getId())
        .sagaId(paymentResponseAvroModel.getSagaId())
        .paymentId(paymentResponseAvroModel.getPaymentId())
        .customerId(paymentResponseAvroModel.getCustomerId())
        .orderId(paymentResponseAvroModel.getOrderId())
        .price(paymentResponseAvroModel.getPrice())
        .createdAt(paymentResponseAvroModel.getCreatedAt())
        .paymentStatus(PaymentStatus.valueOf(
            paymentResponseAvroModel.getPaymentStatus().name()))
        .failureMessages(paymentResponseAvroModel.getFailureMessages())
        .build();
  }

  public BookstoreApprovalResponse
  approvalResponseAvroModelToApprovalResponse(
      BookstoreApprovalResponseAvroModel
          bookstoreApprovalResponseAvroModel
  ) {
    return BookstoreApprovalResponse.builder()
        .id(bookstoreApprovalResponseAvroModel.getId())
        .sagaId(bookstoreApprovalResponseAvroModel.getSagaId())
        .bookstoreId(bookstoreApprovalResponseAvroModel.getBookstoreId())
        .orderId(bookstoreApprovalResponseAvroModel.getOrderId())
        .createdAt(bookstoreApprovalResponseAvroModel.getCreatedAt())
        .orderApprovalStatus(OrderApprovalStatus.valueOf(
            bookstoreApprovalResponseAvroModel.getOrderApprovalStatus().name()))
        .failureMessages(bookstoreApprovalResponseAvroModel.getFailureMessages())
        .build();
  }
}
