package com.rekindle.book.store.domain.application.exception;

import lombok.Builder;

@Builder
public record ErrorDTO(String code, String message) {

}
