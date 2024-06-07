package it.epicode.energy.types.requests.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreateInvoiceRequestBody {

  @NotNull(message = "date cannot be empty")
  private LocalDate date;

  @NotNull(message = "amount cannot be empty")
  private double amount;

  @NotBlank(message = "invoice state cannot be empty")
  private String invoiceState;

  @NotNull(message = "customer id cannot be empty")
  private UUID customerId;
}
