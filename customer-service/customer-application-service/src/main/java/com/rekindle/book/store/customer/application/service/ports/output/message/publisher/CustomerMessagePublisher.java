package com.rekindle.book.store.customer.application.service.ports.output.message.publisher;


import com.rekindle.book.store.domain.customer.event.CustomerCreatedEvent;

public interface CustomerMessagePublisher {

    void publish(CustomerCreatedEvent customerCreatedEvent);

}