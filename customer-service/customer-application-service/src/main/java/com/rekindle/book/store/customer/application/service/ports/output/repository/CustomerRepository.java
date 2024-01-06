package com.rekindle.book.store.customer.application.service.ports.output.repository;


import com.rekindle.book.store.customer.application.service.create.CreateCustomerCommand;
import com.rekindle.book.store.customer.application.service.dto.CustomerDto;
import com.rekindle.book.store.domain.customer.entity.Customer;
import java.util.List;
import java.util.UUID;

public interface CustomerRepository {

  Customer createCustomer(Customer customer);

  CustomerDto getCustomerById(UUID customerId);

  List<CustomerDto> fetchAllCustomers();

  void updateCustomerInformation(UUID customerId, CreateCustomerCommand createCommand);

  void deleteCustomer(UUID customerId);
}
