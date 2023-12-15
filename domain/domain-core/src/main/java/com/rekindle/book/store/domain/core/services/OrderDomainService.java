package com.rekindle.book.store.domain.core.services;


import com.rekindle.book.store.domain.core.entity.Bookstore;
import com.rekindle.book.store.domain.core.entity.Order;
import com.rekindle.book.store.domain.core.event.OrderCancelledEvent;
import com.rekindle.book.store.domain.core.event.OrderCreatedEvent;
import com.rekindle.book.store.domain.core.event.OrderPaidEvent;
import java.util.List;

public interface OrderDomainService {

  OrderCreatedEvent validateAndInitiateOrder(Order order, Bookstore bookStore);

  OrderPaidEvent payOrder(Order order);

  void approveOrder(Order order);

  OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

  void cancelOrder(Order order, List<String> failureMessages);
}
