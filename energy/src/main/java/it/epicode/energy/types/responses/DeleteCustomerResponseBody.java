package it.epicode.energy.types.responses;

import it.epicode.energy.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteCustomerResponseBody {
  private String message;
  private Customer customer;
}