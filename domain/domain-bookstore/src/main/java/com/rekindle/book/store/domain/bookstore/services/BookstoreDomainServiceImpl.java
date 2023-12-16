package com.rekindle.book.store.domain.bookstore.services;


import static com.rekindle.book.store.domain.core.utilities.DomainConstants.UTC;

import com.rekindle.book.store.domain.bookstore.entity.Bookstore;
import com.rekindle.book.store.domain.bookstore.event.OrderApprovalEvent;
import com.rekindle.book.store.domain.bookstore.event.OrderApprovedEvent;
import com.rekindle.book.store.domain.bookstore.event.OrderRejectedEvent;
import com.rekindle.book.store.domain.core.event.DomainEventPublisher;
import com.rekindle.book.store.domain.core.valueobject.OrderApprovalStatus;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookstoreDomainServiceImpl implements BookstoreDomainService {

  @Override
  public OrderApprovalEvent validateOrder(
      Bookstore bookstore,
      List<String> failureMessages,
      DomainEventPublisher<OrderApprovedEvent>
          orderApprovedEventDomainEventPublisher,
      DomainEventPublisher<OrderRejectedEvent>
          orderRejectedEventDomainEventPublisher
  ) {
    bookstore.validateOrder(failureMessages);
    log.info("Validating order with id: {}", bookstore.getOrderDetail().getId().getValue());

    if (failureMessages.isEmpty()) {
      log.info("Order is approved for order id: {}", bookstore.getOrderDetail().getId().getValue());
      bookstore.constructOrderApproval(OrderApprovalStatus.APPROVED);
      return new OrderApprovedEvent(bookstore.getOrderApproval(),
          bookstore.getId(),
          failureMessages,
          ZonedDateTime.now(ZoneId.of(UTC)),
          orderApprovedEventDomainEventPublisher);
    } else {
      log.info("Order is rejected for order id: {}", bookstore.getOrderDetail().getId().getValue());
      bookstore.constructOrderApproval(OrderApprovalStatus.REJECTED);
      return new OrderRejectedEvent(bookstore.getOrderApproval(),
          bookstore.getId(),
          failureMessages,
          ZonedDateTime.now(ZoneId.of(UTC)),
          orderRejectedEventDomainEventPublisher);
    }
  }
}
