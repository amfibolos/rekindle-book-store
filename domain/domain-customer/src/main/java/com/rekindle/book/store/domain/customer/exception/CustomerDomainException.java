package com.rekindle.book.store.domain.customer.exception;


import com.rekindle.book.store.domain.core.exception.DomainException;

public class CustomerDomainException extends DomainException {

    public CustomerDomainException(String message) {
        super(message);
    }
}
