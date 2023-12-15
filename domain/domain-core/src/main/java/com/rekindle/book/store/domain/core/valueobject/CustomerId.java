package com.rekindle.book.store.domain.core.valueobject;

import com.rekindle.book.store.domain.common.valueobject.BaseId;
import com.rekindle.book.store.domain.common.valueobject.ValueObject;
import java.util.UUID;

public class CustomerId extends BaseId<UUID> implements ValueObject {

  public CustomerId(UUID value) {
    super(value);
  }
}
