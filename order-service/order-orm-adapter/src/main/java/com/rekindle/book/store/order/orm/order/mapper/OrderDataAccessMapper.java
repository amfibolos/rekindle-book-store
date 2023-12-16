package com.rekindle.book.store.order.orm.order.mapper;


import com.rekindle.book.store.domain.core.utilities.Delimiter;
import com.rekindle.book.store.domain.core.valueobject.BookstoreId;
import com.rekindle.book.store.domain.core.valueobject.CustomerId;
import com.rekindle.book.store.domain.core.valueobject.Money;
import com.rekindle.book.store.domain.core.valueobject.OrderId;
import com.rekindle.book.store.domain.core.valueobject.ProductId;
import com.rekindle.book.store.domain.order.entity.Order;
import com.rekindle.book.store.domain.order.entity.OrderItem;
import com.rekindle.book.store.domain.order.entity.Product;
import com.rekindle.book.store.domain.order.valueobject.Address;
import com.rekindle.book.store.domain.order.valueobject.OrderItemId;
import com.rekindle.book.store.domain.order.valueobject.TrackingId;
import com.rekindle.book.store.order.orm.order.entity.OrderAddressEntity;
import com.rekindle.book.store.order.orm.order.entity.OrderEntity;
import com.rekindle.book.store.order.orm.order.entity.OrderItemEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderDataAccessMapper {

  public OrderEntity orderToOrderEntity(Order order) {
    OrderEntity orderEntity = OrderEntity.builder()
        .id(order.getId().getValue())
        .customerId(order.getCustomerId().getValue())
        .bookstoreId(order.getBookstoreId().getValue())
        .trackingId(order.getTrackingId().getValue())
        .address(deliveryAddressToAddressEntity(order.getDeliveryAddress()))
        .price(order.getPrice().getAmount())
        .items(orderItemsToOrderItemEntities(order.getItems()))
        .orderStatus(order.getOrderStatus())
        .failureMessages(order.getFailureMessages() != null ?
            String.join(Delimiter.COMMA, order.getFailureMessages()) : "")
        .build();
    orderEntity.getAddress().setOrder(orderEntity);
    orderEntity.getItems().forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntity));

    return orderEntity;
  }

  public Order orderEntityToOrder(OrderEntity orderEntity) {
    return Order.builder()
        .orderId(new OrderId(orderEntity.getId()))
        .customerId(new CustomerId(orderEntity.getCustomerId()))
        .bookstoreId((new BookstoreId(orderEntity.getBookstoreId())))
        .deliveryAddress(addressEntityToDeliveryAddress(orderEntity.getAddress()))
        .price(new Money(orderEntity.getPrice()))
        .items(orderItemEntitiesToOrderItems(orderEntity.getItems()))
        .trackingId(new TrackingId(orderEntity.getTrackingId()))
        .orderStatus(orderEntity.getOrderStatus())
        .failureMessages(orderEntity.getFailureMessages().isEmpty() ? new ArrayList<>() :
            new ArrayList<>(Arrays.asList(orderEntity.getFailureMessages()
                .split(Delimiter.COMMA))))
        .build();
  }

  private List<OrderItem> orderItemEntitiesToOrderItems(List<OrderItemEntity> items) {
    return items.stream()
        .map(orderItemEntity -> OrderItem.builder()
            .orderItemId(new OrderItemId(orderItemEntity.getId()))
            .product(new Product(new ProductId(orderItemEntity.getProductId())))
            .price(new Money(orderItemEntity.getPrice()))
            .quantity(orderItemEntity.getQuantity())
            .subTotal(new Money(orderItemEntity.getSubTotal()))
            .build())
        .collect(Collectors.toList());
  }

  private Address addressEntityToDeliveryAddress(OrderAddressEntity address) {
    return new Address(address.getId(),
        address.getStreet(),
        address.getPostalCode(),
        address.getCity());
  }

  private List<OrderItemEntity> orderItemsToOrderItemEntities(List<OrderItem> items) {
    return items.stream()
        .map(orderItem -> OrderItemEntity.builder()
            .id(orderItem.getId().getValue())
            .productId(orderItem.getProduct().getId().getValue())
            .price(orderItem.getPrice().getAmount())
            .quantity(orderItem.getQuantity())
            .subTotal(orderItem.getSubTotal().getAmount())
            .build())
        .collect(Collectors.toList());
  }

  private OrderAddressEntity deliveryAddressToAddressEntity(Address deliveryAddress) {
    return OrderAddressEntity.builder()
        .id(deliveryAddress.getId())
        .street(deliveryAddress.getStreet())
        .postalCode(deliveryAddress.getPostalCode())
        .city(deliveryAddress.getCity())
        .build();
  }
}
