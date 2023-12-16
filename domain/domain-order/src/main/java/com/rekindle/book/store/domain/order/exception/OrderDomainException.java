package com.rekindle.book.store.domain.order.exception;


import com.rekindle.book.store.domain.core.exception.DomainException;

public class OrderDomainException extends DomainException {

  public OrderDomainException(String message) {
    super(message);
  }

  public OrderDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
