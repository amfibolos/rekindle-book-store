package com.rekindle.book.store.domain.order.services;


import static com.rekindle.book.store.domain.core.utilities.DomainConstants.UTC;

import com.rekindle.book.store.domain.core.event.DomainEventPublisher;
import com.rekindle.book.store.domain.order.entity.Bookstore;
import com.rekindle.book.store.domain.order.entity.Order;
import com.rekindle.book.store.domain.order.entity.Product;
import com.rekindle.book.store.domain.order.event.OrderCancelledEvent;
import com.rekindle.book.store.domain.order.event.OrderCreatedEvent;
import com.rekindle.book.store.domain.order.event.OrderPaidEvent;
import com.rekindle.book.store.domain.order.exception.OrderDomainException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

  @Override
  public OrderCreatedEvent validateAndInitiateOrder(
      Order order, Bookstore bookstore,
      DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher
  ) {
    validateBookstore(bookstore);
    setOrderProductInformation(order, bookstore);
    order.validateOrder();
    order.initializeOrder();
    log.info("Order with id: {} is initiated", order.getId().getValue());
    return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)),
        orderCreatedEventDomainEventPublisher);
  }

  @Override
  public OrderPaidEvent payOrder(
      Order order, DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher
  ) {
    order.pay();
    log.info("Order with id: {} is paid", order.getId().getValue());
    return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)),
        orderPaidEventDomainEventPublisher);
  }

  @Override
  public void approveOrder(Order order) {
    order.approve();
    log.info("Order with id: {} is approved", order.getId().getValue());
  }

  @Override
  public OrderCancelledEvent cancelOrderPayment(
      Order order, List<String> failureMessages,
      DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher
  ) {
    order.initCancel(failureMessages);
    log.info("Order payment is cancelling for order id: {}", order.getId().getValue());
    return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)),
        orderCancelledEventDomainEventPublisher);
  }

  @Override
  public void cancelOrder(Order order, List<String> failureMessages) {
    order.cancel(failureMessages);
    log.info("Order with id: {} is cancelled", order.getId().getValue());
  }

  private void validateBookstore(Bookstore bookstore) {
    if (!bookstore.isActive()) {
      throw new OrderDomainException("Bookstore with id " + bookstore.getId().getValue() +
          " is currently not active!");
    }
  }

  private void setOrderProductInformation(Order order, Bookstore bookstore) {
    order.getItems().forEach(orderItem -> bookstore.getProducts().forEach(bookstoreProduct -> {
      Product currentProduct = orderItem.getProduct();
      if (currentProduct.equals(bookstoreProduct)) {
        currentProduct.updateWithConfirmedNameAndPrice(bookstoreProduct.getName(),
            bookstoreProduct.getPrice());
      }
    }));
  }
}
