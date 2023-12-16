package com.rekindle.book.store.domain.payment.exception;


import com.rekindle.book.store.domain.core.exception.DomainException;

public class PaymentDomainException extends DomainException {

  public PaymentDomainException(String message) {
    super(message);
  }

  public PaymentDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
