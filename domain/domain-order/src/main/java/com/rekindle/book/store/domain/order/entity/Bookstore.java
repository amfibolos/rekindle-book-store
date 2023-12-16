package com.rekindle.book.store.domain.order.entity;


import com.rekindle.book.store.domain.core.entity.AggregateRoot;
import com.rekindle.book.store.domain.core.valueobject.BookstoreId;
import java.util.List;

public class Bookstore extends AggregateRoot<BookstoreId> {

  private final List<Product> products;
  private final boolean active;

  private Bookstore(Builder builder) {
    super.setId(builder.bookstoreId);
    products = builder.products;
    active = builder.active;
  }

  public static Builder builder() {
    return new Builder();
  }

  public List<Product> getProducts() {
    return products;
  }

  public boolean isActive() {
    return active;
  }

  public static final class Builder {

    private BookstoreId bookstoreId;
    private List<Product> products;
    private boolean active;

    private Builder() {
    }

    public Builder bookstoreId(BookstoreId val) {
      bookstoreId = val;
      return this;
    }

    public Builder products(List<Product> val) {
      products = val;
      return this;
    }

    public Builder active(boolean val) {
      active = val;
      return this;
    }

    public Bookstore build() {
      return new Bookstore(this);
    }
  }
}
