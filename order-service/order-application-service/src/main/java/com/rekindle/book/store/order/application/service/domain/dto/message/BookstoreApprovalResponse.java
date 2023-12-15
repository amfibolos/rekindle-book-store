package com.rekindle.book.store.order.application.service.domain.dto.message;


import com.rekindle.book.store.domain.core.valueobject.OrderApprovalStatus;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BookstoreApprovalResponse {

  private String id;
  private String sagaId;
  private String orderId;
  private String bookstoreId;
  private Instant createdAt;
  private OrderApprovalStatus orderApprovalStatus;
  private List<String> failureMessages;
}
