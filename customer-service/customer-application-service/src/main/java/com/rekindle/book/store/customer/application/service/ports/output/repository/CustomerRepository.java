package com.rekindle.book.store.customer.application.service.ports.output.repository;


import com.rekindle.book.store.domain.customer.entity.Customer;
import java.util.UUID;

public interface CustomerRepository {

    Customer createCustomer(Customer customer);

    Customer getCustomerById(UUID customerId);
}
