package com.rekindle.book.store.domain.bookstore.valueobject;


import com.rekindle.book.store.domain.core.valueobject.BaseId;
import java.util.UUID;

public class OrderApprovalId extends BaseId<UUID> {

  public OrderApprovalId(UUID value) {
    super(value);
  }
}
