package com.rekindle.book.store.payment.application.service.dto;

import com.rekindle.book.store.domain.payment.valueobject.TransactionType;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

@Builder
public record CreditHistoryDto(UUID creditId, UUID customerId, BigDecimal totalPrice,
                               TransactionType transactionType) {

}
