package com.rekindle.book.store.order.application.service.domain.ports.input.message.listener.payment;

import com.rekindle.book.store.domain.core.utilities.Delimiter;
import com.rekindle.book.store.domain.order.event.OrderPaidEvent;
import com.rekindle.book.store.order.application.service.domain.dto.message.PaymentResponse;
import com.rekindle.book.store.order.application.service.domain.saga.OrderPaymentSaga;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {

  private final OrderPaymentSaga orderPaymentSaga;

  public PaymentResponseMessageListenerImpl(OrderPaymentSaga orderPaymentSaga) {
    this.orderPaymentSaga = orderPaymentSaga;
  }

  @Override
  public void paymentCompleted(PaymentResponse paymentResponse) {
    OrderPaidEvent domainEvent = orderPaymentSaga.process(paymentResponse);
    log.info("Publishing OrderPaidEvent for order id: {}", paymentResponse.getOrderId());
    domainEvent.fire();
  }

  @Override
  public void paymentCancelled(PaymentResponse paymentResponse) {
    orderPaymentSaga.rollback(paymentResponse);
    log.info("Order is roll backed for order id: {} with failure messages: {}",
        paymentResponse.getOrderId(),
        String.join(Delimiter.COMMA, paymentResponse.getFailureMessages()));
  }
}
