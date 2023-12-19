package com.rekindle.book.store.customer.application.service.create;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateCustomerCommand(@NotNull String username, @NotNull String firstName,
                                    @NotNull String lastName) {

}
