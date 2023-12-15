package com.rekindle.book.store.domain.core.exception;


import com.rekindle.book.store.domain.common.exception.DomainException;

public class OrderDomainException extends DomainException {

  public OrderDomainException(String message) {
    super(message);
  }

  public OrderDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
