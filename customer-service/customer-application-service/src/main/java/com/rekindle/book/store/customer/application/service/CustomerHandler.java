package com.rekindle.book.store.customer.application.service;


import com.rekindle.book.store.customer.application.service.create.CreateCustomerCommand;
import com.rekindle.book.store.customer.application.service.mapper.CustomerDataMapper;
import com.rekindle.book.store.customer.application.service.ports.output.repository.CustomerRepository;
import com.rekindle.book.store.domain.customer.CustomerDomainService;
import com.rekindle.book.store.domain.customer.entity.Customer;
import com.rekindle.book.store.domain.customer.event.CustomerCreatedEvent;
import com.rekindle.book.store.domain.customer.exception.CustomerDomainException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
class CustomerHandler {

  private final CustomerDomainService customerDomainService;

  private final CustomerRepository customerRepository;

  private final CustomerDataMapper customerDataMapper;

  public CustomerHandler(
      CustomerDomainService customerDomainService,
      CustomerRepository customerRepository,
      CustomerDataMapper customerDataMapper
  ) {
    this.customerDomainService = customerDomainService;
    this.customerRepository = customerRepository;
    this.customerDataMapper = customerDataMapper;
  }

  @Transactional
  public CustomerCreatedEvent createCustomer(CreateCustomerCommand createCustomerCommand) {
    Customer customer = customerDataMapper.createCustomerCommandToCustomer(createCustomerCommand);
    CustomerCreatedEvent customerCreatedEvent = customerDomainService.validateAndInitiateCustomer(
        customer);
    Customer savedCustomer = customerRepository.createCustomer(customer);
    if (savedCustomer == null) {
      log.error("Could not save customer named: {} {}", createCustomerCommand.firstName(),
          createCustomerCommand.lastName());
      throw new CustomerDomainException("Could not save customer with id " +
          createCustomerCommand.firstName() + " " + createCustomerCommand.lastName());
    }
    return customerCreatedEvent;
  }

  public Customer fetchCustomerById(UUID customerId) {
    return customerRepository.getCustomerById(customerId);
  }
}
