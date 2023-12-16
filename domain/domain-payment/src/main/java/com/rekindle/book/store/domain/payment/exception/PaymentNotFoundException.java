package com.rekindle.book.store.domain.payment.exception;


import com.rekindle.book.store.domain.core.exception.DomainException;

public class PaymentNotFoundException extends DomainException {

  public PaymentNotFoundException(String message) {
    super(message);
  }

  public PaymentNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
