package com.rekindle.book.store.payment.orm.credithistory.mapper;


import com.rekindle.book.store.domain.core.valueobject.CustomerId;
import com.rekindle.book.store.domain.core.valueobject.Money;
import com.rekindle.book.store.domain.payment.entity.CreditHistory;
import com.rekindle.book.store.domain.payment.valueobject.CreditHistoryId;
import com.rekindle.book.store.payment.orm.credithistory.entity.CreditHistoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CreditHistoryDataAccessMapper {

  public CreditHistory creditHistoryEntityToCreditHistory(CreditHistoryEntity creditHistoryEntity) {
    return CreditHistory.builder()
        .creditHistoryId(new CreditHistoryId(creditHistoryEntity.getId()))
        .customerId(new CustomerId(creditHistoryEntity.getCustomerId()))
        .amount(new Money(creditHistoryEntity.getAmount()))
        .transactionType(creditHistoryEntity.getType())
        .build();
  }

  public CreditHistoryEntity creditHistoryToCreditHistoryEntity(CreditHistory creditHistory) {
    return CreditHistoryEntity.builder()
        .id(creditHistory.getId().getValue())
        .customerId(creditHistory.getCustomerId().getValue())
        .amount(creditHistory.getAmount().getAmount())
        .type(creditHistory.getTransactionType())
        .build();
  }

}
