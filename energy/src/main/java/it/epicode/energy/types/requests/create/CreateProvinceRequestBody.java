package it.epicode.energy.types.requests.create;

import it.epicode.energy.entities.County;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateProvinceRequestBody {

  @NotBlank(message = "initials cannot be empty")
  private String initials;

  @NotBlank(message = "region cannot be empty")
  private String region;

}
