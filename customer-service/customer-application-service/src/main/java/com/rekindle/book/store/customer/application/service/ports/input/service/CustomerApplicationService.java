package com.rekindle.book.store.customer.application.service.ports.input.service;


import com.rekindle.book.store.customer.application.service.create.CreateCustomerCommand;
import com.rekindle.book.store.customer.application.service.create.CreateCustomerResponse;
import com.rekindle.book.store.customer.application.service.dto.CustomerDto;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface CustomerApplicationService {

  CreateCustomerResponse createCustomer(@Valid CreateCustomerCommand createCustomerCommand);

  CustomerDto fetchCustomer(UUID customerId);

  List<CustomerDto> fetchCustomers();

  void updateCustomer(UUID customerId, CreateCustomerCommand createCommand);

  void delete(UUID customerId);
}
