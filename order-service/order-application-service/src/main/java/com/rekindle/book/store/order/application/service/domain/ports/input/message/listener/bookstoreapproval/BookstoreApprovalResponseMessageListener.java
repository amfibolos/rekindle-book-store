package com.rekindle.book.store.order.application.service.domain.ports.input.message.listener.bookstoreapproval;


import com.rekindle.book.store.order.application.service.domain.dto.message.BookstoreApprovalResponse;

public interface BookstoreApprovalResponseMessageListener {

  void orderApproved(BookstoreApprovalResponse bookstoreApprovalResponse);

  void orderRejected(BookstoreApprovalResponse bookstoreApprovalResponse);
}
