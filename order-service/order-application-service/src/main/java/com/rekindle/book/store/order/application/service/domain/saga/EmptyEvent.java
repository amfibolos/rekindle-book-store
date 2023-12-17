package com.rekindle.book.store.order.application.service.domain.saga;

import com.rekindle.book.store.domain.core.event.DomainEvent;

public final class EmptyEvent implements DomainEvent<Void> {

  public static final EmptyEvent INSTANCE = new EmptyEvent();

  private EmptyEvent() {
  }

  @Override
  public void fire() {

  }
}
