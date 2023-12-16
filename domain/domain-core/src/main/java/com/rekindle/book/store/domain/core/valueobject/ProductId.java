package com.rekindle.book.store.domain.core.valueobject;

import java.util.UUID;

public class ProductId extends BaseId<UUID> implements ValueObject {

  public ProductId(UUID value) {
    super(value);
  }
}
