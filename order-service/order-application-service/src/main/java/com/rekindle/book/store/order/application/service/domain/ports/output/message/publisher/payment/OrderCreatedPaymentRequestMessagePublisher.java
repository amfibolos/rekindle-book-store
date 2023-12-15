package com.rekindle.book.store.order.application.service.domain.ports.output.message.publisher.payment;


import com.rekindle.book.store.domain.common.event.DomainEventPublisher;
import com.rekindle.book.store.domain.core.event.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends
    DomainEventPublisher<OrderCreatedEvent> {

}
