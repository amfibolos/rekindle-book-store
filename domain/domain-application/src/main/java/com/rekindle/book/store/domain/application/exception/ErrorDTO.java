package com.rekindle.book.store.domain.application.exception;

import java.time.LocalDateTime;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ErrorDTO(String apiPath, HttpStatus errorCode, String errorMessage,
                       LocalDateTime errorTime) {

}
