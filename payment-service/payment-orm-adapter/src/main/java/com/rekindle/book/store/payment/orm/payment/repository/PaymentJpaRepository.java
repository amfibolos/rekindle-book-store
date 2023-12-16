package com.rekindle.book.store.payment.orm.payment.repository;


import com.rekindle.book.store.payment.orm.payment.entity.PaymentEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID> {

  Optional<PaymentEntity> findByOrderId(UUID orderId);


}
