package com.rekindle.book.store.order.application.service.domain.ports.output.repository;


import com.rekindle.book.store.domain.core.entity.Bookstore;
import java.util.Optional;

public interface BookstoreRepository {

  Optional<Bookstore> findBookstoreInformation(Bookstore bookStore);
}
