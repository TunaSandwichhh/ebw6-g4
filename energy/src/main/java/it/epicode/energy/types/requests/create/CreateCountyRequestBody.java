package it.epicode.energy.types.requests.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCountyRequestBody {

  @NotBlank(message = "county name cannot be empty")
  private String countyName;

  @NotNull(message = "province code cannot be empty")
  private int provinceCode;

  //identificativo
  @NotBlank(message = "province name cannot be empty")
  private int provinceId;
}
