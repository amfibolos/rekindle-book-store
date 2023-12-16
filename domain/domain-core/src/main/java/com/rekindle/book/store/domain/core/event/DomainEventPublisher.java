package com.rekindle.book.store.domain.core.event;

public interface DomainEventPublisher<T extends DomainEvent> {

  void publish(T domainEvent);
}
