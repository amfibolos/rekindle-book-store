package com.rekindle.book.store.domain.core.valueobject;

import com.rekindle.book.store.domain.common.valueobject.ValueObject;

public enum OrderStatus implements ValueObject {
  PENDING, PAID, APPROVED, CANCELLING, CANCELLED
}
