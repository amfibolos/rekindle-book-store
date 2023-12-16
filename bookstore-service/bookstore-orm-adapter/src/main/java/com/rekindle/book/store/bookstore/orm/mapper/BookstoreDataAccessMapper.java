package com.rekindle.book.store.bookstore.orm.mapper;


import com.rekindle.book.store.bookstore.orm.entity.BookstoreEntity;
import com.rekindle.book.store.bookstore.orm.entity.OrderApprovalEntity;
import com.rekindle.book.store.bookstore.orm.exception.BookstoreDataAccessException;
import com.rekindle.book.store.domain.bookstore.entity.Bookstore;
import com.rekindle.book.store.domain.bookstore.entity.OrderApproval;
import com.rekindle.book.store.domain.bookstore.entity.OrderDetail;
import com.rekindle.book.store.domain.bookstore.entity.Product;
import com.rekindle.book.store.domain.bookstore.valueobject.OrderApprovalId;
import com.rekindle.book.store.domain.core.valueobject.BookstoreId;
import com.rekindle.book.store.domain.core.valueobject.Money;
import com.rekindle.book.store.domain.core.valueobject.OrderId;
import com.rekindle.book.store.domain.core.valueobject.ProductId;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BookstoreDataAccessMapper {

  public List<UUID> bookstoreToBookstoreProducts(Bookstore bookstore) {
    return bookstore.getOrderDetail().getProducts().stream()
        .map(product -> product.getId().getValue())
        .collect(Collectors.toList());
  }

  public Bookstore bookstoreEntityToBookstore(List<BookstoreEntity> bookstoreEntities) {
    BookstoreEntity bookstoreEntity =
        bookstoreEntities.stream().findFirst().orElseThrow(() ->
            new BookstoreDataAccessException("No bookstores found!"));

    List<Product> bookstoreProducts = bookstoreEntities.stream().map(entity ->
            Product.builder()
                .productId(new ProductId(entity.getProductId()))
                .name(entity.getProductName())
                .price(new Money(entity.getProductPrice()))
                .available(entity.getProductAvailable())
                .build())
        .collect(Collectors.toList());

    return Bookstore.builder()
        .bookstoreId(new BookstoreId(bookstoreEntity.getBookstoreId()))
        .orderDetail(OrderDetail.builder()
            .products(bookstoreProducts)
            .build())
        .active(bookstoreEntity.getBookstoreActive())
        .build();
  }

  public OrderApprovalEntity orderApprovalToOrderApprovalEntity(OrderApproval orderApproval) {
    return OrderApprovalEntity.builder()
        .id(orderApproval.getId().getValue())
        .bookstoreId(orderApproval.getBookstoreId().getValue())
        .orderId(orderApproval.getOrderId().getValue())
        .status(orderApproval.getApprovalStatus())
        .build();
  }

  public OrderApproval orderApprovalEntityToOrderApproval(OrderApprovalEntity orderApprovalEntity) {
    return OrderApproval.builder()
        .orderApprovalId(new OrderApprovalId(orderApprovalEntity.getId()))
        .bookstoreId(new BookstoreId(orderApprovalEntity.getBookstoreId()))
        .orderId(new OrderId(orderApprovalEntity.getOrderId()))
        .approvalStatus(orderApprovalEntity.getStatus())
        .build();
  }

}
