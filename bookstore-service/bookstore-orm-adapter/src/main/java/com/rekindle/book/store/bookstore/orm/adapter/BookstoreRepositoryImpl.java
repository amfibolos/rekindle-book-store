package com.rekindle.book.store.bookstore.orm.adapter;


import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreCreateCommand;
import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreProductCreateCommand;
import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreProductDto;
import com.rekindle.book.store.bookstore.application.service.domain.dto.ProductDto;
import com.rekindle.book.store.bookstore.application.service.domain.ports.output.repository.BookstoreRepository;
import com.rekindle.book.store.bookstore.orm.entity.BookstoreEntity;
import com.rekindle.book.store.bookstore.orm.entity.BookstoreIdentityEntity;
import com.rekindle.book.store.bookstore.orm.entity.ProductEntity;
import com.rekindle.book.store.bookstore.orm.exception.ProductEntityNotFoundException;
import com.rekindle.book.store.bookstore.orm.mapper.BookstoreDataAccessMapper;
import com.rekindle.book.store.bookstore.orm.repository.BookstoreIdentityRepository;
import com.rekindle.book.store.bookstore.orm.repository.BookstoreJpaRepository;
import com.rekindle.book.store.bookstore.orm.repository.ProductRepository;
import com.rekindle.book.store.domain.bookstore.entity.Bookstore;
import com.rekindle.book.store.domain.bookstore.exception.BookstoreNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BookstoreRepositoryImpl implements BookstoreRepository {

  private final BookstoreJpaRepository bookstoreJpaRepository;
  private final BookstoreIdentityRepository bookstoreIdentityRepository;
  private final ProductRepository productRepository;
  private final BookstoreDataAccessMapper bookstoreDataAccessMapper;

  public BookstoreRepositoryImpl(
      BookstoreJpaRepository bookstoreJpaRepository,
      BookstoreIdentityRepository bookstoreIdentityRepository, ProductRepository productRepository,
      BookstoreDataAccessMapper bookstoreDataAccessMapper
  ) {
    this.bookstoreJpaRepository = bookstoreJpaRepository;
    this.bookstoreIdentityRepository = bookstoreIdentityRepository;
    this.productRepository = productRepository;
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

  @Override
  @Transactional
  public UUID registerNewBookstore(BookstoreCreateCommand bookstoreCreateCommand) {
    BookstoreIdentityEntity entity = BookstoreIdentityEntity.builder()
        .id(UUID.randomUUID())
        .name(bookstoreCreateCommand.name())
        .active(bookstoreCreateCommand.isActive())
        .build();
    bookstoreIdentityRepository.save(entity);
    return entity.getId();
  }

  @Override
  @Transactional
  public UUID registerNewBookstoreProduct(
      BookstoreProductCreateCommand createCommand, UUID bookstoreId
  ) {
    BookstoreIdentityEntity bookstoreEntity = bookstoreIdentityRepository
        .findById(bookstoreId)
        .orElseThrow(() -> new BookstoreNotFoundException("Bookstore not found"));

    ProductEntity productEntity = ProductEntity.builder()
        .id(UUID.randomUUID())
        .name(createCommand.name())
        .price(createCommand.price())
        .available(createCommand.available())
        .build();

    bookstoreEntity.addProductEntity(productEntity);
    bookstoreIdentityRepository.save(bookstoreEntity);
    return productEntity.getId();
  }

  @Override
  @Transactional
  public BookstoreProductDto getProductById(UUID productId) {
    ProductEntity productEntity = productRepository.findById(productId)
        .orElseThrow(() -> new ProductEntityNotFoundException("Product not found!"));
    return BookstoreProductDto
        .builder()
        .available(productEntity.getAvailable())
        .name(productEntity.getName())
        .price(productEntity.getPrice())
        .bookstores(productEntity.getBookstoreIdentityEntities().stream()
            .map(BookstoreIdentityEntity::getId).collect(
                Collectors.toList()))
        .build();
  }

  @Override
  public void updateProductById(UUID productId, ProductDto productDto) {
    ProductEntity productEntity = productRepository.findById(productId)
        .orElseThrow(() -> new ProductEntityNotFoundException("Product not found!"));
    productEntity.setName(productDto.name());
    productEntity.setPrice(productDto.price());
    productEntity.setAvailable(productDto.available());
    productRepository.save(productEntity);
  }
}
