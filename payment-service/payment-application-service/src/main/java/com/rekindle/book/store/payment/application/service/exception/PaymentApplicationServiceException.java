package com.rekindle.book.store.payment.application.service.exception;


import com.rekindle.book.store.domain.core.exception.DomainException;

public class PaymentApplicationServiceException extends DomainException {

  public PaymentApplicationServiceException(String message) {
    super(message);
  }

  public PaymentApplicationServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
