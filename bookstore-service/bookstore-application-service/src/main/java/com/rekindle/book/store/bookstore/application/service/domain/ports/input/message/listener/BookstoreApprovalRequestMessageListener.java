package com.rekindle.book.store.bookstore.application.service.domain.ports.input.message.listener;


import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreApprovalRequest;

public interface BookstoreApprovalRequestMessageListener {
    void approveOrder(BookstoreApprovalRequest bookstoreApprovalRequest);
}
