package com.rekindle.book.store.domain.bookstore.services;


import com.rekindle.book.store.domain.bookstore.entity.Bookstore;
import com.rekindle.book.store.domain.bookstore.event.OrderApprovalEvent;
import com.rekindle.book.store.domain.bookstore.event.OrderApprovedEvent;
import com.rekindle.book.store.domain.bookstore.event.OrderRejectedEvent;
import com.rekindle.book.store.domain.core.event.DomainEventPublisher;
import java.util.List;

public interface BookstoreDomainService {

  OrderApprovalEvent validateOrder(
      Bookstore bookstore,
      List<String> failureMessages,
      DomainEventPublisher<OrderApprovedEvent> orderApprovedEventDomainEventPublisher,
      DomainEventPublisher<OrderRejectedEvent> orderRejectedEventDomainEventPublisher
  );
}
