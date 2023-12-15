package com.rekindle.book.store.order.application.service.domain.dto.create;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OrderAddressDto(@NotNull @Max(value = 50) String street,
                              @Max(value = 10) @NotNull String postalCode,
                              @Max(value = 50) @NotNull String city) {

}
