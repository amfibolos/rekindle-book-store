package com.rekindle.book.store.domain.core.event;

public interface DomainEvent<T> {

  void fire();
}
