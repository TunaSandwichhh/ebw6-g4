package it.epicode.energy.types.requests.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCountyRequestBody {

  private String countyName;

  private int provinceCode;

  private String provinceName;

}
