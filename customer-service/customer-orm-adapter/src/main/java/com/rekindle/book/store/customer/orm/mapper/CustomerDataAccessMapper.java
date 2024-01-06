package com.rekindle.book.store.customer.orm.mapper;


import com.rekindle.book.store.customer.application.service.dto.CustomerDto;
import com.rekindle.book.store.customer.orm.entity.CustomerEntity;
import com.rekindle.book.store.domain.core.valueobject.CustomerId;
import com.rekindle.book.store.domain.customer.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

  public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
    return new Customer(new CustomerId(customerEntity.getId()),
        customerEntity.getUsername(),
        customerEntity.getFirstName(),
        customerEntity.getLastName());
  }

  public CustomerDto customerEntityToCustomerDto(CustomerEntity customerEntity) {
    return new CustomerDto(customerEntity.getId(),
        customerEntity.getUsername(),
        customerEntity.getFirstName(),
        customerEntity.getLastName());
  }

  public CustomerEntity customerToCustomerEntity(Customer customer) {
    return CustomerEntity.builder()
        .id(customer.getId().getValue())
        .username(customer.getUsername())
        .firstName(customer.getFirstName())
        .lastName(customer.getLastName())
        .build();
  }

}
