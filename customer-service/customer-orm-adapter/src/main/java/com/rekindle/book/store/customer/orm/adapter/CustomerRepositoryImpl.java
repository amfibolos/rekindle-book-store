package com.rekindle.book.store.customer.orm.adapter;


import com.rekindle.book.store.customer.application.service.ports.output.repository.CustomerRepository;
import com.rekindle.book.store.customer.orm.mapper.CustomerDataAccessMapper;
import com.rekindle.book.store.customer.orm.repository.CustomerJpaRepository;
import com.rekindle.book.store.domain.customer.entity.Customer;
import com.rekindle.book.store.domain.customer.exception.CustomerDomainException;
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
  public Customer createCustomer(Customer customer) {
    return customerDataAccessMapper.customerEntityToCustomer(
        customerJpaRepository.save(customerDataAccessMapper.customerToCustomerEntity(customer)));
  }

  @Override
  public Customer getCustomerById(UUID customerId) {
    return customerJpaRepository.findById(customerId)
        .map(customerDataAccessMapper::customerEntityToCustomer)
        .orElseThrow(() -> new CustomerDomainException("Customer not found!"));
  }
}
