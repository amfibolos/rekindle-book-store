package com.rekindle.book.store.bookstore.application.service.domain.mapper;


import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreApprovalRequest;
import com.rekindle.book.store.domain.bookstore.entity.Bookstore;
import com.rekindle.book.store.domain.bookstore.entity.OrderDetail;
import com.rekindle.book.store.domain.bookstore.entity.Product;
import com.rekindle.book.store.domain.core.valueobject.BookstoreId;
import com.rekindle.book.store.domain.core.valueobject.Money;
import com.rekindle.book.store.domain.core.valueobject.OrderId;
import com.rekindle.book.store.domain.core.valueobject.OrderStatus;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BookstoreDataMapper {

  public Bookstore bookstoreApprovalRequestToBookstore(
      BookstoreApprovalRequest
          bookstoreApprovalRequest
  ) {
    return Bookstore.builder()
        .bookstoreId(new BookstoreId(UUID.fromString(bookstoreApprovalRequest.getBookstoreId())))
        .orderDetail(OrderDetail.builder()
            .orderId(new OrderId(UUID.fromString(bookstoreApprovalRequest.getOrderId())))
            .products(bookstoreApprovalRequest.getProducts().stream().map(
                    product -> Product.builder()
                        .productId(product.getId())
                        .quantity(product.getQuantity())
                        .build())
                .collect(Collectors.toList()))
            .totalAmount(new Money(bookstoreApprovalRequest.getPrice()))
            .orderStatus(
                OrderStatus.valueOf(bookstoreApprovalRequest.getBookstoreOrderStatus().name()))
            .build())
        .build();
  }
}
