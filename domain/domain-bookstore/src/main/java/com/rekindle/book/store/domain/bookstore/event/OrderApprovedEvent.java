package com.rekindle.book.store.domain.bookstore.event;


import com.rekindle.book.store.domain.bookstore.entity.OrderApproval;
import com.rekindle.book.store.domain.core.event.DomainEventPublisher;
import com.rekindle.book.store.domain.core.valueobject.BookstoreId;
import java.time.ZonedDateTime;
import java.util.List;

public class OrderApprovedEvent extends OrderApprovalEvent {

  private final DomainEventPublisher<OrderApprovedEvent> orderApprovedEventDomainEventPublisher;

  public OrderApprovedEvent(
      OrderApproval orderApproval,
      BookstoreId bookstoreId,
      List<String> failureMessages,
      ZonedDateTime createdAt,
      DomainEventPublisher<OrderApprovedEvent> orderApprovedEventDomainEventPublisher
  ) {
    super(orderApproval, bookstoreId, failureMessages, createdAt);
    this.orderApprovedEventDomainEventPublisher = orderApprovedEventDomainEventPublisher;
  }

  @Override
  public void fire() {
    orderApprovedEventDomainEventPublisher.publish(this);
  }
}
