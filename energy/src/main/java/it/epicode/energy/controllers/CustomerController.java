package it.epicode.energy.controllers;

import it.epicode.energy.entities.Customer;
import it.epicode.energy.services.CustomerService;
import it.epicode.energy.types.requests.create.CreateCustomerRequestBody;
import it.epicode.energy.types.requests.update.UpdateCustomerRequestBody;
import it.epicode.energy.types.responses.DeleteCustomerResponseBody;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @GetMapping
  public ResponseEntity<Page<Customer>> getCustomers(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "id") String sortBy) {
    return new ResponseEntity<>(customerService.retrieveAllCustomers(page, size, sortBy), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable UUID id) {
    return new ResponseEntity<>(customerService.retrieveCustomerById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Customer> createCustomer(@RequestBody @Validated CreateCustomerRequestBody customerRequestBody, BindingResult validation) throws BadRequestException {
    if(validation.hasErrors()){
      throw new BadRequestException(validation.getAllErrors()
              .stream()
              .map(objectError -> objectError.getDefaultMessage())
              .reduce("", (acc, curr) -> acc+curr));
    }
    return new ResponseEntity<>(customerService.addCustomer(customerRequestBody), HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Customer> updateCustomer(@RequestBody @Validated UpdateCustomerRequestBody customerRequestBody,
                                                 @PathVariable UUID id,
                                                 BindingResult validation) throws BadRequestException {
    if(validation.hasErrors()){
      throw new BadRequestException(validation.getAllErrors()
              .stream()
              .map(objectError -> objectError.getDefaultMessage())
              .reduce("", (acc, curr) -> acc+curr));
    }
    return new ResponseEntity<>(customerService.editCustomer(id, customerRequestBody), HttpStatus.OK);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<DeleteCustomerResponseBody> deleteCustomer(@PathVariable UUID id) {
    return new ResponseEntity<>(customerService.removeCustomer(id), HttpStatus.OK);
  }
}
