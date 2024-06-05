package it.epicode.energy.types.requests.update;

import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateCustomerRequestBody {

  private String businessName;

  private int vatNumber;

  @Email(message = "email does not have the right format")
  private String email;

  private LocalDate lastContactDate;

  private double yearlyRevenue;

  @Email(message = "email does not have the right format")
  private String certifiedEmail;

  private String telephone;

  @Email(message = "email does not have the right format")
  private String contactEmail;

  private String contactFirstName;

  private String contactLastName;

  private String contactTelephone;

  private String companyLogo;

  private String customerType;
}
