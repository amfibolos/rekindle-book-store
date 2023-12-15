package com.rekindle.book.store.order.application.service.domain;


import com.rekindle.book.store.domain.core.entity.Bookstore;
import com.rekindle.book.store.domain.core.entity.Customer;
import com.rekindle.book.store.domain.core.entity.Order;
import com.rekindle.book.store.domain.core.event.OrderCreatedEvent;
import com.rekindle.book.store.domain.core.exception.OrderDomainException;
import com.rekindle.book.store.domain.core.services.OrderDomainService;
import com.rekindle.book.store.order.application.service.domain.dto.create.CreateOrderCommand;
import com.rekindle.book.store.order.application.service.domain.mapper.OrderDataMapper;
import com.rekindle.book.store.order.application.service.domain.ports.output.repository.BookstoreRepository;
import com.rekindle.book.store.order.application.service.domain.ports.output.repository.CustomerRepository;
import com.rekindle.book.store.order.application.service.domain.ports.output.repository.OrderRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderCreateHelper {

  private final OrderDomainService orderDomainService;

  private final OrderRepository orderRepository;

  private final CustomerRepository customerRepository;

  private final BookstoreRepository bookstoreRepository;

  private final OrderDataMapper orderDataMapper;

  public OrderCreateHelper(
      OrderDomainService orderDomainService,
      OrderRepository orderRepository,
      CustomerRepository customerRepository,
      BookstoreRepository bookstoreRepository,
      OrderDataMapper orderDataMapper
  ) {
    this.orderDomainService = orderDomainService;
    this.orderRepository = orderRepository;
    this.customerRepository = customerRepository;
    this.bookstoreRepository = bookstoreRepository;
    this.orderDataMapper = orderDataMapper;
  }

  @Transactional
  public OrderCreatedEvent persistOrder(CreateOrderCommand createOrderCommand) {
    checkCustomer(createOrderCommand.customerId());
    Bookstore bookStore = checkBookstore(createOrderCommand);
    Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
    OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order,
        bookStore);
    saveOrder(order);
    log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
    return orderCreatedEvent;
  }

  private Bookstore checkBookstore(CreateOrderCommand createOrderCommand) {
    Bookstore bookStore = orderDataMapper.createOrderCommandToBookstore(createOrderCommand);
    Optional<Bookstore> optionalBookstore = bookstoreRepository.findBookstoreInformation(
        bookStore);
    if (optionalBookstore.isEmpty()) {
      log.warn("Could not find bookstore with bookstore id: {}",
          createOrderCommand.bookstoreId());
      throw new OrderDomainException("Could not find bookstore with bookstore id: " +
          createOrderCommand.bookstoreId());
    }
    return optionalBookstore.get();
  }

  private void checkCustomer(UUID customerId) {
    Optional<Customer> customer = customerRepository.findCustomer(customerId);
    if (customer.isEmpty()) {
      log.warn("Could not find customer with customer id: {}", customerId);
      throw new OrderDomainException("Could not find customer with customer id: " + customer);
    }
  }

  private Order saveOrder(Order order) {
    Order orderResult = orderRepository.save(order);
    if (orderResult == null) {
      log.error("Could not save order!");
      throw new OrderDomainException("Could not save order!");
    }
    log.info("Order is saved with id: {}", orderResult.getId().getValue());
    return orderResult;
  }
}
