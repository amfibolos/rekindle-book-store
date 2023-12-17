package com.rekindle.book.store.order.application.service.domain.ports.input.message.listener.bookstoreapproval;

import com.rekindle.book.store.domain.core.utilities.Delimiter;
import com.rekindle.book.store.domain.order.event.OrderCancelledEvent;
import com.rekindle.book.store.order.application.service.domain.dto.message.BookstoreApprovalResponse;
import com.rekindle.book.store.order.application.service.domain.saga.OrderApprovalSaga;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class BookstoreApprovalResponseMessageListenerImpl implements
    BookstoreApprovalResponseMessageListener {

  private final OrderApprovalSaga orderApprovalSaga;

  public BookstoreApprovalResponseMessageListenerImpl(OrderApprovalSaga orderApprovalSaga) {
    this.orderApprovalSaga = orderApprovalSaga;
  }

  @Override
  public void orderApproved(BookstoreApprovalResponse bookstoreApprovalResponse) {
    orderApprovalSaga.process(bookstoreApprovalResponse);
    log.info("Order is approved for order id: {}", bookstoreApprovalResponse.getOrderId());
  }

  @Override
  public void orderRejected(BookstoreApprovalResponse bookstoreApprovalResponse) {
    OrderCancelledEvent domainEvent = orderApprovalSaga.rollback(bookstoreApprovalResponse);
    log.info("Publishing order cancelled event for order id: {} with failure messages: {}",
        bookstoreApprovalResponse.getOrderId(),
        String.join(Delimiter.COMMA, bookstoreApprovalResponse.getFailureMessages()));
    domainEvent.fire();
  }
}
