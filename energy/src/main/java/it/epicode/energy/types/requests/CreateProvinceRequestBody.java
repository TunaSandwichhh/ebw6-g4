package it.epicode.energy.types.requests;

import it.epicode.energy.entities.County;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateProvinceRequestBody {

  @NotBlank(message = "initials cannot be empty")
  private String initials;

  @NotBlank(message = "region cannot be empty")
  private String region;

  @Size(min = 1, message = "list of counties cannot be empty")
  private List<County> counties;
}
