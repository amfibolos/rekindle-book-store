package com.rekindle.book.store.order.orm.bookstore.repository;


import com.rekindle.book.store.order.orm.bookstore.entity.BookstoreEntity;
import com.rekindle.book.store.order.orm.bookstore.entity.BookstoreEntityId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookstoreJpaRepository extends JpaRepository<BookstoreEntity, BookstoreEntityId> {

  Optional<List<BookstoreEntity>> findByBookstoreIdAndProductIdIn(
      UUID bookstoreId, List<UUID> productIds
  );
}
