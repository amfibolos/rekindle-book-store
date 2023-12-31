package com.rekindle.book.store.order.application.service.domain.ports.output.message.publisher.bookstoreapproval;


import com.rekindle.book.store.domain.core.event.DomainEventPublisher;
import com.rekindle.book.store.domain.order.event.OrderPaidEvent;

public interface OrderPaidBookstoreRequestMessagePublisher extends
    DomainEventPublisher<OrderPaidEvent> {

}
