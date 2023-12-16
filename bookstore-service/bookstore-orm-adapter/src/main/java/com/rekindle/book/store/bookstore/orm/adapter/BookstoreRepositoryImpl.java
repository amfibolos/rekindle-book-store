package com.rekindle.book.store.bookstore.orm.adapter;


import com.rekindle.book.store.bookstore.application.service.domain.ports.output.repository.BookstoreRepository;
import com.rekindle.book.store.bookstore.orm.entity.BookstoreEntity;
import com.rekindle.book.store.bookstore.orm.mapper.BookstoreDataAccessMapper;
import com.rekindle.book.store.bookstore.orm.repository.BookstoreJpaRepository;
import com.rekindle.book.store.domain.bookstore.entity.Bookstore;
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
    List<UUID> bookstoreProductIds =
        bookstoreDataAccessMapper.bookstoreToBookstoreProducts(bookstore);
    Optional<List<BookstoreEntity>> bookstoreEntities = bookstoreJpaRepository
        .findByBookstoreIdAndProductIdIn(bookstore.getId().getValue(),
            bookstoreProductIds);
    return bookstoreEntities.map(bookstoreDataAccessMapper::bookstoreEntityToBookstore);
  }
}
