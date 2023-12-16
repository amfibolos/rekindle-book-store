package com.rekindle.book.store.bookstore.application.service.domain;


import com.rekindle.book.store.bookstore.application.service.domain.dto.BookstoreApprovalRequest;
import com.rekindle.book.store.bookstore.application.service.domain.mapper.BookstoreDataMapper;
import com.rekindle.book.store.bookstore.application.service.domain.ports.output.message.publisher.OrderApprovedMessagePublisher;
import com.rekindle.book.store.bookstore.application.service.domain.ports.output.message.publisher.OrderRejectedMessagePublisher;
import com.rekindle.book.store.bookstore.application.service.domain.ports.output.repository.BookstoreRepository;
import com.rekindle.book.store.bookstore.application.service.domain.ports.output.repository.OrderApprovalRepository;
import com.rekindle.book.store.domain.bookstore.entity.Bookstore;
import com.rekindle.book.store.domain.bookstore.event.OrderApprovalEvent;
import com.rekindle.book.store.domain.bookstore.exception.BookstoreNotFoundException;
import com.rekindle.book.store.domain.bookstore.services.BookstoreDomainService;
import com.rekindle.book.store.domain.core.valueobject.OrderId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class BookstoreApprovalRequestHelper {

  private final BookstoreDomainService bookstoreDomainService;
  private final BookstoreDataMapper bookstoreDataMapper;
  private final BookstoreRepository bookstoreRepository;
  private final OrderApprovalRepository orderApprovalRepository;
  private final OrderApprovedMessagePublisher orderApprovedMessagePublisher;
  private final OrderRejectedMessagePublisher orderRejectedMessagePublisher;

  public BookstoreApprovalRequestHelper(
      BookstoreDomainService bookstoreDomainService,
      BookstoreDataMapper bookstoreDataMapper,
      BookstoreRepository bookstoreRepository,
      OrderApprovalRepository orderApprovalRepository,
      OrderApprovedMessagePublisher orderApprovedMessagePublisher,
      OrderRejectedMessagePublisher orderRejectedMessagePublisher
  ) {
    this.bookstoreDomainService = bookstoreDomainService;
    this.bookstoreDataMapper = bookstoreDataMapper;
    this.bookstoreRepository = bookstoreRepository;
    this.orderApprovalRepository = orderApprovalRepository;
    this.orderApprovedMessagePublisher = orderApprovedMessagePublisher;
    this.orderRejectedMessagePublisher = orderRejectedMessagePublisher;
  }

  @Transactional
  public OrderApprovalEvent persistOrderApproval(
      BookstoreApprovalRequest bookstoreApprovalRequest
  ) {
    log.info("Processing bookstore approval for order id: {}",
        bookstoreApprovalRequest.getOrderId());
    List<String> failureMessages = new ArrayList<>();
    Bookstore bookstore = findBookstore(bookstoreApprovalRequest);
    OrderApprovalEvent orderApprovalEvent =
        bookstoreDomainService.validateOrder(
            bookstore,
            failureMessages,
            orderApprovedMessagePublisher,
            orderRejectedMessagePublisher);
    orderApprovalRepository.save(bookstore.getOrderApproval());
    return orderApprovalEvent;
  }

  private Bookstore findBookstore(BookstoreApprovalRequest bookstoreApprovalRequest) {
    Bookstore bookstore = bookstoreDataMapper
        .bookstoreApprovalRequestToBookstore((bookstoreApprovalRequest));
    Optional<Bookstore> bookstoreResult = bookstoreRepository.findBookstoreInformation(
        bookstore);
    if (bookstoreResult.isEmpty()) {
      log.error("Bookstore with id " + bookstore.getId().getValue() + " not found!");
      throw new BookstoreNotFoundException("Bookstore with id " + bookstore.getId().getValue() +
          " not found!");
    }

    Bookstore bookstoreEntity = bookstoreResult.get();
    bookstore.setActive(bookstoreEntity.isActive());
    bookstore.getOrderDetail().getProducts().forEach(product ->
        bookstoreEntity.getOrderDetail().getProducts().forEach(p -> {
          if (p.getId().equals(product.getId())) {
            product.updateWithConfirmedNamePriceAndAvailability(p.getName(), p.getPrice(),
                p.isAvailable());
          }
        }));
    bookstore.getOrderDetail()
        .setId(new OrderId(UUID.fromString(bookstoreApprovalRequest.getOrderId())));

    return bookstore;
  }
}
