package com.rekindle.book.store.order.application.service.domain.dto.create;


import com.rekindle.book.store.domain.core.valueobject.OrderStatus;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Builder;

@Builder
public record CreateOrderCommandResponse(@NotNull UUID orderTrackingId,
                                         @NotNull OrderStatus orderStatus,
                                         @NotNull String message) {

}
