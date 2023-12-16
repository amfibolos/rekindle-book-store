package com.rekindle.book.store.domain.order.valueobject;


import com.rekindle.book.store.domain.core.valueobject.BaseId;
import com.rekindle.book.store.domain.core.valueobject.ValueObject;

public class OrderItemId extends BaseId<Long> implements ValueObject {

  public OrderItemId(Long value) {
    super(value);
  }
}
