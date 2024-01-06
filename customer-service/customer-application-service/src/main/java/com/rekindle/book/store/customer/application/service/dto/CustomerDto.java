package com.rekindle.book.store.customer.application.service.dto;

import java.util.UUID;

public record CustomerDto(UUID id, String username, String firstName, String lastName) {

}
