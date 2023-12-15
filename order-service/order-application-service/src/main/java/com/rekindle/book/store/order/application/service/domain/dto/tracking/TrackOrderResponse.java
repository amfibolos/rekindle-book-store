package com.rekindle.book.store.order.application.service.domain.dto.tracking;


import com.rekindle.book.store.domain.core.valueobject.OrderStatus;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record TrackOrderResponse(@NotNull UUID orderTrackingId, @NotNull OrderStatus orderStatus,
                                 List<String> failureMessages) {

}
