package com.rekindle.book.store.saga;

import com.rekindle.book.store.domain.core.event.DomainEvent;

public interface SagaStep<T, S extends DomainEvent, U extends DomainEvent> {

  S process(T data);

  U rollback(T data);
}
