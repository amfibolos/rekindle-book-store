package com.rekindle.book.store.bookstore.rest.controller;

import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreCreateCommand;
import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreProductCreateCommand;
import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreProductDto;
import com.rekindle.book.store.bookstore.application.service.domain.ports.output.repository.BookstoreRepository;
import io.swagger.v3.oas.annotations.Operation;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping(value = "api/v1/bookstore", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BookstoreController {

  private final BookstoreRepository bookstoreRepository;

  public BookstoreController(BookstoreRepository bookstoreRepository) {
    this.bookstoreRepository = bookstoreRepository;
  }
  @Operation(
      summary = "Register new Bookstore in Rekindle Bookstore Network",
      description = "REST API POST method to add new bookstore to Rekindle Network")
  @PostMapping
  public ResponseEntity<UUID> registerBookstore(@RequestBody BookstoreCreateCommand createCommand) {
    UUID id = bookstoreRepository.registerNewBookstore(createCommand);
    log.info("Bookstore {} has been registered with id {}", createCommand.name(), id);
    return ResponseEntity.ok(id);
  }

  @Operation(
      summary = "Register new product under specific Bookstore in Rekindle Bookstore Network",
      description = "REST API POST method to add new product under specific Bookstore")
  @PostMapping("/{bookstoreId}/product")
  public ResponseEntity<UUID> registerBookstoreProduct(
      @PathVariable("bookstoreId") UUID bookstoreId,
      @RequestBody BookstoreProductCreateCommand createCommand
  ) {
    UUID productId = bookstoreRepository.registerNewBookstoreProduct(createCommand, bookstoreId);
    log.info("New product {} has been registered under bookstore {}", createCommand.name(),
        bookstoreId);
    return ResponseEntity.ok(productId);
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
}
