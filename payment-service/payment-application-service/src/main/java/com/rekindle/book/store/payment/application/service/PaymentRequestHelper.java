package com.rekindle.book.store.payment.application.service;


import com.rekindle.book.store.domain.core.valueobject.CustomerId;
import com.rekindle.book.store.domain.core.valueobject.Money;
import com.rekindle.book.store.domain.payment.entity.CreditEntry;
import com.rekindle.book.store.domain.payment.entity.CreditHistory;
import com.rekindle.book.store.domain.payment.entity.Payment;
import com.rekindle.book.store.domain.payment.event.PaymentEvent;
import com.rekindle.book.store.domain.payment.exception.PaymentDomainException;
import com.rekindle.book.store.domain.payment.services.PaymentDomainService;
import com.rekindle.book.store.domain.payment.valueobject.CreditEntryId;
import com.rekindle.book.store.domain.payment.valueobject.CreditHistoryId;
import com.rekindle.book.store.domain.payment.valueobject.TransactionType;
import com.rekindle.book.store.payment.application.service.dto.CreateCreditEntryCommand;
import com.rekindle.book.store.payment.application.service.dto.CreateCreditEntryResponse;
import com.rekindle.book.store.payment.application.service.dto.PaymentRequest;
import com.rekindle.book.store.payment.application.service.exception.PaymentApplicationServiceException;
import com.rekindle.book.store.payment.application.service.mapper.PaymentDataMapper;
import com.rekindle.book.store.payment.application.service.ports.output.message.publisher.PaymentCancelledMessagePublisher;
import com.rekindle.book.store.payment.application.service.ports.output.message.publisher.PaymentCompletedMessagePublisher;
import com.rekindle.book.store.payment.application.service.ports.output.message.publisher.PaymentFailedMessagePublisher;
import com.rekindle.book.store.payment.application.service.ports.output.repository.CreditEntryRepository;
import com.rekindle.book.store.payment.application.service.ports.output.repository.CreditHistoryRepository;
import com.rekindle.book.store.payment.application.service.ports.output.repository.PaymentRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class PaymentRequestHelper {

  private final PaymentDomainService paymentDomainService;
  private final PaymentDataMapper paymentDataMapper;
  private final PaymentRepository paymentRepository;
  private final CreditEntryRepository creditEntryRepository;
  private final CreditHistoryRepository creditHistoryRepository;
  private final PaymentCompletedMessagePublisher paymentCompletedEventDomainEventPublisher;
  private final PaymentCancelledMessagePublisher paymentCancelledEventDomainEventPublisher;
  private final PaymentFailedMessagePublisher paymentFailedEventDomainEventPublisher;

  public PaymentRequestHelper(
      PaymentDomainService paymentDomainService,
      PaymentDataMapper paymentDataMapper,
      PaymentRepository paymentRepository,
      CreditEntryRepository creditEntryRepository,
      CreditHistoryRepository creditHistoryRepository,
      PaymentCompletedMessagePublisher paymentCompletedEventDomainEventPublisher,
      PaymentCancelledMessagePublisher paymentCancelledEventDomainEventPublisher,
      PaymentFailedMessagePublisher paymentFailedEventDomainEventPublisher
  ) {
    this.paymentDomainService = paymentDomainService;
    this.paymentDataMapper = paymentDataMapper;
    this.paymentRepository = paymentRepository;
    this.creditEntryRepository = creditEntryRepository;
    this.creditHistoryRepository = creditHistoryRepository;
    this.paymentCompletedEventDomainEventPublisher = paymentCompletedEventDomainEventPublisher;
    this.paymentCancelledEventDomainEventPublisher = paymentCancelledEventDomainEventPublisher;
    this.paymentFailedEventDomainEventPublisher = paymentFailedEventDomainEventPublisher;
  }

  @Transactional
  public PaymentEvent persistPayment(PaymentRequest paymentRequest) {
    log.info("Received payment complete event for order id: {}", paymentRequest.getOrderId());
    Payment payment = paymentDataMapper.paymentRequestModelToPayment(paymentRequest);
    CreditEntry creditEntry = getCreditEntry(payment.getCustomerId());
    List<CreditHistory> creditHistories = getCreditHistory(payment.getCustomerId());
    List<String> failureMessages = new ArrayList<>();
    PaymentEvent paymentEvent =
        paymentDomainService.validateAndInitiatePayment(payment, creditEntry, creditHistories,
            failureMessages,
            paymentCompletedEventDomainEventPublisher, paymentFailedEventDomainEventPublisher);
    persistDbObjects(payment, creditEntry, creditHistories, failureMessages);
    return paymentEvent;
  }

  @Transactional
  public PaymentEvent persistCancelPayment(PaymentRequest paymentRequest) {
    log.info("Received payment rollback event for order id: {}", paymentRequest.getOrderId());
    Optional<Payment> paymentResponse = paymentRepository
        .findByOrderId(UUID.fromString(paymentRequest.getOrderId()));
    if (paymentResponse.isEmpty()) {
      log.error("Payment with order id: {} could not be found!", paymentRequest.getOrderId());
      throw new PaymentApplicationServiceException("Payment with order id: " +
          paymentRequest.getOrderId() + " could not be found!");
    }
    Payment payment = paymentResponse.get();
    CreditEntry creditEntry = getCreditEntry(payment.getCustomerId());
    List<CreditHistory> creditHistories = getCreditHistory(payment.getCustomerId());
    List<String> failureMessages = new ArrayList<>();
    PaymentEvent paymentEvent = paymentDomainService
        .validateAndCancelPayment(payment, creditEntry, creditHistories, failureMessages,
            paymentCancelledEventDomainEventPublisher, paymentFailedEventDomainEventPublisher);
    persistDbObjects(payment, creditEntry, creditHistories, failureMessages);
    return paymentEvent;
  }

  public CreateCreditEntryResponse persistCreditEntry(CreateCreditEntryCommand command) {
    Money money = new Money(command.totalPrice());
    if (!money.isGreaterThanZero()) {
      log.error("Credit amount must be greater than 0");
      throw new PaymentDomainException("Credit amount must be greater than 0");
    }
    Optional<CreditEntry> creditEntry = creditEntryRepository.findByCustomerId(
        new CustomerId(command.customerId()));
    try {
      if (creditEntry.isPresent()) {
        creditEntry.get().addCreditAmount(money);
        creditEntryRepository.save(creditEntry.get());
        persistNewCreditHistoryEntry(creditEntry.get(), money);
        return new CreateCreditEntryResponse(creditEntry.get().getId().getValue());
      } else {
        CreditEntry newEntry = CreditEntry.builder()
            .creditEntryId(new CreditEntryId(UUID.randomUUID()))
            .customerId(new CustomerId(command.customerId()))
            .totalCreditAmount(money)
            .build();
        creditEntryRepository.save(newEntry);
        persistNewCreditHistoryEntry(newEntry, newEntry.getTotalCreditAmount());
        return new CreateCreditEntryResponse(newEntry.getId().getValue());
      }
    } catch (Exception e) {
      log.error("Creation of new credit entry for customer {} has failed with: {}",
          command.customerId(), e.getMessage());
      throw new PaymentApplicationServiceException("Could not save new credit entry: ", e);
    }
  }

  private CreditEntry getCreditEntry(CustomerId customerId) {
    Optional<CreditEntry> creditEntry = creditEntryRepository.findByCustomerId(customerId);
    if (creditEntry.isEmpty()) {
      log.error("Could not find credit entry for customer: {}", customerId.getValue());
      throw new PaymentApplicationServiceException("Could not find credit entry for customer: " +
          customerId.getValue());
    }
    return creditEntry.get();
  }

  private List<CreditHistory> getCreditHistory(CustomerId customerId) {
    Optional<List<CreditHistory>> creditHistories = creditHistoryRepository.findByCustomerId(
        customerId);
    if (creditHistories.isEmpty()) {
      log.error("Could not find credit history for customer: {}", customerId.getValue());
      throw new PaymentApplicationServiceException("Could not find credit history for customer: " +
          customerId.getValue());
    }
    return creditHistories.get();
  }

  private void persistDbObjects(
      Payment payment,
      CreditEntry creditEntry,
      List<CreditHistory> creditHistories,
      List<String> failureMessages
  ) {
    paymentRepository.save(payment);
    if (failureMessages.isEmpty()) {
      creditEntryRepository.save(creditEntry);
      creditHistoryRepository.save(creditHistories.getLast());
    }
  }

  private void persistNewCreditHistoryEntry(CreditEntry creditEntry, Money money) {
    creditHistoryRepository.save(CreditHistory.builder()
        .creditHistoryId(new CreditHistoryId(UUID.randomUUID()))
        .customerId(creditEntry.getCustomerId())
        .amount(money)
        .transactionType(TransactionType.CREDIT)
        .build());
  }

  public List<CreditHistory> retrieveCreditHistoryByCustomerId(UUID customerId) {
    return creditHistoryRepository.findByCustomerId(new CustomerId(customerId))
        .orElse(Collections.emptyList());
  }
}
