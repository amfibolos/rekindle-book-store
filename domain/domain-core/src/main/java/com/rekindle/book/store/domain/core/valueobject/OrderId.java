package com.rekindle.book.store.domain.core.valueobject;

import java.util.UUID;

public class OrderId extends BaseId<UUID> implements ValueObject {

  public OrderId(UUID value) {
    super(value);
  }
}
