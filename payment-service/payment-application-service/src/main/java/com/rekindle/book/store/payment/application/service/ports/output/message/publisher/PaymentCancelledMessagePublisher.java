package com.rekindle.book.store.payment.application.service.ports.output.message.publisher;


import com.rekindle.book.store.domain.core.event.DomainEventPublisher;
import com.rekindle.book.store.domain.payment.event.PaymentCancelledEvent;

public interface PaymentCancelledMessagePublisher extends
    DomainEventPublisher<PaymentCancelledEvent> {

}
