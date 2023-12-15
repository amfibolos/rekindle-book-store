package com.rekindle.book.store.order.orm.customer.adapter;


import com.rekindle.book.store.domain.core.entity.Customer;
import com.rekindle.book.store.order.application.service.domain.ports.output.repository.CustomerRepository;
import com.rekindle.book.store.order.orm.customer.mapper.CustomerDataAccessMapper;
import com.rekindle.book.store.order.orm.customer.repository.CustomerJpaRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

  private final CustomerJpaRepository customerJpaRepository;
  private final CustomerDataAccessMapper customerDataAccessMapper;

  public CustomerRepositoryImpl(
      CustomerJpaRepository customerJpaRepository,
      CustomerDataAccessMapper customerDataAccessMapper
  ) {
    this.customerJpaRepository = customerJpaRepository;
    this.customerDataAccessMapper = customerDataAccessMapper;
  }

  @Override
  public Optional<Customer> findCustomer(UUID customerId) {
    return customerJpaRepository.findById(customerId)
        .map(customerDataAccessMapper::customerEntityToCustomer);
  }
}
