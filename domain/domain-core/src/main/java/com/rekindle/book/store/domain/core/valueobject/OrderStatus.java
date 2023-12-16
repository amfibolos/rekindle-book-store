package com.rekindle.book.store.domain.core.valueobject;

public enum OrderStatus implements ValueObject {
  PENDING, PAID, APPROVED, CANCELLING, CANCELLED
}
