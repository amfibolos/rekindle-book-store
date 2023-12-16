package com.rekindle.book.store.saga;

public enum SagaStatus {
  STARTED,
  FAILED,
  SUCCEEDED,
  PROCESSING,
  COMPENSATING,
  COMPENSATED
}
