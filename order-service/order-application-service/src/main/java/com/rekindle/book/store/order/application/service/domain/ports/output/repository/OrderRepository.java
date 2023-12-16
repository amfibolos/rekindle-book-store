package com.rekindle.book.store.order.application.service.domain.ports.output.repository;


import com.rekindle.book.store.domain.order.entity.Order;
import com.rekindle.book.store.domain.order.valueobject.TrackingId;
import java.util.Optional;

public interface OrderRepository {

  Order save(Order order);

  Optional<Order> findByTrackingId(TrackingId trackingId);
}
