package com.rekindle.book.store.bookstore.application.service.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record BookstoreProductDto(@NotNull @NotBlank UUID id,
                                  @NotNull @NotBlank String name,
                                  @NotNull BigDecimal price,
                                  @NotNull Boolean available,
                                  @NotNull List<UUID> bookstores) {

}
