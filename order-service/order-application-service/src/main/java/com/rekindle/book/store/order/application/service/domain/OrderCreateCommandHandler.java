package com.rekindle.book.store.order.application.service.domain;


import com.rekindle.book.store.domain.order.event.OrderCreatedEvent;
import com.rekindle.book.store.order.application.service.domain.dto.create.CreateOrderCommand;
import com.rekindle.book.store.order.application.service.domain.dto.create.CreateOrderCommandResponse;
import com.rekindle.book.store.order.application.service.domain.mapper.OrderDataMapper;
import com.rekindle.book.store.order.application.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCreateCommandHandler {

  private final OrderCreateHelper orderCreateHelper;

  private final OrderDataMapper orderDataMapper;

  private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

  public OrderCreateCommandHandler(
      OrderCreateHelper orderCreateHelper,
      OrderDataMapper orderDataMapper,
      OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher
  ) {
    this.orderCreateHelper = orderCreateHelper;
    this.orderDataMapper = orderDataMapper;
    this.orderCreatedPaymentRequestMessagePublisher = orderCreatedPaymentRequestMessagePublisher;
  }

  public CreateOrderCommandResponse createOrder(CreateOrderCommand createOrderCommand) {
    OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand);
    log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
    orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
    return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder(),
        "Order created successfully");
  }
}
