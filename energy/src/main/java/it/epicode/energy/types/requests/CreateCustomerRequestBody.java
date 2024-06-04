package it.epicode.energy.types.requests;

import it.epicode.energy.entities.Address;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class CreateCustomerRequestBody {

  @NotBlank(message = "business name cannot be empty")
  private String businessName;

  @NotNull(message = "vat number name cannot be empty")
  private int vatNumber;

  @NotBlank(message = "email cannot be empty")
  @Email(message = "email does not have the right format")
  private String email;

  private LocalDate lastContactDate;

  @NotNull(message = "yearly revenue cannot be empty")
  private double yearlyRevenue;

  @NotBlank(message = "certified email cannot be empty")
  @Email(message = "email does not have the right format")
  private String certifiedEmail;

  @NotBlank(message = "telephone cannot be empty")
  private String telephone;

  @NotBlank(message = "contact email cannot be empty")
  @Email(message = "email does not have the right format")
  private String contactEmail;

  @NotBlank(message = "contact first name cannot be empty")
  private String contactFirstName;

  @NotBlank(message = "contact last name cannot be empty")
  private String contactLastName;

  @NotBlank(message = "contact telephone cannot be empty")
  private String contactTelephone;

  @NotBlank(message = "company logo cannot be empty")
  private String companyLogo;

  @NotBlank(message = "customer type cannot be empty")
  private String customerType;

}
