package com.rekindle.book.store.bookstore.application.service.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookstoreCreateCommand(@NotNull @NotBlank String name,
                                     @NotNull Boolean isActive) {

}
