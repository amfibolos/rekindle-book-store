package com.rekindle.book.store.order.orm.order.adapter;


import com.rekindle.book.store.domain.core.valueobject.OrderId;
import com.rekindle.book.store.domain.order.entity.Order;
import com.rekindle.book.store.domain.order.valueobject.TrackingId;
import com.rekindle.book.store.order.application.service.domain.ports.output.repository.OrderRepository;
import com.rekindle.book.store.order.orm.order.mapper.OrderDataAccessMapper;
import com.rekindle.book.store.order.orm.order.repository.OrderJpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryImpl implements OrderRepository {

  private final OrderJpaRepository orderJpaRepository;
  private final OrderDataAccessMapper orderDataAccessMapper;

  public OrderRepositoryImpl(
      OrderJpaRepository orderJpaRepository,
      OrderDataAccessMapper orderDataAccessMapper
  ) {
    this.orderJpaRepository = orderJpaRepository;
    this.orderDataAccessMapper = orderDataAccessMapper;
  }

  @Override
  public Order save(Order order) {
    return orderDataAccessMapper.orderEntityToOrder(orderJpaRepository
        .save(orderDataAccessMapper.orderToOrderEntity(order)));
  }

  @Override
  public Optional<Order> findByTrackingId(TrackingId trackingId) {
    return orderJpaRepository.findByTrackingId(trackingId.getValue())
        .map(orderDataAccessMapper::orderEntityToOrder);
  }

  @Override
  public Optional<Order> findById(OrderId orderId) {
    return orderJpaRepository.findById(orderId.getValue())
        .map(orderDataAccessMapper::orderEntityToOrder);
  }
}
