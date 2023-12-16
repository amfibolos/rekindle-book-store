package com.rekindle.book.store.domain.core.valueobject;

import java.util.UUID;

public class BookstoreId extends BaseId<UUID> implements ValueObject {

  public BookstoreId(UUID value) {
    super(value);
  }
}
