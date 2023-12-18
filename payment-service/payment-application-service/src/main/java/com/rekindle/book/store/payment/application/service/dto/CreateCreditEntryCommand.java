package com.rekindle.book.store.payment.application.service.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

@Builder
public record CreateCreditEntryCommand(@NotNull UUID customerId, @NotNull BigDecimal totalPrice) {

}
