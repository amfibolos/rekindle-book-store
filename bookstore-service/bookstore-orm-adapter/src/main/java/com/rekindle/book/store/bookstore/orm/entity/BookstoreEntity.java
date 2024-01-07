package com.rekindle.book.store.bookstore.orm.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
  @NotNull
  private UUID bookstoreId;
  @Id
  @NotNull
  private UUID productId;
  @NotBlank
  @NotNull
  private String bookstoreName;
  @NotNull
  private Boolean bookstoreActive;
  @NotBlank
  @NotNull
  private String productName;
  @NotNull
  private BigDecimal productPrice;
  @NotNull
  private Boolean productAvailable;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookstoreEntityId that = (BookstoreEntityId) o;
    return bookstoreId.equals(that.getBookstoreId()) && productId.equals(that.getProductId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(bookstoreId, productId);
  }
}
