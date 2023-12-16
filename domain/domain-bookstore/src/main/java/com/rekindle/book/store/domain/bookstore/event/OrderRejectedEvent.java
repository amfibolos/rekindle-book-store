package com.rekindle.book.store.domain.bookstore.event;


import com.rekindle.book.store.domain.bookstore.entity.OrderApproval;
import com.rekindle.book.store.domain.core.event.DomainEventPublisher;
import com.rekindle.book.store.domain.core.valueobject.BookstoreId;
import java.time.ZonedDateTime;
import java.util.List;

public class OrderRejectedEvent extends OrderApprovalEvent {

  private final DomainEventPublisher<OrderRejectedEvent> orderRejectedEventDomainEventPublisher;

  public OrderRejectedEvent(
      OrderApproval orderApproval,
      BookstoreId bookstoreId,
      List<String> failureMessages,
      ZonedDateTime createdAt,
      DomainEventPublisher<OrderRejectedEvent> orderRejectedEventDomainEventPublisher
  ) {
    super(orderApproval, bookstoreId, failureMessages, createdAt);
    this.orderRejectedEventDomainEventPublisher = orderRejectedEventDomainEventPublisher;
  }

  @Override
  public void fire() {
    orderRejectedEventDomainEventPublisher.publish(this);
  }
}
