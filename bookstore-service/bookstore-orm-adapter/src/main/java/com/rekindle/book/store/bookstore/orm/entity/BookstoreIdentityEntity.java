package com.rekindle.book.store.bookstore.orm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "bookstores", schema = "bookstore")
@Entity
public class BookstoreIdentityEntity {

  @Id
  @NotNull
  private UUID id;
  @NotBlank
  @NotNull
  private String name;
  @NotNull
  private Boolean active;
  @Builder.Default
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      schema = "bookstore",
      name = "bookstore_products",
      joinColumns = @JoinColumn(name = "bookstore_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id")
  )
  private Set<ProductEntity> productEntities = new HashSet<>();

  public void addProductEntity(ProductEntity productEntity) {
    this.productEntities.add(productEntity);
    productEntity.getBookstoreIdentityEntities().add(this);
  }

  public void removeProductEntity(ProductEntity productEntity) {
    this.productEntities.remove(productEntity);
    productEntity.getBookstoreIdentityEntities().remove(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookstoreIdentityEntity that = (BookstoreIdentityEntity) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
