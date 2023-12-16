package com.rekindle.book.store.bookstore.orm.adapter;


import com.rekindle.book.store.bookstore.application.service.domain.ports.output.repository.OrderApprovalRepository;
import com.rekindle.book.store.bookstore.orm.mapper.BookstoreDataAccessMapper;
import com.rekindle.book.store.bookstore.orm.repository.OrderApprovalJpaRepository;
import com.rekindle.book.store.domain.bookstore.entity.OrderApproval;
import org.springframework.stereotype.Component;

@Component
public class OrderApprovalRepositoryImpl implements OrderApprovalRepository {

  private final OrderApprovalJpaRepository orderApprovalJpaRepository;
  private final BookstoreDataAccessMapper bookstoreDataAccessMapper;

  public OrderApprovalRepositoryImpl(
      OrderApprovalJpaRepository orderApprovalJpaRepository,
      BookstoreDataAccessMapper bookstoreDataAccessMapper
  ) {
    this.orderApprovalJpaRepository = orderApprovalJpaRepository;
    this.bookstoreDataAccessMapper = bookstoreDataAccessMapper;
  }

  @Override
  public OrderApproval save(OrderApproval orderApproval) {
    return bookstoreDataAccessMapper
        .orderApprovalEntityToOrderApproval(orderApprovalJpaRepository
            .save(bookstoreDataAccessMapper.orderApprovalToOrderApprovalEntity(orderApproval)));
  }

}
