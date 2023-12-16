package com.rekindle.book.store.bookstore.application.service.domain.ports.output.repository;


import com.rekindle.book.store.domain.bookstore.entity.Bookstore;
import java.util.Optional;

public interface BookstoreRepository {

  Optional<Bookstore> findBookstoreInformation(Bookstore bookstore);
}
