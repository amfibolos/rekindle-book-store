package com.rekindle.book.store.domain.payment.valueobject;


import com.rekindle.book.store.domain.core.valueobject.BaseId;
import java.util.UUID;

public class PaymentId extends BaseId<UUID> {
    public PaymentId(UUID value) {
        super(value);
    }
}
