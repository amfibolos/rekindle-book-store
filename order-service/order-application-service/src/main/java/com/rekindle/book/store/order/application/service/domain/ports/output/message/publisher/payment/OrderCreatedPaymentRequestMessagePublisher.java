package com.rekindle.book.store.order.application.service.domain.ports.output.message.publisher.payment;


import com.rekindle.book.store.domain.core.event.DomainEventPublisher;
import com.rekindle.book.store.domain.order.event.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends
    DomainEventPublisher<OrderCreatedEvent> {

}
