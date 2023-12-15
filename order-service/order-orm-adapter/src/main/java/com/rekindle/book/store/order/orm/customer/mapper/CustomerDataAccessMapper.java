package com.rekindle.book.store.order.orm.customer.mapper;


import com.rekindle.book.store.domain.core.entity.Customer;
import com.rekindle.book.store.domain.core.valueobject.CustomerId;
import com.rekindle.book.store.order.orm.customer.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

  public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
    return new Customer(new CustomerId(customerEntity.getId()));
  }
}
