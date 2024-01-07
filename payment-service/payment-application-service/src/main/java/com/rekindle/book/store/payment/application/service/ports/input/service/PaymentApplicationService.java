package com.rekindle.book.store.payment.application.service.ports.input.service;

import com.rekindle.book.store.domain.payment.entity.CreditHistory;
import com.rekindle.book.store.payment.application.service.dto.CreateCreditEntryCommand;
import com.rekindle.book.store.payment.application.service.dto.CreateCreditEntryResponse;
import com.rekindle.book.store.payment.application.service.dto.CreditHistoryDto;
import com.rekindle.book.store.payment.application.service.dto.PaymentStatusDto;
import java.util.List;
import java.util.UUID;

public interface PaymentApplicationService {

  PaymentStatusDto createPaymentStatusResponse(UUID orderId);

  CreateCreditEntryResponse creditUserWallet(CreateCreditEntryCommand createCreditEntryCommand);

  List<CreditHistoryDto> retrieveCreditHistoryByCustomerId(UUID customerId);

}
