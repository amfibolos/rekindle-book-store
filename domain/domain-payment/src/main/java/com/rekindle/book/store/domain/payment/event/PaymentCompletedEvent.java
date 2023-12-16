package com.rekindle.book.store.domain.payment.event;


import com.rekindle.book.store.domain.core.event.DomainEventPublisher;
import com.rekindle.book.store.domain.payment.entity.Payment;
import java.time.ZonedDateTime;
import java.util.Collections;

public class PaymentCompletedEvent extends PaymentEvent {

  private final DomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher;

  public PaymentCompletedEvent(
      Payment payment,
      ZonedDateTime createdAt,
      DomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher
  ) {
    super(payment, createdAt, Collections.emptyList());
    this.paymentCompletedEventDomainEventPublisher = paymentCompletedEventDomainEventPublisher;
  }

  @Override
  public void fire() {
    paymentCompletedEventDomainEventPublisher.publish(this);
  }
}
