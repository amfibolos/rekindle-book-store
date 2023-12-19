package com.rekindle.book.store.customer.application.service.create;


import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateCustomerResponse {

  @NotNull
  private final UUID customerId;
  @NotNull
  private final String message;
}
