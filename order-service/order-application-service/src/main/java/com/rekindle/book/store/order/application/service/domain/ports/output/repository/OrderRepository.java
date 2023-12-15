package com.rekindle.book.store.order.application.service.domain.ports.output.repository;


import com.rekindle.book.store.domain.core.entity.Order;
import com.rekindle.book.store.domain.core.valueobject.TrackingId;
import java.util.Optional;

public interface OrderRepository {

  Order save(Order order);

  Optional<Order> findByTrackingId(TrackingId trackingId);
}
