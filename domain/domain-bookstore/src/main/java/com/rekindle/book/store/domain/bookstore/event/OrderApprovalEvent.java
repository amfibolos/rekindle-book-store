package com.rekindle.book.store.domain.bookstore.event;


import com.rekindle.book.store.domain.bookstore.entity.OrderApproval;
import com.rekindle.book.store.domain.core.event.DomainEvent;
import com.rekindle.book.store.domain.core.valueobject.BookstoreId;
import java.time.ZonedDateTime;
import java.util.List;

public abstract class OrderApprovalEvent implements DomainEvent<OrderApproval> {

  private final OrderApproval orderApproval;
  private final BookstoreId bookstoreId;
  private final List<String> failureMessages;
  private final ZonedDateTime createdAt;

  public OrderApprovalEvent(
      OrderApproval orderApproval,
      BookstoreId bookstoreId,
      List<String> failureMessages,
      ZonedDateTime createdAt
  ) {
    this.orderApproval = orderApproval;
    this.bookstoreId = bookstoreId;
    this.failureMessages = failureMessages;
    this.createdAt = createdAt;
  }

  public OrderApproval getOrderApproval() {
    return orderApproval;
  }

  public BookstoreId getBookstoreId() {
    return bookstoreId;
  }

  public List<String> getFailureMessages() {
    return failureMessages;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }
}
