package com.rekindle.book.store.bookstore.rest.exception;


import com.rekindle.book.store.bookstore.application.service.domain.exception.BookstoreApplicationServiceException;
import com.rekindle.book.store.domain.application.exception.ErrorDTO;
import com.rekindle.book.store.domain.application.exception.GlobalExceptionHandler;
import com.rekindle.book.store.domain.bookstore.exception.BookstoreDomainException;
import com.rekindle.book.store.domain.bookstore.exception.BookstoreNotFoundException;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class BookstoreExceptionHandler extends GlobalExceptionHandler {

  @ResponseBody
  @ExceptionHandler(value = {BookstoreDomainException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDTO handleException(BookstoreDomainException bookstoreDomainException) {
    log.error(bookstoreDomainException.getMessage(), bookstoreDomainException);
    return ErrorDTO.builder()
        .errorCode(HttpStatus.BAD_REQUEST)
        .errorMessage(bookstoreDomainException.getMessage())
        .errorTime(LocalDateTime.now())
        .build();
  }

  @ResponseBody
  @ExceptionHandler(value = {BookstoreNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorDTO handleException(BookstoreNotFoundException bookstoreNotFoundException) {
    log.error(bookstoreNotFoundException.getMessage(), bookstoreNotFoundException);
    return ErrorDTO.builder()
        .errorCode(HttpStatus.NOT_FOUND)
        .errorMessage(bookstoreNotFoundException.getMessage())
        .errorTime(LocalDateTime.now())
        .build();
  }

  @ResponseBody
  @ExceptionHandler(value = {BookstoreApplicationServiceException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDTO handleException(BookstoreApplicationServiceException serviceException) {
    log.error(serviceException.getMessage(), serviceException);
    return ErrorDTO.builder()
        .errorCode(HttpStatus.BAD_REQUEST)
        .errorMessage(serviceException.getMessage())
        .errorTime(LocalDateTime.now())
        .build();
  }
}
