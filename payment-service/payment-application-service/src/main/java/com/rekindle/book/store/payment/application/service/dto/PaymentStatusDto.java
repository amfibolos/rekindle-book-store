package com.rekindle.book.store.payment.application.service.dto;

import com.rekindle.book.store.domain.core.valueobject.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

@Builder
public record PaymentStatusDto(@NotNull UUID customerId, @NotNull BigDecimal price,
                               @NotNull PaymentStatus status) {

}
