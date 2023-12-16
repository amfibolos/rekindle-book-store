package com.rekindle.book.store.domain.order.event;


import com.rekindle.book.store.domain.core.event.DomainEventPublisher;
import com.rekindle.book.store.domain.order.entity.Order;
import java.time.ZonedDateTime;

public class OrderCreatedEvent extends OrderEvent {

  private final DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher;

  public OrderCreatedEvent(
      Order order,
      ZonedDateTime createdAt,
      DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher
  ) {
    super(order, createdAt);
    this.orderCreatedEventDomainEventPublisher = orderCreatedEventDomainEventPublisher;
  }

  @Override
  public void fire() {
    orderCreatedEventDomainEventPublisher.publish(this);
  }
}
