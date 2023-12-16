package com.rekindle.book.store.payment.orm.creditentry.mapper;


import com.rekindle.book.store.domain.core.valueobject.CustomerId;
import com.rekindle.book.store.domain.core.valueobject.Money;
import com.rekindle.book.store.domain.payment.entity.CreditEntry;
import com.rekindle.book.store.domain.payment.valueobject.CreditEntryId;
import com.rekindle.book.store.payment.orm.creditentry.entity.CreditEntryEntity;
import org.springframework.stereotype.Component;

@Component
public class CreditEntryDataAccessMapper {

  public CreditEntry creditEntryEntityToCreditEntry(CreditEntryEntity creditEntryEntity) {
    return CreditEntry.builder()
        .creditEntryId(new CreditEntryId(creditEntryEntity.getId()))
        .customerId(new CustomerId(creditEntryEntity.getCustomerId()))
        .totalCreditAmount(new Money(creditEntryEntity.getTotalCreditAmount()))
        .build();
  }

  public CreditEntryEntity creditEntryToCreditEntryEntity(CreditEntry creditEntry) {
    return CreditEntryEntity.builder()
        .id(creditEntry.getId().getValue())
        .customerId(creditEntry.getCustomerId().getValue())
        .totalCreditAmount(creditEntry.getTotalCreditAmount().getAmount())
        .build();
  }

}
