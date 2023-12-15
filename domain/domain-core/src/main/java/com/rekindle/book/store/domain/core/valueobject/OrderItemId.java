package com.rekindle.book.store.domain.core.valueobject;


import com.rekindle.book.store.domain.common.valueobject.BaseId;
import com.rekindle.book.store.domain.common.valueobject.ValueObject;

public class OrderItemId extends BaseId<Long> implements ValueObject {

  public OrderItemId(Long value) {
    super(value);
  }
}
