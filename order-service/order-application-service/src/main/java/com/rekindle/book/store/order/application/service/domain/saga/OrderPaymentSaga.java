package com.rekindle.book.store.order.application.service.domain.saga;

import com.rekindle.book.store.domain.core.valueobject.OrderId;
import com.rekindle.book.store.domain.order.entity.Order;
import com.rekindle.book.store.domain.order.event.OrderPaidEvent;
import com.rekindle.book.store.domain.order.exception.OrderNotFoundException;
import com.rekindle.book.store.domain.order.services.OrderDomainService;
import com.rekindle.book.store.order.application.service.domain.dto.message.PaymentResponse;
import com.rekindle.book.store.order.application.service.domain.ports.output.message.publisher.bookstoreapproval.OrderPaidBookstoreRequestMessagePublisher;
import com.rekindle.book.store.order.application.service.domain.ports.output.repository.OrderRepository;
import com.rekindle.book.store.saga.SagaStep;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderPaymentSaga implements SagaStep<PaymentResponse, OrderPaidEvent, EmptyEvent> {

  private final OrderDomainService orderDomainService;
  private final OrderRepository orderRepository;
  private final OrderPaidBookstoreRequestMessagePublisher orderPaidBookstoreRequestMessagePublisher;

  public OrderPaymentSaga(
      OrderDomainService orderDomainService,
      OrderRepository orderRepository,
      OrderPaidBookstoreRequestMessagePublisher orderPaidBookstoreRequestMessagePublisher
  ) {
    this.orderDomainService = orderDomainService;
    this.orderRepository = orderRepository;
    this.orderPaidBookstoreRequestMessagePublisher = orderPaidBookstoreRequestMessagePublisher;
  }

  @Override
  @Transactional
  public OrderPaidEvent process(PaymentResponse paymentResponse) {
    log.info("Completing payment for order with id: {}", paymentResponse.getOrderId());
    Order order = findOrder(paymentResponse.getOrderId());
    OrderPaidEvent domainEvent = orderDomainService.payOrder(order,
        orderPaidBookstoreRequestMessagePublisher);
    orderRepository.save(order);
    log.info("Order with id: {} is paid", order.getId().getValue());
    return domainEvent;
  }

  @Override
  @Transactional
  public EmptyEvent rollback(PaymentResponse paymentResponse) {
    log.info("Cancelling order with id: {}", paymentResponse.getOrderId());
    Order order = findOrder(paymentResponse.getOrderId());
    orderDomainService.cancelOrder(order, paymentResponse.getFailureMessages());
    orderRepository.save(order);
    log.info("Order with id: {} is cancelled", order.getId().getValue());
    return EmptyEvent.INSTANCE;
  }

  private Order findOrder(String orderId) {
    Optional<Order> orderResponse = orderRepository.findById(new OrderId(UUID.fromString(orderId)));
    if (orderResponse.isEmpty()) {
      log.error("Order with id: {} could not be found!", orderId);
      throw new OrderNotFoundException("Order with id " + orderId + " could not be found!");
    }
    return orderResponse.get();
  }

}
