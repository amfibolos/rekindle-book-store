package com.rekindle.book.store.domain.order.services;


import com.rekindle.book.store.domain.core.event.DomainEventPublisher;
import com.rekindle.book.store.domain.order.entity.Bookstore;
import com.rekindle.book.store.domain.order.entity.Order;
import com.rekindle.book.store.domain.order.event.OrderCancelledEvent;
import com.rekindle.book.store.domain.order.event.OrderCreatedEvent;
import com.rekindle.book.store.domain.order.event.OrderPaidEvent;
import java.util.List;

public interface OrderDomainService {

  OrderCreatedEvent validateAndInitiateOrder(
      Order order, Bookstore bookstore,
      DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher
  );

  OrderPaidEvent payOrder(
      Order order, DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher
  );

  void approveOrder(Order order);

  OrderCancelledEvent cancelOrderPayment(
      Order order, List<String> failureMessages,
      DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher
  );

  void cancelOrder(Order order, List<String> failureMessages);

}
