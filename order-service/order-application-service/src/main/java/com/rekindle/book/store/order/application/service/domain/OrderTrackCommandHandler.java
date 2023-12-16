package com.rekindle.book.store.order.application.service.domain;


import com.rekindle.book.store.domain.order.entity.Order;
import com.rekindle.book.store.domain.order.exception.OrderNotFoundException;
import com.rekindle.book.store.domain.order.valueobject.TrackingId;
import com.rekindle.book.store.order.application.service.domain.dto.tracking.TrackOrderQuery;
import com.rekindle.book.store.order.application.service.domain.dto.tracking.TrackOrderResponse;
import com.rekindle.book.store.order.application.service.domain.mapper.OrderDataMapper;
import com.rekindle.book.store.order.application.service.domain.ports.output.repository.OrderRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderTrackCommandHandler {

  private final OrderDataMapper orderDataMapper;

  private final OrderRepository orderRepository;

  public OrderTrackCommandHandler(
      OrderDataMapper orderDataMapper, OrderRepository orderRepository
  ) {
    this.orderDataMapper = orderDataMapper;
    this.orderRepository = orderRepository;
  }

  @Transactional(readOnly = true)
  public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
    Optional<Order> orderResult =
        orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.orderTrackingId()));
    if (orderResult.isEmpty()) {
      log.warn("Could not find order with tracking id: {}", trackOrderQuery.orderTrackingId());
      throw new OrderNotFoundException("Could not find order with tracking id: " +
          trackOrderQuery.orderTrackingId());
    }
    return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
  }
}
