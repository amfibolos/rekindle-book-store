package com.rekindle.book.store.order.rest.controller;

import com.rekindle.book.store.order.application.service.domain.dto.create.CreateOrderCommand;
import com.rekindle.book.store.order.application.service.domain.dto.create.CreateOrderCommandResponse;
import com.rekindle.book.store.order.application.service.domain.dto.tracking.TrackOrderQuery;
import com.rekindle.book.store.order.application.service.domain.dto.tracking.TrackOrderResponse;
import com.rekindle.book.store.order.application.service.domain.ports.input.service.OrderApplicationService;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/orders", produces = {MediaType.APPLICATION_JSON_VALUE})
public class OrderController {

  private final OrderApplicationService orderApplicationService;

  public OrderController(OrderApplicationService orderApplicationService) {
    this.orderApplicationService = orderApplicationService;
  }

  @PostMapping
  public ResponseEntity<CreateOrderCommandResponse> createOrder(
      @RequestBody CreateOrderCommand createOrderCommand
  ) {
    log.info("Creating order for customer: {} at bookstore: {}", createOrderCommand.customerId(),
        createOrderCommand.bookstoreId());
    CreateOrderCommandResponse createOrderResponse = orderApplicationService.createOrder(
        createOrderCommand);
    log.info("Order created with tracking id: {}", createOrderResponse.orderTrackingId());
    return ResponseEntity.ok(createOrderResponse);
  }

  @GetMapping("/{trackingId}")
  public ResponseEntity<TrackOrderResponse> getOrderByTrackingId(@PathVariable("trackingId") UUID trackingId) {
    TrackOrderResponse trackOrderResponse =
        orderApplicationService.trackOrder(
            TrackOrderQuery.builder().orderTrackingId(trackingId).build());
    log.info("Returning order status with tracking id: {}", trackOrderResponse.orderTrackingId());
    return ResponseEntity.ok(trackOrderResponse);
  }

}
