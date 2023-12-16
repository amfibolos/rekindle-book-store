package com.rekindle.book.store.domain.order.entity;


import com.rekindle.book.store.domain.core.entity.AggregateRoot;
import com.rekindle.book.store.domain.core.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

  public Customer() {
  }

  public Customer(CustomerId customerId) {
    super.setId(customerId);
  }
}
