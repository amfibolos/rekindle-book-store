package com.rekindle.book.store.domain.customer.event;


import com.rekindle.book.store.domain.core.event.DomainEvent;
import com.rekindle.book.store.domain.customer.entity.Customer;
import java.time.ZonedDateTime;

public class CustomerCreatedEvent implements DomainEvent<Customer> {

  private final Customer customer;

  private final ZonedDateTime createdAt;

  public CustomerCreatedEvent(Customer customer, ZonedDateTime createdAt) {
    this.customer = customer;
    this.createdAt = createdAt;
  }

  public Customer getCustomer() {
    return customer;
  }

  @Override
  public void fire() {

  }
}
