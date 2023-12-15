package com.rekindle.book.store.order.application.service.domain.ports.input.message.listener.bookstoreapproval;

import com.rekindle.book.store.order.application.service.domain.dto.message.BookstoreApprovalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class BookstoreApprovalResponseMessageListenerImpl implements
    BookstoreApprovalResponseMessageListener {

  @Override
  public void orderApproved(BookstoreApprovalResponse bookstoreApprovalResponse) {

  }

  @Override
  public void orderRejected(BookstoreApprovalResponse bookstoreApprovalResponse) {
  }
}
