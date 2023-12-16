package com.rekindle.book.store.domain.bookstore.entity;


import com.rekindle.book.store.domain.bookstore.valueobject.OrderApprovalId;
import com.rekindle.book.store.domain.core.entity.AggregateRoot;
import com.rekindle.book.store.domain.core.valueobject.BookstoreId;
import com.rekindle.book.store.domain.core.valueobject.Money;
import com.rekindle.book.store.domain.core.valueobject.OrderApprovalStatus;
import com.rekindle.book.store.domain.core.valueobject.OrderStatus;
import java.util.List;
import java.util.UUID;

public class Bookstore extends AggregateRoot<BookstoreId> {

  private OrderApproval orderApproval;
  private boolean active;
  private final OrderDetail orderDetail;

  public void validateOrder(List<String> failureMessages) {
    if (orderDetail.getOrderStatus() != OrderStatus.PAID) {
      failureMessages.add("Payment is not completed for order: " + orderDetail.getId());
    }
    Money totalAmount = orderDetail.getProducts().stream().map(product -> {
      if (!product.isAvailable()) {
        failureMessages.add("Product with id: " + product.getId().getValue()
            + " is not available");
      }
      return product.getPrice().multiply(product.getQuantity());
    }).reduce(Money.ZERO, Money::add);

    if (!totalAmount.equals(orderDetail.getTotalAmount())) {
      failureMessages.add("Price total is not correct for order: " + orderDetail.getId());
    }
  }

  public void constructOrderApproval(OrderApprovalStatus orderApprovalStatus) {
    this.orderApproval = OrderApproval.builder()
        .orderApprovalId(new OrderApprovalId(UUID.randomUUID()))
        .bookstoreId(this.getId())
        .orderId(this.getOrderDetail().getId())
        .approvalStatus(orderApprovalStatus)
        .build();
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  private Bookstore(Builder builder) {
    setId(builder.bookstoreId);
    orderApproval = builder.orderApproval;
    active = builder.active;
    orderDetail = builder.orderDetail;
  }

  public static Builder builder() {
    return new Builder();
  }

  public OrderApproval getOrderApproval() {
    return orderApproval;
  }

  public boolean isActive() {
    return active;
  }

  public OrderDetail getOrderDetail() {
    return orderDetail;
  }

  public static final class Builder {

    private BookstoreId bookstoreId;
    private OrderApproval orderApproval;
    private boolean active;
    private OrderDetail orderDetail;

    private Builder() {
    }

    public Builder bookstoreId(BookstoreId val) {
      bookstoreId = val;
      return this;
    }

    public Builder orderApproval(OrderApproval val) {
      orderApproval = val;
      return this;
    }

    public Builder active(boolean val) {
      active = val;
      return this;
    }

    public Builder orderDetail(OrderDetail val) {
      orderDetail = val;
      return this;
    }

    public Bookstore build() {
      return new Bookstore(this);
    }
  }
}
