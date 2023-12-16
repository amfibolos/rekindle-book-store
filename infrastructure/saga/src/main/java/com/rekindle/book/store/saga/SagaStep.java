package com.rekindle.book.store.saga;

public interface SagaStep<T> {

  void process(T data);

  void rollback(T data);
}
