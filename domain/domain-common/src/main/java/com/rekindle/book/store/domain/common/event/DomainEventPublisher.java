package com.rekindle.book.store.domain.common.event;

public interface DomainEventPublisher<T extends DomainEvent> {

  void publish(T domainEvent);
}
