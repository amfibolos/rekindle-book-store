package com.rekindle.book.store.order.application.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record CreateOrderCommand(@NotNull UUID customerId, @NotNull UUID bookstoreId,
                                 @NotNull BigDecimal price,
                                 @NotNull List<OrderItemDto> items,
                                 @NotNull OrderAddressDto address) {

}
