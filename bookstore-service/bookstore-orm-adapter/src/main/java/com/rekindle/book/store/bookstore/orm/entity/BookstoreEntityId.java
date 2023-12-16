package com.rekindle.book.store.bookstore.orm.entity;

import java.io.Serializable;
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
public class BookstoreEntityId implements Serializable {

  private UUID bookstoreId;
  private UUID productId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookstoreEntityId that = (BookstoreEntityId) o;
    return bookstoreId.equals(that.bookstoreId) && productId.equals(that.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bookstoreId, productId);
  }
}
