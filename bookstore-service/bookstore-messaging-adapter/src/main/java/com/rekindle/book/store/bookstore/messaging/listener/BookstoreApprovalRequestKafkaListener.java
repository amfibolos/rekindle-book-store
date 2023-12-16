package com.rekindle.book.store.bookstore.messaging.listener;


import com.rekindle.book.store.bookstore.application.service.domain.ports.input.message.listener.BookstoreApprovalRequestMessageListener;
import com.rekindle.book.store.bookstore.messaging.mapper.BookstoreMessagingDataMapper;
import com.rekindle.book.store.kafka.avro.model.BookstoreApprovalRequestAvroModel;
import com.rekindle.book.store.kafka.consumer.KafkaConsumer;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookstoreApprovalRequestKafkaListener implements
    KafkaConsumer<BookstoreApprovalRequestAvroModel> {

  private final BookstoreApprovalRequestMessageListener bookstoreApprovalRequestMessageListener;
  private final BookstoreMessagingDataMapper bookstoreMessagingDataMapper;

  public BookstoreApprovalRequestKafkaListener(
      BookstoreApprovalRequestMessageListener
          bookstoreApprovalRequestMessageListener,
      BookstoreMessagingDataMapper
          bookstoreMessagingDataMapper
  ) {
    this.bookstoreApprovalRequestMessageListener = bookstoreApprovalRequestMessageListener;
    this.bookstoreMessagingDataMapper = bookstoreMessagingDataMapper;
  }

  @Override
  @KafkaListener(id = "${kafka-consumer-config.bookstore-approval-consumer-group-id}",
      topics = "${bookstore-service-topics.bookstore-approval-request-topic-name}")
  public void receive(
      @Payload List<BookstoreApprovalRequestAvroModel> messages,
      @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
      @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
      @Header(KafkaHeaders.OFFSET) List<Long> offsets
  ) {
    log.info(
        "{} number of orders approval requests received with keys {}, partitions {} and offsets {}"
            +
            ", sending for bookstore approval",
        messages.size(),
        keys.toString(),
        partitions.toString(),
        offsets.toString());

    messages.forEach(bookstoreApprovalRequestAvroModel -> {
      log.info("Processing order approval for order id: {}",
          bookstoreApprovalRequestAvroModel.getOrderId());
      bookstoreApprovalRequestMessageListener.approveOrder(bookstoreMessagingDataMapper.
          bookstoreApprovalRequestAvroModelToBookstoreApproval(
              bookstoreApprovalRequestAvroModel));
    });
  }

}
