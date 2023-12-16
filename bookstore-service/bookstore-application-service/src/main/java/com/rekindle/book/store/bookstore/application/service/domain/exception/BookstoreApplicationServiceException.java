package com.rekindle.book.store.bookstore.application.service.domain.exception;


import com.rekindle.book.store.domain.core.exception.DomainException;

public class BookstoreApplicationServiceException extends DomainException {

  public BookstoreApplicationServiceException(String message) {
    super(message);
  }

  public BookstoreApplicationServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
