package com.rekindle.book.store.payment.application.service.ports.output.repository;


import com.rekindle.book.store.domain.core.valueobject.CustomerId;
import com.rekindle.book.store.domain.payment.entity.CreditHistory;
import java.util.List;
import java.util.Optional;

public interface CreditHistoryRepository {

  CreditHistory save(CreditHistory creditHistory);

  Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId);
}
