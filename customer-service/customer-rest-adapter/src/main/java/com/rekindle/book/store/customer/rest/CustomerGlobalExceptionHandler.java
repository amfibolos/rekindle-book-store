package com.rekindle.book.store.customer.rest;


import com.rekindle.book.store.domain.application.exception.ErrorDTO;
import com.rekindle.book.store.domain.application.exception.GlobalExceptionHandler;
import com.rekindle.book.store.domain.customer.exception.CustomerDomainException;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class CustomerGlobalExceptionHandler extends GlobalExceptionHandler {

  @ResponseBody
  @ExceptionHandler(value = {CustomerDomainException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDTO handleException(CustomerDomainException exception) {
    log.error(exception.getMessage(), exception);
    return ErrorDTO.builder()
        .errorTime(LocalDateTime.now())
        .errorMessage(exception.getMessage())
        .errorCode(HttpStatus.BAD_REQUEST)
        .build();
  }

}
