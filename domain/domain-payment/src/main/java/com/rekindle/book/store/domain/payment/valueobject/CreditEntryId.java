package com.rekindle.book.store.domain.payment.valueobject;


import com.rekindle.book.store.domain.core.valueobject.BaseId;
import java.util.UUID;

public class CreditEntryId extends BaseId<UUID> {

  public CreditEntryId(UUID value) {
    super(value);
  }
}
