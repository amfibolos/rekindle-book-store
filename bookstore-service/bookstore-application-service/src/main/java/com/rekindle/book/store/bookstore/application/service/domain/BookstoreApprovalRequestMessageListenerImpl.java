package com.rekindle.book.store.bookstore.application.service.domain;


import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreApprovalRequest;
import com.rekindle.book.store.bookstore.application.service.domain.ports.input.message.listener.BookstoreApprovalRequestMessageListener;
import com.rekindle.book.store.domain.bookstore.event.OrderApprovalEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookstoreApprovalRequestMessageListenerImpl implements
    BookstoreApprovalRequestMessageListener {

  private final BookstoreApprovalRequestHelper bookstoreApprovalRequestHelper;

  public BookstoreApprovalRequestMessageListenerImpl(
      BookstoreApprovalRequestHelper
          bookstoreApprovalRequestHelper
  ) {
    this.bookstoreApprovalRequestHelper = bookstoreApprovalRequestHelper;
  }

  @Override
  public void approveOrder(BookstoreApprovalRequest bookstoreApprovalRequest) {
    OrderApprovalEvent orderApprovalEvent =
        bookstoreApprovalRequestHelper.persistOrderApproval(bookstoreApprovalRequest);
    orderApprovalEvent.fire();
  }
}
