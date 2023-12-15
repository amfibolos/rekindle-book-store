package com.rekindle.book.store.order.application.service.domain.ports.output.repository;

import com.rekindle.book.store.domain.core.entity.Customer;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

  Optional<Customer> findCustomer(UUID customerId);
}
