package it.epicode.energy.types.responses;

import it.epicode.energy.entities.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteInvoiceResponseBody {
  private String message;
  private Invoice invoice;
}
