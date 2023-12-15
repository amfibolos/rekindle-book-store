package com.rekindle.book.store.order.orm.bookstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
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
@IdClass(BookstoreEntityId.class)
@Table(name = "order_bookstore_m_view", schema = "bookstore")
@Entity
public class BookstoreEntity {

  @Id
  private UUID bookstoreId;
  @Id
  private UUID productId;
  private String bookstoreName;
  private Boolean bookstoreActive;
  private String productName;
  private BigDecimal productPrice;
  private Boolean productAvailable;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookstoreEntity that = (BookstoreEntity) o;
    return bookstoreId.equals(that.bookstoreId) && productId.equals(that.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bookstoreId, productId);
  }
}
