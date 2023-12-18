package com.rekindle.book.store.bookstore.application.service.domain.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record BookstoreProductCreateCommand(@NotNull String name,
                                            @NotNull BigDecimal price,
                                            @NotNull Boolean available) {

}
