package it.epicode.energy.types.responses;

import it.epicode.energy.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteAddressResponseBody {
  private String message;
  private Address address;
}
