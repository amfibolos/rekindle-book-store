package com.rekindle.book.store.domain.bookstore.exception;


import com.rekindle.book.store.domain.core.exception.DomainException;

public class BookstoreDomainException extends DomainException {

  public BookstoreDomainException(String message) {
    super(message);
  }

  public BookstoreDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
