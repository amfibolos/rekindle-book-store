package com.rekindle.book.store.bookstore.orm.entity;


import com.rekindle.book.store.domain.core.valueobject.OrderApprovalStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_approval", schema = "bookstore")
@Entity
public class OrderApprovalEntity {

  @Id
  private UUID id;
  private UUID bookstoreId;
  private UUID orderId;
  @Enumerated(EnumType.STRING)
  private OrderApprovalStatus status;
}
