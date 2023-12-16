package com.rekindle.book.store.domain.bookstore.exception;


import com.rekindle.book.store.domain.core.exception.DomainException;

public class BookstoreNotFoundException extends DomainException {

  public BookstoreNotFoundException(String message) {
    super(message);
  }

  public BookstoreNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
