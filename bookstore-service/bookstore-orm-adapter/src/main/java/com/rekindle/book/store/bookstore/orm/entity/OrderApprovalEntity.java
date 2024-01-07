package com.rekindle.book.store.bookstore.orm.entity;


import com.rekindle.book.store.domain.core.valueobject.OrderApprovalStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
  @NotNull
  private UUID id;
  @NotNull
  private UUID bookstoreId;
  @NotNull
  private UUID orderId;
  @NotBlank
  @NotNull
  @Enumerated(EnumType.STRING)
  private OrderApprovalStatus status;
}
