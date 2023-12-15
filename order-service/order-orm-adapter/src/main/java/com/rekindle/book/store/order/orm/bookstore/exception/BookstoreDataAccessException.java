package com.rekindle.book.store.order.orm.bookstore.exception;

public class BookstoreDataAccessException extends RuntimeException {

  public BookstoreDataAccessException() {
  }

  public BookstoreDataAccessException(String message) {
    super(message);
  }

  public BookstoreDataAccessException(String message, Throwable cause) {
    super(message, cause);
  }

  public BookstoreDataAccessException(Throwable cause) {
    super(cause);
  }

  public BookstoreDataAccessException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace
  ) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
