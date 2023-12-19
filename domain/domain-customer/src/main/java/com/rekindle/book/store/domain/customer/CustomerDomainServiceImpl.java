package com.rekindle.book.store.domain.customer;


import com.rekindle.book.store.domain.customer.entity.Customer;
import com.rekindle.book.store.domain.customer.event.CustomerCreatedEvent;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerDomainServiceImpl implements CustomerDomainService {

  public CustomerCreatedEvent validateAndInitiateCustomer(Customer customer) {
    log.info("Customer with id: {} is initiated", customer.getId().getValue());
    return new CustomerCreatedEvent(customer, ZonedDateTime.now(ZoneId.of("UTC")));
  }
}

