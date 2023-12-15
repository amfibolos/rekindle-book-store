package com.rekindle.book.store.order.application.service.domain.ports.input.service;

import com.rekindle.book.store.order.application.service.domain.OrderCreateCommandHandler;
import com.rekindle.book.store.order.application.service.domain.OrderTrackCommandHandler;
import com.rekindle.book.store.order.application.service.domain.dto.create.CreateOrderCommand;
import com.rekindle.book.store.order.application.service.domain.dto.create.CreateOrderCommandResponse;
import com.rekindle.book.store.order.application.service.domain.dto.tracking.TrackOrderQuery;
import com.rekindle.book.store.order.application.service.domain.dto.tracking.TrackOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
class OrderApplicationServiceImpl implements OrderApplicationService {

  private final OrderCreateCommandHandler orderCreateCommandHandler;

  private final OrderTrackCommandHandler orderTrackCommandHandler;

  public OrderApplicationServiceImpl(
      OrderCreateCommandHandler orderCreateCommandHandler,
      OrderTrackCommandHandler orderTrackCommandHandler
  ) {
    this.orderCreateCommandHandler = orderCreateCommandHandler;
    this.orderTrackCommandHandler = orderTrackCommandHandler;
  }

  @Override
  public CreateOrderCommandResponse createOrder(CreateOrderCommand createOrderCommand) {
    return orderCreateCommandHandler.createOrder(createOrderCommand);
  }

  @Override
  public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
    return orderTrackCommandHandler.trackOrder(trackOrderQuery);
  }
}
