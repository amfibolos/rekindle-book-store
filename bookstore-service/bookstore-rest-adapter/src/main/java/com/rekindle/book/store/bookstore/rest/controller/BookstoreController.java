package com.rekindle.book.store.bookstore.rest.controller;

import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreCreateCommand;
import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreDto;
import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreProductCreateCommand;
import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreProductDto;
import com.rekindle.book.store.bookstore.application.service.domain.dto.ProductDto;
import com.rekindle.book.store.bookstore.application.service.domain.ports.output.repository.BookstoreRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/bookstores", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BookstoreController {

  private final BookstoreRepository bookstoreRepository;

  public BookstoreController(BookstoreRepository bookstoreRepository) {
    this.bookstoreRepository = bookstoreRepository;
  }

  @Operation(
      summary = "Register new Bookstore in Rekindle Bookstore Network",
      description = "REST API POST method to add new bookstore to Rekindle Network")
  @PostMapping
  public ResponseEntity<UUID> registerBookstore(
      @Valid @RequestBody BookstoreCreateCommand createCommand
  ) {
    UUID id = bookstoreRepository.registerNewBookstore(createCommand);
    log.info("Bookstore {} has been registered with id {}", createCommand.name(), id);
    return ResponseEntity.status(HttpStatus.CREATED).body(id);
  }

  @Operation(
      summary = "Get Bookstore by id from Rekindle Bookstore Network",
      description = "REST API GET method to fetch bookstore from Rekindle Network")
  @GetMapping("/{bookstoreId}")
  public ResponseEntity<BookstoreDto> getBookstoreById(
      @PathVariable("bookstoreId") UUID bookstoreId
  ) {
    BookstoreDto bookstore = bookstoreRepository.fetchBookstore(bookstoreId);
    log.info("Bookstore {} has been found", bookstore.id());
    return ResponseEntity.ok(bookstore);
  }

  @Operation(
      summary = "Get all Bookstores from Rekindle Bookstore Network",
      description = "REST API GET method to fetch all bookstores from Rekindle Network")
  @GetMapping
  public ResponseEntity<List<BookstoreDto>> getAllBookstores() {
    List<BookstoreDto> bookstores = bookstoreRepository.fetchAllBookstore();
    log.info("Bookstores have been found");
    return ResponseEntity.ok(bookstores);
  }

  @Operation(
      summary = "Update Bookstore by id from Rekindle Bookstore Network",
      description = "REST API PUT method to update bookstore from Rekindle Network")
  @PutMapping("/{bookstoreId}")
  public ResponseEntity<Void> updateBookstoreById(
      @PathVariable("bookstoreId") UUID bookstoreId,
      @Valid @RequestBody BookstoreCreateCommand createCommand
  ) {
    bookstoreRepository.updateBookstoreById(bookstoreId, createCommand);
    log.info("Bookstore with id {} has been updated", bookstoreId);
    return ResponseEntity.noContent().build();
  }

  @Operation(
      summary = "Delete Bookstore by id from Rekindle Bookstore Network",
      description = "REST API DELETE method to delete bookstore from Rekindle Network")
  @DeleteMapping("/{bookstoreId}")
  public ResponseEntity<Void> deleteBookstoreById(
      @PathVariable("bookstoreId") UUID bookstoreId
  ) {
    bookstoreRepository.deleteBookstoreById(bookstoreId);
    log.info("Bookstore with id {} has been deleted", bookstoreId);
    return ResponseEntity.noContent().build();
  }

  @Operation(
      summary = "Register new product under specific Bookstore in Rekindle Bookstore Network",
      description = "REST API POST method to add new product under specific Bookstore")
  @PostMapping("/{bookstoreId}/product")
  public ResponseEntity<UUID> registerBookstoreProduct(
      @PathVariable("bookstoreId") UUID bookstoreId,
      @Valid @RequestBody BookstoreProductCreateCommand createCommand
  ) {
    UUID productId = bookstoreRepository.registerNewBookstoreProduct(createCommand, bookstoreId);
    log.info("New product {} has been registered under bookstore {}", createCommand.name(),
        bookstoreId);
    return ResponseEntity.status(HttpStatus.CREATED).body(productId);
  }

  @Operation(
      summary = "Get Product information by its ID",
      description = "REST API GET method to fetch single product")
  @GetMapping("/product/{productId}")
  public ResponseEntity<BookstoreProductDto> getProductById(
      @PathVariable("productId") UUID productId
  ) {
    BookstoreProductDto product = bookstoreRepository.getProductById(productId);
    log.info("Product {} with id {} has been found.", product.name(), productId);
    return ResponseEntity.ok(product);
  }

  @Operation(
      summary = "Get All Products information",
      description = "REST API GET method to fetch all products")
  @GetMapping("/product")
  public ResponseEntity<List<BookstoreProductDto>> getAllProducts() {
    List<BookstoreProductDto> products = bookstoreRepository.getAllProducts();
    log.info("Products have been found.");
    return ResponseEntity.ok(products);
  }

  @Operation(
      summary = "Update Product information",
      description = "REST API PUT method to update single product")
  @PutMapping("/product/{productId}")
  public ResponseEntity<Void> updateProductById(
      @PathVariable("productId") UUID productId, @RequestBody ProductDto productDto
  ) {
    bookstoreRepository.updateProductById(productId, productDto);
    log.info("Product with id {} has been updated.", productId);
    return ResponseEntity.noContent().build();
  }

  @Operation(
      summary = "Delete Product information",
      description = "REST API DELETE method to update single product")
  @DeleteMapping("/{bookstoreId}/product/{productId}")
  public ResponseEntity<Void> deleteProductById(
      @PathVariable("bookstoreId") UUID bookstoreId,
      @PathVariable("productId") UUID productId
  ) {
    bookstoreRepository.deleteProductById(bookstoreId, productId);
    log.info("Product with id {} belonging to bookstore {} has been deleted.", productId,
        bookstoreId);
    return ResponseEntity.noContent().build();
  }
}
