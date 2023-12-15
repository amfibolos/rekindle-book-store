package com.rekindle.book.store.order.application.service.domain.ports.input.service;


import com.rekindle.book.store.order.application.service.domain.dto.create.CreateOrderCommand;
import com.rekindle.book.store.order.application.service.domain.dto.create.CreateOrderCommandResponse;
import com.rekindle.book.store.order.application.service.domain.dto.tracking.TrackOrderQuery;
import com.rekindle.book.store.order.application.service.domain.dto.tracking.TrackOrderResponse;
import jakarta.validation.Valid;

public interface OrderApplicationService {

  CreateOrderCommandResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

  TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
