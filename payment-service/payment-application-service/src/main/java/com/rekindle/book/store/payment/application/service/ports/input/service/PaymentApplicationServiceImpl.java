package com.rekindle.book.store.payment.application.service.ports.input.service;

import com.rekindle.book.store.payment.application.service.PaymentRequestHelper;
import com.rekindle.book.store.payment.application.service.dto.CreateCreditEntryCommand;
import com.rekindle.book.store.payment.application.service.dto.CreateCreditEntryResponse;
import com.rekindle.book.store.payment.application.service.dto.CreditHistoryDto;
import com.rekindle.book.store.payment.application.service.dto.PaymentStatusDto;
import com.rekindle.book.store.payment.application.service.exception.PaymentApplicationServiceException;
import com.rekindle.book.store.payment.application.service.mapper.PaymentDataMapper;
import com.rekindle.book.store.payment.application.service.ports.output.repository.PaymentRepository;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class PaymentApplicationServiceImpl implements PaymentApplicationService {

  private final PaymentRepository paymentRepository;
  private final PaymentDataMapper paymentDataMapper;
  private final PaymentRequestHelper paymentRequestHelper;

  public PaymentApplicationServiceImpl(
      PaymentRepository paymentRepository, PaymentDataMapper paymentDataMapper,
      PaymentRequestHelper paymentRequestHelper
  ) {
    this.paymentRepository = paymentRepository;
    this.paymentDataMapper = paymentDataMapper;
    this.paymentRequestHelper = paymentRequestHelper;
  }

  @Override
  public PaymentStatusDto createPaymentStatusResponse(UUID orderId) {
    log.info("Retrieving payment status by order id {}", orderId);
    return paymentRepository.findByOrderId(orderId)
        .map(paymentDataMapper::paymentToPaymentStatusDto)
        .orElseThrow(() -> new PaymentApplicationServiceException("Payment with order id: " +
            orderId + " could not be found!"));
  }

  @Override
  public CreateCreditEntryResponse creditUserWallet(
      CreateCreditEntryCommand createCreditEntryCommand
  ) {
    return paymentRequestHelper.persistCreditEntry(createCreditEntryCommand);
  }

  @Override
  public List<CreditHistoryDto> retrieveCreditHistoryByCustomerId(UUID customerId) {
    return paymentRequestHelper.retrieveCreditHistoryByCustomerId(customerId).stream()
        .map(x -> CreditHistoryDto.builder()
            .creditId(x.getId().getValue())
            .customerId(x.getCustomerId().getValue())
            .totalPrice(x.getAmount().getAmount())
            .transactionType(x.getTransactionType())
            .build())
        .toList();
  }
}
