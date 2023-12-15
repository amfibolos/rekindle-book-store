package com.rekindle.book.store.order.application.service.domain.dto.tracking;


import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Builder;

@Builder
public record TrackOrderQuery(@NotNull UUID orderTrackingId) {

}
