package com.rekindle.book.store.domain.payment.valueobject;


import com.rekindle.book.store.domain.core.valueobject.BaseId;
import java.util.UUID;

public class CreditHistoryId extends BaseId<UUID> {

  public CreditHistoryId(UUID value) {
    super(value);
  }
}
