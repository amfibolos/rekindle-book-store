package com.rekindle.book.store.bookstore.application.service.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record BookstoreDto(@NotNull @NotBlank UUID id,
                           @NotNull @NotBlank String name,
                           @NotBlank Boolean isActive) {

}
