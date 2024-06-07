package it.epicode.energy.controllers;

import it.epicode.energy.entities.Invoice;
import it.epicode.energy.services.InvoiceService;
import it.epicode.energy.types.requests.create.CreateInvoiceRequestBody;
import it.epicode.energy.types.requests.update.UpdateInvoiceRequestBody;
import it.epicode.energy.types.responses.DeleteInvoiceResponseBody;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

  @Autowired
  private InvoiceService invoiceService;

  @GetMapping
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
  public ResponseEntity<Page<Invoice>> getInvoices(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "id") String sortBy) {
    return new ResponseEntity<>(invoiceService.retrieveAllCustomers(page, size, sortBy), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
  public ResponseEntity<Invoice> getInvoice(@PathVariable int id) {
    return new ResponseEntity<>(invoiceService.retrieveInvoiceById(id), HttpStatus.OK);
  }

  @PostMapping
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
  public ResponseEntity<Invoice> createInvoice(@RequestBody @Validated CreateInvoiceRequestBody invoiceRequestBody, BindingResult validation) throws BadRequestException {
    if(validation.hasErrors()){
      throw new BadRequestException(validation.getAllErrors()
              .stream()
              .map(objectError -> objectError.getDefaultMessage())
              .reduce("", (acc, curr) -> acc+curr));
    }
    return new ResponseEntity<>(invoiceService.addInvoice(invoiceRequestBody), HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
  public ResponseEntity<Invoice> updateInvoice(@RequestBody @Validated UpdateInvoiceRequestBody invoiceRequestBody,
                                               @PathVariable int id,
                                               BindingResult validation) throws BadRequestException {
    if(validation.hasErrors()){
      throw new BadRequestException(validation.getAllErrors()
              .stream()
              .map(objectError -> objectError.getDefaultMessage())
              .reduce("", (acc, curr) -> acc+curr));
    }
    return new ResponseEntity<>(invoiceService.editInvoice(id, invoiceRequestBody), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<DeleteInvoiceResponseBody> deleteInvoice(@PathVariable int id) {
    return new ResponseEntity<>(invoiceService.removeInvoice(id), HttpStatus.OK);
  }

}
