package com.rekindle.book.store.kafka.producer;

import java.util.function.BiConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaMessageHelper {

  public <T, U> BiConsumer<SendResult<String, T>, Throwable>
  getKafkaCallback(String responseTopicName, T avroModel, String orderId, String avroModelName) {
    return (result, exception) -> {
      if (exception == null) {
        RecordMetadata metadata = result.getRecordMetadata();
        log.info("Received successful response from Kafka for order id: {}" +
                " Topic: {} Partition: {} Offset: {} Timestamp: {}",
            orderId,
            metadata.topic(),
            metadata.partition(),
            metadata.offset(),
            metadata.timestamp());
      } else {
        log.error("Error while sending " + avroModelName +
            " message {} to topic {}", avroModel.toString(), responseTopicName, exception);
      }
    };
  }
}
