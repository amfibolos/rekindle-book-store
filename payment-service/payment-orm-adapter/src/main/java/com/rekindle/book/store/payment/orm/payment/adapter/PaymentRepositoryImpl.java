package com.rekindle.book.store.payment.orm.payment.adapter;


import com.rekindle.book.store.domain.payment.entity.Payment;
import com.rekindle.book.store.payment.application.service.ports.output.repository.PaymentRepository;
import com.rekindle.book.store.payment.orm.payment.mapper.PaymentDataAccessMapper;
import com.rekindle.book.store.payment.orm.payment.repository.PaymentJpaRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class PaymentRepositoryImpl implements PaymentRepository {

  private final PaymentJpaRepository paymentJpaRepository;
  private final PaymentDataAccessMapper paymentDataAccessMapper;

  public PaymentRepositoryImpl(
      PaymentJpaRepository paymentJpaRepository,
      PaymentDataAccessMapper paymentDataAccessMapper
  ) {
    this.paymentJpaRepository = paymentJpaRepository;
    this.paymentDataAccessMapper = paymentDataAccessMapper;
  }

  @Override
  public Payment save(Payment payment) {
    return paymentDataAccessMapper
        .paymentEntityToPayment(paymentJpaRepository
            .save(paymentDataAccessMapper.paymentToPaymentEntity(payment)));
  }

  @Override
  public Optional<Payment> findByOrderId(UUID orderId) {
    return paymentJpaRepository.findByOrderId(orderId)
        .map(paymentDataAccessMapper::paymentEntityToPayment);
  }
}
