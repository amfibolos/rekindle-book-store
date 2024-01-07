package com.rekindle.book.store.bookstore.application.service.domain.ports.output.repository;


import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreCreateCommand;
import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreDto;
import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreProductCreateCommand;
import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreProductDto;
import com.rekindle.book.store.bookstore.application.service.domain.dto.ProductDto;
import com.rekindle.book.store.domain.bookstore.entity.Bookstore;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookstoreRepository {

  Optional<Bookstore> findBookstoreInformation(Bookstore bookstore);

  UUID registerNewBookstore(BookstoreCreateCommand bookstoreCreateCommand);

  UUID registerNewBookstoreProduct(BookstoreProductCreateCommand createCommand, UUID bookstoreId);

  BookstoreProductDto getProductById(UUID productId);

  void updateProductById(UUID productId, ProductDto productDto);

  void deleteProductById(UUID bookstoreId,UUID productId);

  BookstoreDto fetchBookstore(UUID bookstoreId);

  List<BookstoreDto> fetchAllBookstore();

  void updateBookstoreById(UUID bookstoreId, BookstoreCreateCommand createCommand);

  void deleteBookstoreById(UUID bookstoreId);

  List<BookstoreProductDto> getAllProducts();
}
