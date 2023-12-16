package com.rekindle.book.store.domain.order.exception;


import com.rekindle.book.store.domain.core.exception.DomainException;

public class OrderNotFoundException extends DomainException {

  public OrderNotFoundException(String message) {
    super(message);
  }

  public OrderNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
