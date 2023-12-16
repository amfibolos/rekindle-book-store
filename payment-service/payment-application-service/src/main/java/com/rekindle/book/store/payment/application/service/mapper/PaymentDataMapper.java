package com.rekindle.book.store.payment.application.service.mapper;


import com.rekindle.book.store.domain.core.valueobject.CustomerId;
import com.rekindle.book.store.domain.core.valueobject.Money;
import com.rekindle.book.store.domain.core.valueobject.OrderId;
import com.rekindle.book.store.domain.payment.entity.Payment;
import com.rekindle.book.store.payment.application.service.dto.PaymentRequest;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class PaymentDataMapper {

  public Payment paymentRequestModelToPayment(PaymentRequest paymentRequest) {
    return Payment.builder()
        .orderId(new OrderId(UUID.fromString(paymentRequest.getOrderId())))
        .customerId(new CustomerId(UUID.fromString(paymentRequest.getCustomerId())))
        .price(new Money(paymentRequest.getPrice()))
        .build();
  }
}
