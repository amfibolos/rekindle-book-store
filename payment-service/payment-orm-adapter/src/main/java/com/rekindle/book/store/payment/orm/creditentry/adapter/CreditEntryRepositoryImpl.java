package com.rekindle.book.store.payment.orm.creditentry.adapter;


import com.rekindle.book.store.domain.core.valueobject.CustomerId;
import com.rekindle.book.store.domain.payment.entity.CreditEntry;
import com.rekindle.book.store.payment.application.service.ports.output.repository.CreditEntryRepository;
import com.rekindle.book.store.payment.orm.creditentry.mapper.CreditEntryDataAccessMapper;
import com.rekindle.book.store.payment.orm.creditentry.repository.CreditEntryJpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class CreditEntryRepositoryImpl implements CreditEntryRepository {

  private final CreditEntryJpaRepository creditEntryJpaRepository;
  private final CreditEntryDataAccessMapper creditEntryDataAccessMapper;

  public CreditEntryRepositoryImpl(
      CreditEntryJpaRepository creditEntryJpaRepository,
      CreditEntryDataAccessMapper creditEntryDataAccessMapper
  ) {
    this.creditEntryJpaRepository = creditEntryJpaRepository;
    this.creditEntryDataAccessMapper = creditEntryDataAccessMapper;
  }

  @Override
  public CreditEntry save(CreditEntry creditEntry) {
    return creditEntryDataAccessMapper
        .creditEntryEntityToCreditEntry(creditEntryJpaRepository
            .save(creditEntryDataAccessMapper.creditEntryToCreditEntryEntity(creditEntry)));
  }

  @Override
  public Optional<CreditEntry> findByCustomerId(CustomerId customerId) {
    return creditEntryJpaRepository
        .findByCustomerId(customerId.getValue())
        .map(creditEntryDataAccessMapper::creditEntryEntityToCreditEntry);
  }
}
