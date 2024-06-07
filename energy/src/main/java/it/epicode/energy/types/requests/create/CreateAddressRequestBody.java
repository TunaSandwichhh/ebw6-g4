package it.epicode.energy.types.requests.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateAddressRequestBody {

  @NotBlank(message = "Street cannot be empty")
  private String street;

  @NotBlank(message = "Street number cannot be empty")
  private String streetNumber;

  @NotBlank(message = "Location cannot be empty")
  private String location;

  @NotNull(message = "Postal code cannot be empty")
  private int postalCode;

  @NotNull(message = "County ID cannot be empty")
  private int countyId;

  @NotNull(message = "Customer ID cannot be empty")
  private UUID customerId;
}
