package com.rekindle.book.store.domain.order.event;


import com.rekindle.book.store.domain.core.event.DomainEventPublisher;
import com.rekindle.book.store.domain.order.entity.Order;
import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent {

  private final DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher;

  public OrderPaidEvent(
      Order order,
      ZonedDateTime createdAt,
      DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher
  ) {
    super(order, createdAt);
    this.orderPaidEventDomainEventPublisher = orderPaidEventDomainEventPublisher;
  }

  @Override
  public void fire() {
    orderPaidEventDomainEventPublisher.publish(this);
  }
}
