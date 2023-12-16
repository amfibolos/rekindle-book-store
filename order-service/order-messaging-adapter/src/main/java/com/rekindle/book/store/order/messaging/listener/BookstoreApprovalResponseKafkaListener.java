package com.rekindle.book.store.order.messaging.listener;


import com.rekindle.book.store.domain.core.utilities.Delimiter;
import com.rekindle.book.store.kafka.avro.model.BookstoreApprovalResponseAvroModel;
import com.rekindle.book.store.kafka.avro.model.OrderApprovalStatus;
import com.rekindle.book.store.kafka.consumer.KafkaConsumer;
import com.rekindle.book.store.order.application.service.domain.ports.input.message.listener.bookstoreapproval.BookstoreApprovalResponseMessageListener;
import com.rekindle.book.store.order.messaging.mapper.OrderMessagingDataMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookstoreApprovalResponseKafkaListener implements
    KafkaConsumer<BookstoreApprovalResponseAvroModel> {

  private final BookstoreApprovalResponseMessageListener bookstoreApprovalResponseMessageListener;
  private final OrderMessagingDataMapper orderMessagingDataMapper;

  public BookstoreApprovalResponseKafkaListener(
      BookstoreApprovalResponseMessageListener
          bookstoreApprovalResponseMessageListener,
      OrderMessagingDataMapper orderMessagingDataMapper
  ) {
    this.bookstoreApprovalResponseMessageListener = bookstoreApprovalResponseMessageListener;
    this.orderMessagingDataMapper = orderMessagingDataMapper;
  }

  @Override
  @KafkaListener(id = "${kafka-consumer-config.bookstore-approval-consumer-group-id}",
      topics = "${order-service-topics.bookstore-approval-response-topic-name}")
  public void receive(
      @Payload List<BookstoreApprovalResponseAvroModel> messages,
      @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
      @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
      @Header(KafkaHeaders.OFFSET) List<Long> offsets
  ) {
    log.info(
        "{} number of bookstore approval responses received with keys {}, partitions {} and offsets {}",
        messages.size(),
        keys.toString(),
        partitions.toString(),
        offsets.toString());

    messages.forEach(bookstoreApprovalResponseAvroModel -> {
      if (OrderApprovalStatus.APPROVED
          == bookstoreApprovalResponseAvroModel.getOrderApprovalStatus()) {
        log.info("Processing approved order for order id: {}",
            bookstoreApprovalResponseAvroModel.getOrderId());
        bookstoreApprovalResponseMessageListener.orderApproved(orderMessagingDataMapper
            .approvalResponseAvroModelToApprovalResponse(bookstoreApprovalResponseAvroModel));
      } else if (OrderApprovalStatus.REJECTED
          == bookstoreApprovalResponseAvroModel.getOrderApprovalStatus()) {
        log.info("Processing rejected order for order id: {}, with failure messages: {}",
            bookstoreApprovalResponseAvroModel.getOrderId(),
            String.join(Delimiter.COMMA,
                bookstoreApprovalResponseAvroModel.getFailureMessages()));
        bookstoreApprovalResponseMessageListener.orderRejected(orderMessagingDataMapper
            .approvalResponseAvroModelToApprovalResponse(bookstoreApprovalResponseAvroModel));
      }
    });

  }
}
