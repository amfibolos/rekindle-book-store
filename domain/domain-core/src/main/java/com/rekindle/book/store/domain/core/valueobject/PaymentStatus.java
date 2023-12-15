package com.rekindle.book.store.domain.core.valueobject;

import com.rekindle.book.store.domain.common.valueobject.ValueObject;

public enum PaymentStatus implements ValueObject {
  COMPLETED, CANCELLED, FAILED
}
