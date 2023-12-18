package com.rekindle.book.store.bookstore.application.service.domain.dto;

import jakarta.validation.constraints.NotNull;

public record BookstoreCreateCommand(@NotNull String name, @NotNull Boolean isActive) {

}
