package it.epicode.energy.types.requests.update;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateAddressRequestBody {

  private String street;

  private String streetNumber;

  private String location;

  private int postalCode;

  private int countyId;

  private UUID customerId;

}
