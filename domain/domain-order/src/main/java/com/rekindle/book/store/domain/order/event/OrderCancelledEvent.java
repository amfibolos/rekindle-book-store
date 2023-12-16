package com.rekindle.book.store.domain.order.event;


import com.rekindle.book.store.domain.core.event.DomainEventPublisher;
import com.rekindle.book.store.domain.order.entity.Order;
import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent {

  private final DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher;

  public OrderCancelledEvent(
      Order order,
      ZonedDateTime createdAt,
      DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher
  ) {
    super(order, createdAt);
    this.orderCancelledEventDomainEventPublisher = orderCancelledEventDomainEventPublisher;
  }

  @Override
  public void fire() {
    orderCancelledEventDomainEventPublisher.publish(this);
  }
}
