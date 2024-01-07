package com.rekindle.book.store.payment.rest.controller;

import com.rekindle.book.store.domain.payment.entity.CreditHistory;
import com.rekindle.book.store.payment.application.service.dto.CreateCreditEntryCommand;
import com.rekindle.book.store.payment.application.service.dto.CreateCreditEntryResponse;
import com.rekindle.book.store.payment.application.service.dto.CreditHistoryDto;
import com.rekindle.book.store.payment.application.service.dto.PaymentStatusDto;
import com.rekindle.book.store.payment.application.service.ports.input.service.PaymentApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/payments", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PaymentController {

  private final PaymentApplicationService paymentApplicationService;

  public PaymentController(PaymentApplicationService paymentApplicationService) {
    this.paymentApplicationService = paymentApplicationService;
  }

  @Operation(
      summary = "Get payment information by order ID",
      description = "REST API GET method to fetch single payment")
  @GetMapping("/{orderId}")
  public ResponseEntity<PaymentStatusDto> getOrderById(@PathVariable("orderId") UUID orderId) {
    PaymentStatusDto statusDto = paymentApplicationService.createPaymentStatusResponse(
        orderId);
    log.info("Payment with order id {} has been found.", orderId);
    return ResponseEntity.ok(statusDto);
  }

  @Operation(
      summary = "Get credit history information by customer id",
      description = "REST API GET method to fetch whole credit history")
  @GetMapping("/credit/history/{customerId}")
  public ResponseEntity<List<CreditHistoryDto>> getCustomerCreditHistory(
      @PathVariable("customerId") UUID customerId
  ) {
    List<CreditHistoryDto> historyList = paymentApplicationService.retrieveCreditHistoryByCustomerId(
        customerId);
    log.info("Credit history belonging to customer {} has been retrieved", customerId);
    return ResponseEntity.ok(historyList);
  }

  @Operation(
      summary = "Top up customer's wallet",
      description = "REST API POST method to add money to customer's wallet")
  @PostMapping("/credit")
  public ResponseEntity<CreateCreditEntryResponse> creditUserWallet(
      @Valid @RequestBody
      CreateCreditEntryCommand createCreditEntryCommand
  ) {
    CreateCreditEntryResponse response = paymentApplicationService.creditUserWallet(
        createCreditEntryCommand);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

}
