package com.rekindle.book.store.order.application.service.domain.dto.create;


import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

@Builder
public record OrderItemDto(@NotNull UUID productId, @NotNull Integer quantity,
                           @NotNull BigDecimal price,
                           @NotNull BigDecimal subTotal) {

}
