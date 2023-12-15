package com.rekindle.book.store.domain.core.services;


import com.rekindle.book.store.domain.core.entity.Bookstore;
import com.rekindle.book.store.domain.core.entity.Order;
import com.rekindle.book.store.domain.core.entity.Product;
import com.rekindle.book.store.domain.core.event.OrderCancelledEvent;
import com.rekindle.book.store.domain.core.event.OrderCreatedEvent;
import com.rekindle.book.store.domain.core.event.OrderPaidEvent;
import com.rekindle.book.store.domain.core.exception.OrderDomainException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

  private static final String UTC = "UTC";

  @Override
  public OrderCreatedEvent validateAndInitiateOrder(Order order, Bookstore bookStore) {
    validateBookstore(bookStore);
    setOrderProductInformation(order, bookStore);
    order.validateOrder();
    order.initializeOrder();
    log.info("Order with id: {} is initiated", order.getId().getValue());
    return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
  }

  @Override
  public OrderPaidEvent payOrder(Order order) {
    order.pay();
    log.info("Order with id: {} is paid", order.getId().getValue());
    return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
  }

  @Override
  public void approveOrder(Order order) {
    order.approve();
    log.info("Order with id: {} is approved", order.getId().getValue());
  }

  @Override
  public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
    order.initCancel(failureMessages);
    log.info("Order payment is cancelling for order id: {}", order.getId().getValue());
    return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
  }

  @Override
  public void cancelOrder(Order order, List<String> failureMessages) {
    order.cancel(failureMessages);
    log.info("Order with id: {} is cancelled", order.getId().getValue());
  }

  private void validateBookstore(Bookstore bookStore) {
    if (!bookStore.isActive()) {
      throw new OrderDomainException("Bookstore with id " + bookStore.getId().getValue() +
          " is currently not active!");
    }
  }

  private void setOrderProductInformation(Order order, Bookstore bookStore) {
    order.getItems().forEach(orderItem -> bookStore.getProducts().forEach(bookstoreProduct -> {
      Product currentProduct = orderItem.getProduct();
      if (currentProduct.equals(bookstoreProduct)) {
        currentProduct.updateWithConfirmedNameAndPrice(bookstoreProduct.getName(),
            bookstoreProduct.getPrice());
      }
    }));
  }
}
