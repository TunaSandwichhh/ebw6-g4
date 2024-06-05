package it.epicode.energy.types.requests.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateInvoiceRequestBody {

  @NotNull(message = "date cannot be empty")
  private LocalDate date;

  @NotNull(message = "amount cannot be empty")
  private double amount;

  @NotBlank(message = "invoice state cannot be empty")
  private String invoiceState;

}
