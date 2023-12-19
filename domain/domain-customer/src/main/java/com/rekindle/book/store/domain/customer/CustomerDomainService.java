package com.rekindle.book.store.domain.customer;


import com.rekindle.book.store.domain.customer.entity.Customer;
import com.rekindle.book.store.domain.customer.event.CustomerCreatedEvent;

public interface CustomerDomainService {

  CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);

}
