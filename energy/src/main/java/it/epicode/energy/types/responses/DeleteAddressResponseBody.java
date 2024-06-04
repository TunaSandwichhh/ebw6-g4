package it.epicode.energy.types.responses;

import it.epicode.energy.entities.Address;
import lombok.Data;

@Data
public class DeleteAddressResponseBody {
  private String message;
  private Address address;
}
