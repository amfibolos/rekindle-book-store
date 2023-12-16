package com.rekindle.book.store.domain.core.valueobject;

import java.util.UUID;

public class CustomerId extends BaseId<UUID> implements ValueObject {

  public CustomerId(UUID value) {
    super(value);
  }
}
