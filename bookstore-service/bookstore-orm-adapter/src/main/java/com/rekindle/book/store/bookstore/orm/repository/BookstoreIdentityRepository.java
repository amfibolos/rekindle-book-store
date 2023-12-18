package com.rekindle.book.store.bookstore.orm.repository;

import com.rekindle.book.store.bookstore.orm.entity.BookstoreIdentityEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookstoreIdentityRepository extends JpaRepository<BookstoreIdentityEntity, UUID> {

}
