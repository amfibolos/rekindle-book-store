package com.rekindle.book.store.bookstore.application.service.domain.dto;


import com.rekindle.book.store.domain.bookstore.entity.Product;
import com.rekindle.book.store.domain.core.valueobject.BookstoreOrderStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BookstoreApprovalRequest {

  private String id;
  private String sagaId;
  private String bookstoreId;
  private String orderId;
  private BookstoreOrderStatus bookstoreOrderStatus;
  private List<Product> products;
  private BigDecimal price;
  private Instant createdAt;
}
