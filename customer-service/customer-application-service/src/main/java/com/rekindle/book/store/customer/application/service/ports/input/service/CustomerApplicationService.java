package com.rekindle.book.store.customer.application.service.ports.input.service;


import com.rekindle.book.store.customer.application.service.create.CreateCustomerCommand;
import com.rekindle.book.store.customer.application.service.create.CreateCustomerResponse;
import com.rekindle.book.store.domain.customer.entity.Customer;
import jakarta.validation.Valid;
import java.util.UUID;

public interface CustomerApplicationService {

    CreateCustomerResponse createCustomer(@Valid CreateCustomerCommand createCustomerCommand);

    Customer fetchCustomer(UUID customerId);
}
