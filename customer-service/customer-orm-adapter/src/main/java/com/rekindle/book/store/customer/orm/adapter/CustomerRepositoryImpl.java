package com.rekindle.book.store.customer.orm.adapter;


import com.rekindle.book.store.customer.application.service.create.CreateCustomerCommand;
import com.rekindle.book.store.customer.application.service.dto.CustomerDto;
import com.rekindle.book.store.customer.application.service.ports.output.repository.CustomerRepository;
import com.rekindle.book.store.customer.orm.entity.CustomerEntity;
import com.rekindle.book.store.customer.orm.mapper.CustomerDataAccessMapper;
import com.rekindle.book.store.customer.orm.repository.CustomerJpaRepository;
import com.rekindle.book.store.domain.customer.entity.Customer;
import com.rekindle.book.store.domain.customer.exception.CustomerDomainException;
import java.util.List;
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
  public CustomerDto getCustomerById(UUID customerId) {
    return customerJpaRepository.findById(customerId)
        .map(customerDataAccessMapper::customerEntityToCustomerDto)
        .orElseThrow(() -> new CustomerDomainException("Customer not found!"));
  }

  @Override
  public List<CustomerDto> fetchAllCustomers() {
    return customerJpaRepository.findAll()
        .stream().map(customerDataAccessMapper::customerEntityToCustomerDto)
        .toList();
  }

  @Override
  public void updateCustomerInformation(UUID customerId, CreateCustomerCommand createCommand) {
    CustomerEntity entity = customerJpaRepository.findById(customerId)
        .orElseThrow(() -> new CustomerDomainException("Customer not found!"));
    entity.setFirstName(createCommand.firstName());
    entity.setLastName(createCommand.lastName());
    entity.setUsername(createCommand.username());
    customerJpaRepository.save(entity);
  }

  @Override
  public void deleteCustomer(UUID customerId) {
    customerJpaRepository.findById(customerId)
        .orElseThrow(() -> new CustomerDomainException("Customer not found!"));
    customerJpaRepository.deleteById(customerId);
  }
}
