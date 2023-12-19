package com.rekindle.book.store.customer.application.service;


import com.rekindle.book.store.customer.application.service.create.CreateCustomerCommand;
import com.rekindle.book.store.customer.application.service.create.CreateCustomerResponse;
import com.rekindle.book.store.customer.application.service.mapper.CustomerDataMapper;
import com.rekindle.book.store.customer.application.service.ports.input.service.CustomerApplicationService;
import com.rekindle.book.store.customer.application.service.ports.output.message.publisher.CustomerMessagePublisher;
import com.rekindle.book.store.domain.customer.entity.Customer;
import com.rekindle.book.store.domain.customer.event.CustomerCreatedEvent;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
class CustomerApplicationServiceImpl implements CustomerApplicationService {

  private final CustomerHandler customerHandler;

  private final CustomerDataMapper customerDataMapper;

  private final CustomerMessagePublisher customerMessagePublisher;

  public CustomerApplicationServiceImpl(
      CustomerHandler customerHandler,
      CustomerDataMapper customerDataMapper,
      CustomerMessagePublisher customerMessagePublisher
  ) {
    this.customerHandler = customerHandler;
    this.customerDataMapper = customerDataMapper;
    this.customerMessagePublisher = customerMessagePublisher;
  }

  @Override
  public CreateCustomerResponse createCustomer(CreateCustomerCommand createCustomerCommand) {
    CustomerCreatedEvent customerCreatedEvent = customerHandler.createCustomer(
        createCustomerCommand);
    return customerDataMapper
        .customerToCreateCustomerResponse(customerCreatedEvent.getCustomer(),
            "Customer saved successfully!");
  }

  @Override
  public Customer fetchCustomer(UUID customerId) {
    return customerHandler.fetchCustomerById(customerId);
  }
}
