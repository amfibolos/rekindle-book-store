package com.rekindle.book.store.payment.orm.credithistory.adapter;


import com.rekindle.book.store.domain.core.valueobject.CustomerId;
import com.rekindle.book.store.domain.payment.entity.CreditHistory;
import com.rekindle.book.store.payment.application.service.ports.output.repository.CreditHistoryRepository;
import com.rekindle.book.store.payment.orm.credithistory.entity.CreditHistoryEntity;
import com.rekindle.book.store.payment.orm.credithistory.mapper.CreditHistoryDataAccessMapper;
import com.rekindle.book.store.payment.orm.credithistory.repository.CreditHistoryJpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CreditHistoryRepositoryImpl implements CreditHistoryRepository {

  private final CreditHistoryJpaRepository creditHistoryJpaRepository;
  private final CreditHistoryDataAccessMapper creditHistoryDataAccessMapper;

  public CreditHistoryRepositoryImpl(
      CreditHistoryJpaRepository creditHistoryJpaRepository,
      CreditHistoryDataAccessMapper creditHistoryDataAccessMapper
  ) {
    this.creditHistoryJpaRepository = creditHistoryJpaRepository;
    this.creditHistoryDataAccessMapper = creditHistoryDataAccessMapper;
  }

  @Override
  public CreditHistory save(CreditHistory creditHistory) {
    return creditHistoryDataAccessMapper.creditHistoryEntityToCreditHistory(
        creditHistoryJpaRepository
            .save(creditHistoryDataAccessMapper.creditHistoryToCreditHistoryEntity(creditHistory)));
  }

  @Override
  public Optional<List<CreditHistory>> findByCustomerId(CustomerId customerId) {
    Optional<List<CreditHistoryEntity>> creditHistory =
        creditHistoryJpaRepository.findByCustomerId(customerId.getValue());
    return creditHistory
        .map(creditHistoryList ->
            creditHistoryList.stream()
                .map(creditHistoryDataAccessMapper::creditHistoryEntityToCreditHistory)
                .collect(Collectors.toList()));
  }
}
