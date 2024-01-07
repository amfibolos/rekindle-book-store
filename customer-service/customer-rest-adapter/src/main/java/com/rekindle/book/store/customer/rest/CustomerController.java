package com.rekindle.book.store.customer.rest;


import com.rekindle.book.store.customer.application.service.create.CreateCustomerCommand;
import com.rekindle.book.store.customer.application.service.create.CreateCustomerResponse;
import com.rekindle.book.store.customer.application.service.dto.CustomerDto;
import com.rekindle.book.store.customer.application.service.ports.input.service.CustomerApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {

  private final CustomerApplicationService customerApplicationService;

  public CustomerController(CustomerApplicationService customerApplicationService) {
    this.customerApplicationService = customerApplicationService;
  }

  @Operation(
      summary = "Register new customer in Rekindle Bookstore Network",
      description = "REST API POST method to create a new customer")
  @PostMapping
  public ResponseEntity<CreateCustomerResponse> createCustomer(
      @Valid @RequestBody CreateCustomerCommand createCustomerCommand
  ) {
    log.info("Creating customer with username: {}", createCustomerCommand.username());
    CreateCustomerResponse response = customerApplicationService.createCustomer(
        createCustomerCommand);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @Operation(
      summary = "Fetch customer by id from Rekindle Bookstore Network",
      description = "REST API GET method to get customer information")
  @GetMapping("/{customerId}")
  public ResponseEntity<CustomerDto> getCustomerById(
      @PathVariable("customerId") UUID customerId
  ) {
    log.info("Fetching customer with id: {}", customerId);
    CustomerDto customer = customerApplicationService.fetchCustomer(customerId);
    return ResponseEntity.ok(customer);
  }

  @Operation(
      summary = "Fetch all customers from Rekindle Bookstore Network",
      description = "REST API GET method to get all customers information")
  @GetMapping
  public ResponseEntity<List<CustomerDto>> getCustomers() {
    log.info("Fetching all customers");
    List<CustomerDto> customers = customerApplicationService.fetchCustomers();
    return ResponseEntity.ok(customers);
  }

  @Operation(
      summary = "Update customer information by id in Rekindle Bookstore Network",
      description = "REST API PUT method to update customer information")
  @PutMapping("/{customerId}")
  public ResponseEntity<Void> updateCustomerInformation(
      @PathVariable("customerId") UUID customerId,
      @Valid @RequestBody CreateCustomerCommand createCommand
  ) {
    log.info("Updating customer with id: {}", customerId);
    customerApplicationService.updateCustomer(customerId, createCommand);
    return ResponseEntity.noContent().build();
  }

  @Operation(
      summary = "Delete customer information by id in Rekindle Bookstore Network",
      description = "REST API DELETE method to delete the customer")
  @DeleteMapping("/{customerId}")
  public ResponseEntity<Void> deleteCustomer(
      @PathVariable("customerId") UUID customerId
  ) {
    log.info("Deleting customer with id: {}", customerId);
    customerApplicationService.delete(customerId);
    return ResponseEntity.ok().build();
  }
}
