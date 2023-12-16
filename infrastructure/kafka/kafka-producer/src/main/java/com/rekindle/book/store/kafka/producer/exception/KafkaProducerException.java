package com.rekindle.book.store.kafka.producer.exception;

public class KafkaProducerException extends RuntimeException {

  public KafkaProducerException(String message) {
    super(message);
  }
}
