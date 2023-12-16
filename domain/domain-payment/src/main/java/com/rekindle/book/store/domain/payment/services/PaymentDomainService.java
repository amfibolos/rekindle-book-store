package com.rekindle.book.store.domain.payment.services;


import com.rekindle.book.store.domain.core.event.DomainEventPublisher;
import com.rekindle.book.store.domain.payment.entity.CreditEntry;
import com.rekindle.book.store.domain.payment.entity.CreditHistory;
import com.rekindle.book.store.domain.payment.entity.Payment;
import com.rekindle.book.store.domain.payment.event.PaymentCancelledEvent;
import com.rekindle.book.store.domain.payment.event.PaymentCompletedEvent;
import com.rekindle.book.store.domain.payment.event.PaymentEvent;
import com.rekindle.book.store.domain.payment.event.PaymentFailedEvent;
import java.util.List;

public interface PaymentDomainService {

  PaymentEvent validateAndInitiatePayment(
      Payment payment,
      CreditEntry creditEntry,
      List<CreditHistory> creditHistories,
      List<String> failureMessages,
      DomainEventPublisher<PaymentCompletedEvent>
          paymentCompletedEventDomainEventPublisher,
      DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher
  );

  PaymentEvent validateAndCancelPayment(
      Payment payment,
      CreditEntry creditEntry,
      List<CreditHistory> creditHistories,
      List<String> failureMessages,
      DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher,
      DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher
  );
}
