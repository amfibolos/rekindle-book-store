package com.rekindle.book.store.bookstore.messaging.mapper;


import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreApprovalRequest;
import com.rekindle.book.store.domain.bookstore.entity.Product;
import com.rekindle.book.store.domain.bookstore.event.OrderApprovedEvent;
import com.rekindle.book.store.domain.bookstore.event.OrderRejectedEvent;
import com.rekindle.book.store.domain.core.valueobject.BookstoreOrderStatus;
import com.rekindle.book.store.domain.core.valueobject.ProductId;
import com.rekindle.book.store.kafka.avro.model.BookstoreApprovalRequestAvroModel;
import com.rekindle.book.store.kafka.avro.model.BookstoreApprovalResponseAvroModel;
import com.rekindle.book.store.kafka.avro.model.OrderApprovalStatus;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BookstoreMessagingDataMapper {

  public BookstoreApprovalResponseAvroModel
  orderApprovedEventToBookstoreApprovalResponseAvroModel(OrderApprovedEvent orderApprovedEvent) {
    return BookstoreApprovalResponseAvroModel.newBuilder()
        .setId(UUID.randomUUID().toString())
        .setSagaId("")
        .setOrderId(orderApprovedEvent.getOrderApproval().getOrderId().getValue().toString())
        .setBookstoreId(orderApprovedEvent.getBookstoreId().getValue().toString())
        .setCreatedAt(orderApprovedEvent.getCreatedAt().toInstant())
        .setOrderApprovalStatus(OrderApprovalStatus.valueOf(orderApprovedEvent.
            getOrderApproval().getApprovalStatus().name()))
        .setFailureMessages(orderApprovedEvent.getFailureMessages())
        .build();
  }

  public BookstoreApprovalResponseAvroModel
  orderRejectedEventToBookstoreApprovalResponseAvroModel(OrderRejectedEvent orderRejectedEvent) {
    return BookstoreApprovalResponseAvroModel.newBuilder()
        .setId(UUID.randomUUID().toString())
        .setSagaId("")
        .setOrderId(orderRejectedEvent.getOrderApproval().getOrderId().getValue().toString())
        .setBookstoreId(orderRejectedEvent.getBookstoreId().getValue().toString())
        .setCreatedAt(orderRejectedEvent.getCreatedAt().toInstant())
        .setOrderApprovalStatus(OrderApprovalStatus.valueOf(orderRejectedEvent.
            getOrderApproval().getApprovalStatus().name()))
        .setFailureMessages(orderRejectedEvent.getFailureMessages())
        .build();
  }

  public BookstoreApprovalRequest
  bookstoreApprovalRequestAvroModelToBookstoreApproval(
      BookstoreApprovalRequestAvroModel
          bookstoreApprovalRequestAvroModel
  ) {
    return BookstoreApprovalRequest.builder()
        .id(bookstoreApprovalRequestAvroModel.getId())
        .sagaId(bookstoreApprovalRequestAvroModel.getSagaId())
        .bookstoreId(bookstoreApprovalRequestAvroModel.getBookstoreId())
        .orderId(bookstoreApprovalRequestAvroModel.getOrderId())
        .bookstoreOrderStatus(BookstoreOrderStatus.valueOf(bookstoreApprovalRequestAvroModel
            .getBookstoreOrderStatus().name()))
        .products(bookstoreApprovalRequestAvroModel.getProducts()
            .stream().map(avroModel ->
                Product.builder()
                    .productId(new ProductId(UUID.fromString(avroModel.getId())))
                    .quantity(avroModel.getQuantity())
                    .build())
            .collect(Collectors.toList()))
        .price(bookstoreApprovalRequestAvroModel.getPrice())
        .createdAt(bookstoreApprovalRequestAvroModel.getCreatedAt())
        .build();
  }
}
