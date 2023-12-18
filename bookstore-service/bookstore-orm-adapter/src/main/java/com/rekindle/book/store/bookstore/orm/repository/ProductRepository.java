package com.rekindle.book.store.bookstore.orm.repository;

import com.rekindle.book.store.bookstore.orm.entity.ProductEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

}
