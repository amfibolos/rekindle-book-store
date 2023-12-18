package com.rekindle.book.store.payment.rest.controller.exception;

import com.rekindle.book.store.domain.application.exception.ErrorDTO;
import com.rekindle.book.store.domain.application.exception.GlobalExceptionHandler;
import com.rekindle.book.store.domain.payment.exception.PaymentDomainException;
import com.rekindle.book.store.domain.payment.exception.PaymentNotFoundException;
import com.rekindle.book.store.payment.application.service.exception.PaymentApplicationServiceException;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class PaymentExceptionHandler extends GlobalExceptionHandler {

  @ResponseBody
  @ExceptionHandler(value = {PaymentDomainException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDTO handleException(PaymentDomainException paymentDomainException) {
    log.error(paymentDomainException.getMessage(), paymentDomainException);
    return ErrorDTO.builder()
        .errorCode(HttpStatus.BAD_REQUEST)
        .errorMessage(paymentDomainException.getMessage())
        .errorTime(LocalDateTime.now())
        .build();
  }

  @ResponseBody
  @ExceptionHandler(value = {PaymentNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorDTO handleException(PaymentNotFoundException paymentNotFoundException) {
    log.error(paymentNotFoundException.getMessage(), paymentNotFoundException);
    return ErrorDTO.builder()
        .errorCode(HttpStatus.NOT_FOUND)
        .errorMessage(paymentNotFoundException.getMessage())
        .errorTime(LocalDateTime.now())
        .build();
  }

  @ResponseBody
  @ExceptionHandler(value = {PaymentApplicationServiceException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDTO handleException(PaymentApplicationServiceException serviceException) {
    log.error(serviceException.getMessage(), serviceException);
    return ErrorDTO.builder()
        .errorCode(HttpStatus.BAD_REQUEST)
        .errorMessage(serviceException.getMessage())
        .errorTime(LocalDateTime.now())
        .build();
  }
}
