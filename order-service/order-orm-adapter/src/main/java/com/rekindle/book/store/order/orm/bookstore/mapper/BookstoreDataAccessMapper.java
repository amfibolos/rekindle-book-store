package com.rekindle.book.store.order.orm.bookstore.mapper;


import com.rekindle.book.store.domain.core.entity.Bookstore;
import com.rekindle.book.store.domain.core.entity.Product;
import com.rekindle.book.store.domain.core.valueobject.BookstoreId;
import com.rekindle.book.store.domain.core.valueobject.Money;
import com.rekindle.book.store.domain.core.valueobject.ProductId;
import com.rekindle.book.store.order.orm.bookstore.entity.BookstoreEntity;
import com.rekindle.book.store.order.orm.bookstore.exception.BookstoreDataAccessException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BookstoreDataAccessMapper {

  public List<UUID> bookstoreToBookstoreProducts(Bookstore bookstore) {
    return bookstore.getProducts().stream()
        .map(product -> product.getId().getValue())
        .collect(Collectors.toList());
  }

  public Bookstore bookstoreEntityToBookstore(List<BookstoreEntity> bookstoreEntities) {
    BookstoreEntity bookstoreEntity =
        bookstoreEntities.stream().findFirst().orElseThrow(() ->
            new BookstoreDataAccessException("Bookstore could not be found!"));

    List<Product> bookstoreProducts = bookstoreEntities.stream().map(entity ->
        new Product(new ProductId(entity.getProductId()), entity.getProductName(),
            new Money(entity.getProductPrice()))).toList();

    return Bookstore.builder()
        .bookstoreId(new BookstoreId(bookstoreEntity.getBookstoreId()))
        .products(bookstoreProducts)
        .active(bookstoreEntity.getBookstoreActive())
        .build();
  }
}
