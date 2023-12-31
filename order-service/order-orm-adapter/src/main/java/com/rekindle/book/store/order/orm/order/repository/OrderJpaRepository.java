package com.rekindle.book.store.order.orm.order.repository;


import com.rekindle.book.store.order.orm.order.entity.OrderEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {

  Optional<OrderEntity> findByTrackingId(UUID trackingId);
}
