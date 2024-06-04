package it.epicode.energy.types.responses;

import it.epicode.energy.entities.Customer;
import lombok.Data;

@Data
public class DeleteCustomerResponseBody {

  private String message;
  private Customer customer;

}
