package com.rekindle.book.store.bookstore.orm.exception;

public class ProductEntityNotFoundException extends RuntimeException {

  public ProductEntityNotFoundException() {
  }

  public ProductEntityNotFoundException(String message) {
    super(message);
  }

  public ProductEntityNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ProductEntityNotFoundException(Throwable cause) {
    super(cause);
  }

  public ProductEntityNotFoundException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace
  ) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
