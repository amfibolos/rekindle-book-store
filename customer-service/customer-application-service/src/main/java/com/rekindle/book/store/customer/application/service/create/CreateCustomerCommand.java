package com.rekindle.book.store.customer.application.service.create;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateCustomerCommand(@NotNull @NotBlank String username,
                                    @NotNull @NotBlank String firstName,
                                    @NotNull @NotBlank String lastName) {

}
