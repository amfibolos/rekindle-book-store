package com.rekindle.book.store.bookstore.application.service.domain.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProductDto(@NotNull String name,
                         @NotNull BigDecimal price,
                         @NotNull Boolean available) {

}
