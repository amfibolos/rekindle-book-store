package com.rekindle.book.store.outbox;

public interface OutboxScheduler {

  void processOutboxMessage();
}
