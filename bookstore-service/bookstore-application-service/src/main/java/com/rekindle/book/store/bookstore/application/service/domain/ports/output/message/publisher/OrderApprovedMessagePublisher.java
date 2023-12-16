package com.rekindle.book.store.bookstore.application.service.domain.ports.output.message.publisher;


import com.rekindle.book.store.domain.bookstore.event.OrderApprovedEvent;
import com.rekindle.book.store.domain.core.event.DomainEventPublisher;

public interface OrderApprovedMessagePublisher extends DomainEventPublisher<OrderApprovedEvent> {
}
