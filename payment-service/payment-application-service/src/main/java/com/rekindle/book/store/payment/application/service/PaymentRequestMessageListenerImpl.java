package com.rekindle.book.store.payment.application.service;


import com.rekindle.book.store.domain.payment.event.PaymentEvent;
import com.rekindle.book.store.payment.application.service.dto.PaymentRequest;
import com.rekindle.book.store.payment.application.service.ports.input.message.listener.PaymentRequestMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {

  private final PaymentRequestHelper paymentRequestHelper;

  public PaymentRequestMessageListenerImpl(PaymentRequestHelper paymentRequestHelper) {
    this.paymentRequestHelper = paymentRequestHelper;
  }

  @Override
  public void completePayment(PaymentRequest paymentRequest) {
    PaymentEvent paymentEvent = paymentRequestHelper.persistPayment(paymentRequest);
    fireEvent(paymentEvent);
  }

  @Override
  public void cancelPayment(PaymentRequest paymentRequest) {
    PaymentEvent paymentEvent = paymentRequestHelper.persistCancelPayment(paymentRequest);
    fireEvent(paymentEvent);
  }

  private void fireEvent(PaymentEvent paymentEvent) {
    log.info("Publishing payment event with payment id: {} and order id: {}",
        paymentEvent.getPayment().getId().getValue(),
        paymentEvent.getPayment().getOrderId().getValue());
    paymentEvent.fire();
  }
}
