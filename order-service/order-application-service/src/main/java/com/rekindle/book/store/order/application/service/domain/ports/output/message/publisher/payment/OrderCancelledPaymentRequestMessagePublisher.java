package com.rekindle.book.store.order.application.service.domain.ports.output.message.publisher.payment;


import com.rekindle.book.store.domain.core.event.DomainEventPublisher;
import com.rekindle.book.store.domain.order.event.OrderCancelledEvent;

public interface OrderCancelledPaymentRequestMessagePublisher extends
    DomainEventPublisher<OrderCancelledEvent> {

}
