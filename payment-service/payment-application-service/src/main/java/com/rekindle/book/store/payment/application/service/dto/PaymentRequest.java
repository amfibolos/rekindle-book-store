package com.rekindle.book.store.payment.application.service.dto;


import com.rekindle.book.store.domain.core.valueobject.PaymentOrderStatus;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PaymentRequest {

  private String id;
  private String sagaId;
  private String orderId;
  private String customerId;
  private BigDecimal price;
  private Instant createdAt;
  private PaymentOrderStatus paymentOrderStatus;

}
