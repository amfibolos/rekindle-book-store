package com.rekindle.book.store.payment.application.service.ports.input.message.listener;


import com.rekindle.book.store.payment.application.service.dto.PaymentRequest;

public interface PaymentRequestMessageListener {

  void completePayment(PaymentRequest paymentRequest);

  void cancelPayment(PaymentRequest paymentRequest);
}
