package com.rekindle.book.store.domain.core.event;


import com.rekindle.book.store.domain.core.entity.Order;
import java.time.ZonedDateTime;

public class OrderCreatedEvent extends OrderEvent {

  public OrderCreatedEvent(Order order, ZonedDateTime createdAt) {
    super(order, createdAt);
  }
}
