package com.rekindle.book.store.order.rest.exception;

import com.rekindle.book.store.domain.application.exception.ErrorDTO;
import com.rekindle.book.store.domain.application.exception.GlobalExceptionHandler;
import com.rekindle.book.store.domain.order.exception.OrderDomainException;
import com.rekindle.book.store.domain.order.exception.OrderNotFoundException;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class OrderExceptionHandler extends GlobalExceptionHandler {

  @ResponseBody
  @ExceptionHandler(value = {OrderDomainException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDTO handleException(OrderDomainException orderDomainException) {
    log.error(orderDomainException.getMessage(), orderDomainException);
    return ErrorDTO.builder()
        .errorCode(HttpStatus.BAD_REQUEST)
        .errorMessage(orderDomainException.getMessage())
        .errorTime(LocalDateTime.now())
        .build();
  }

  @ResponseBody
  @ExceptionHandler(value = {OrderNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorDTO handleException(OrderNotFoundException orderNotFoundException) {
    log.error(orderNotFoundException.getMessage(), orderNotFoundException);
    return ErrorDTO.builder()
        .errorCode(HttpStatus.NOT_FOUND)
        .errorMessage(orderNotFoundException.getMessage())
        .errorTime(LocalDateTime.now())
        .build();
  }

}
