package com.rekindle.book.store.order.application.service.domain.ports.input.message.listener.payment;


import com.rekindle.book.store.order.application.service.domain.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {

  void paymentCompleted(PaymentResponse paymentResponse);

  void paymentCancelled(PaymentResponse paymentResponse);
}
