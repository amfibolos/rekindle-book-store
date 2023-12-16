package com.rekindle.book.store.order.orm.bookstore.adapter;


import com.rekindle.book.store.domain.order.entity.Bookstore;
import com.rekindle.book.store.order.application.service.domain.ports.output.repository.BookstoreRepository;
import com.rekindle.book.store.order.orm.bookstore.entity.BookstoreEntity;
import com.rekindle.book.store.order.orm.bookstore.mapper.BookstoreDataAccessMapper;
import com.rekindle.book.store.order.orm.bookstore.repository.BookstoreJpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class BookstoreRepositoryImpl implements BookstoreRepository {

  private final BookstoreJpaRepository bookstoreJpaRepository;
  private final BookstoreDataAccessMapper bookstoreDataAccessMapper;

  public BookstoreRepositoryImpl(
      BookstoreJpaRepository bookstoreJpaRepository,
      BookstoreDataAccessMapper bookstoreDataAccessMapper
  ) {
    this.bookstoreJpaRepository = bookstoreJpaRepository;
    this.bookstoreDataAccessMapper = bookstoreDataAccessMapper;
  }

  @Override
  public Optional<Bookstore> findBookstoreInformation(Bookstore bookstore) {
    List<UUID> bookstoreProducts =
        bookstoreDataAccessMapper.bookstoreToBookstoreProducts(bookstore);
    Optional<List<BookstoreEntity>> bookstoreEntities = bookstoreJpaRepository
        .findByBookstoreIdAndProductIdIn(bookstore.getId().getValue(),
            bookstoreProducts);
    return bookstoreEntities.map(bookstoreDataAccessMapper::bookstoreEntityToBookstore);
  }
}
