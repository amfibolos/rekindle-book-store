package com.rekindle.book.store.bookstore.application.service.domain.ports.output.repository;


import com.rekindle.book.store.domain.bookstore.entity.OrderApproval;

public interface OrderApprovalRepository {

  OrderApproval save(OrderApproval orderApproval);
}
