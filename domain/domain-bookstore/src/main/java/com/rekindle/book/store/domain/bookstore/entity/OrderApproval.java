package com.rekindle.book.store.domain.bookstore.entity;


import com.rekindle.book.store.domain.bookstore.valueobject.OrderApprovalId;
import com.rekindle.book.store.domain.core.entity.BaseEntity;
import com.rekindle.book.store.domain.core.valueobject.BookstoreId;
import com.rekindle.book.store.domain.core.valueobject.OrderApprovalStatus;
import com.rekindle.book.store.domain.core.valueobject.OrderId;

public class OrderApproval extends BaseEntity<OrderApprovalId> {
    private final BookstoreId bookstoreId;
    private final OrderId orderId;
    private final OrderApprovalStatus approvalStatus;

    private OrderApproval(Builder builder) {
        setId(builder.orderApprovalId);
        bookstoreId = builder.bookstoreId;
        orderId = builder.orderId;
        approvalStatus = builder.approvalStatus;
    }

    public static Builder builder() {
        return new Builder();
    }


    public BookstoreId getBookstoreId() {
        return bookstoreId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public OrderApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public static final class Builder {
        private OrderApprovalId orderApprovalId;
        private BookstoreId bookstoreId;
        private OrderId orderId;
        private OrderApprovalStatus approvalStatus;

        private Builder() {
        }

        public Builder orderApprovalId(OrderApprovalId val) {
            orderApprovalId = val;
            return this;
        }

        public Builder bookstoreId(BookstoreId val) {
            bookstoreId = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder approvalStatus(OrderApprovalStatus val) {
            approvalStatus = val;
            return this;
        }

        public OrderApproval build() {
            return new OrderApproval(this);
        }
    }
}
