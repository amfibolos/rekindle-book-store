package com.rekindle.book.store.payment.application.service.ports.output.repository;


import com.rekindle.book.store.domain.payment.entity.Payment;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {

  Payment save(Payment payment);

  Optional<Payment> findByOrderId(UUID orderId);
}
