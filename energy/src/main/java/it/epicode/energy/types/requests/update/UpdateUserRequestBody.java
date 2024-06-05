package it.epicode.energy.types.requests.update;

import it.epicode.energy.entities.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UpdateUserRequestBody {

  private String username;

  @Email(message = "email does not have the right format")
  private String email;

  private String password;

  private String firstName;

  private String lastName;

  private String avatarUrl;

  @Size(max = 2, message = "user can haver max 2 roles assigned")
  private List<UserRole> userRoles;

}
