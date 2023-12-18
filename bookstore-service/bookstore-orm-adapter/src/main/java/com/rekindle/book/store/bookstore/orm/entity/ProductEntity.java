package com.rekindle.book.store.bookstore.orm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
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
@Table(name = "products", schema = "bookstore")
@Entity
public class ProductEntity {

  @Id
  private UUID id;
  private String name;
  private BigDecimal price;
  private Boolean available;
  @Builder.Default
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "productEntities")
  private Set<BookstoreIdentityEntity> bookstoreIdentityEntities = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductEntity that = (ProductEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name)
        && Objects.equals(price, that.price) && Objects.equals(available,
        that.available);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, price, available);
  }
}
