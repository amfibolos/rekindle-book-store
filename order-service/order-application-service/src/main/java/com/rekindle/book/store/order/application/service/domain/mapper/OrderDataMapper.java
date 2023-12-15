package com.rekindle.book.store.order.application.service.domain.mapper;


import com.rekindle.book.store.domain.core.entity.Bookstore;
import com.rekindle.book.store.domain.core.entity.Order;
import com.rekindle.book.store.domain.core.entity.OrderItem;
import com.rekindle.book.store.domain.core.entity.Product;
import com.rekindle.book.store.domain.core.valueobject.Address;
import com.rekindle.book.store.domain.core.valueobject.BookstoreId;
import com.rekindle.book.store.domain.core.valueobject.CustomerId;
import com.rekindle.book.store.domain.core.valueobject.Money;
import com.rekindle.book.store.domain.core.valueobject.ProductId;
import com.rekindle.book.store.order.application.service.domain.dto.create.CreateOrderCommand;
import com.rekindle.book.store.order.application.service.domain.dto.create.CreateOrderCommandResponse;
import com.rekindle.book.store.order.application.service.domain.dto.create.OrderAddressDto;
import com.rekindle.book.store.order.application.service.domain.dto.create.OrderItemDto;
import com.rekindle.book.store.order.application.service.domain.dto.tracking.TrackOrderResponse;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderDataMapper {

  public Bookstore createOrderCommandToBookstore(CreateOrderCommand createOrderCommand) {
    return Bookstore.builder()
        .bookstoreId(new BookstoreId(createOrderCommand.bookstoreId()))
        .products(createOrderCommand.items().stream().map(orderItemDto ->
                new Product(new ProductId(orderItemDto.productId())))
            .collect(Collectors.toList()))
        .build();
  }

  public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
    return Order.builder()
        .customerId(new CustomerId(createOrderCommand.customerId()))
        .bookstoreId(new BookstoreId(createOrderCommand.bookstoreId()))
        .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.address()))
        .price(new Money(createOrderCommand.price()))
        .items(orderItemsToOrderItemEntities(createOrderCommand.items()))
        .build();
  }

  public CreateOrderCommandResponse orderToCreateOrderResponse(Order order, String message) {
    return CreateOrderCommandResponse.builder()
        .orderTrackingId(order.getTrackingId().getValue())
        .orderStatus(order.getOrderStatus())
        .message(message)
        .build();
  }

  public TrackOrderResponse orderToTrackOrderResponse(Order order) {
    return TrackOrderResponse.builder()
        .orderTrackingId(order.getTrackingId().getValue())
        .orderStatus(order.getOrderStatus())
        .failureMessages(order.getFailureMessages())
        .build();
  }

  private List<OrderItem> orderItemsToOrderItemEntities(
      List<OrderItemDto> orderItemDtos
  ) {
    return orderItemDtos.stream()
        .map(orderItemDto ->
            OrderItem.builder()
                .product(new Product(new ProductId(orderItemDto.productId())))
                .price(new Money(orderItemDto.price()))
                .quantity(orderItemDto.quantity())
                .subTotal(new Money(orderItemDto.subTotal()))
                .build()).collect(Collectors.toList());
  }

  private Address orderAddressToStreetAddress(OrderAddressDto orderAddressDto) {
    return new Address(
        UUID.randomUUID(),
        orderAddressDto.street(),
        orderAddressDto.postalCode(),
        orderAddressDto.city()
    );
  }
}
