package com.rekindle.book.store.bookstore.application.service.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProductDto(@NotNull @NotBlank String name,
                         @NotNull BigDecimal price,
                         @NotNull Boolean available) {

}
