package com.rekindle.book.store.order.application.service.domain.saga;


import com.rekindle.book.store.domain.order.entity.Order;
import com.rekindle.book.store.domain.order.event.OrderCancelledEvent;
import com.rekindle.book.store.domain.order.services.OrderDomainService;
import com.rekindle.book.store.order.application.service.domain.dto.message.BookstoreApprovalResponse;
import com.rekindle.book.store.order.application.service.domain.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.rekindle.book.store.saga.SagaStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderApprovalSaga implements
    SagaStep<BookstoreApprovalResponse, EmptyEvent, OrderCancelledEvent> {

  private final OrderDomainService orderDomainService;
  private final OrderSagaHelper orderSagaHelper;
  private final OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher;

  public OrderApprovalSaga(
      OrderDomainService orderDomainService,
      OrderSagaHelper orderSagaHelper,
      OrderCancelledPaymentRequestMessagePublisher
          orderCancelledPaymentRequestMessagePublisher
  ) {
    this.orderDomainService = orderDomainService;
    this.orderSagaHelper = orderSagaHelper;
    this.orderCancelledPaymentRequestMessagePublisher = orderCancelledPaymentRequestMessagePublisher;
  }

  @Override
  @Transactional
  public EmptyEvent process(BookstoreApprovalResponse bookstoreApprovalResponse) {
    log.info("Approving order with id: {}", bookstoreApprovalResponse.getOrderId());
    Order order = orderSagaHelper.findOrder(bookstoreApprovalResponse.getOrderId());
    orderDomainService.approveOrder(order);
    orderSagaHelper.saveOrder(order);
    log.info("Order with id: {} is approved", order.getId().getValue());
    return EmptyEvent.INSTANCE;
  }

  @Override
  @Transactional
  public OrderCancelledEvent rollback(BookstoreApprovalResponse bookstoreApprovalResponse) {
    log.info("Cancelling order with id: {}", bookstoreApprovalResponse.getOrderId());
    Order order = orderSagaHelper.findOrder(bookstoreApprovalResponse.getOrderId());
    OrderCancelledEvent domainEvent = orderDomainService.cancelOrderPayment(order,
        bookstoreApprovalResponse.getFailureMessages(),
        orderCancelledPaymentRequestMessagePublisher);
    orderSagaHelper.saveOrder(order);
    log.info("Order with id: {} is cancelling", order.getId().getValue());
    return domainEvent;
  }
}
