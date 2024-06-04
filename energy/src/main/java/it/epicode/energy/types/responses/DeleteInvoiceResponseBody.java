package it.epicode.energy.types.responses;

import it.epicode.energy.entities.Invoice;
import lombok.Data;

@Data
public class DeleteInvoiceResponseBody {
  private String message;
  private Invoice invoice;
}
