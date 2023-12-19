package com.rekindle.book.store.customer.application.service.mapper;


import com.rekindle.book.store.customer.application.service.create.CreateCustomerCommand;
import com.rekindle.book.store.customer.application.service.create.CreateCustomerResponse;
import com.rekindle.book.store.domain.core.valueobject.CustomerId;
import com.rekindle.book.store.domain.customer.entity.Customer;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataMapper {

    public Customer createCustomerCommandToCustomer(CreateCustomerCommand createCustomerCommand) {
        return new Customer(new CustomerId(UUID.randomUUID()),
                createCustomerCommand.username(),
                createCustomerCommand.firstName(),
                createCustomerCommand.lastName());
    }

    public CreateCustomerResponse customerToCreateCustomerResponse(Customer customer, String message) {
        return new CreateCustomerResponse(customer.getId().getValue(), message);
    }
}
