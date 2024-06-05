package it.epicode.energy.types.requests.create;

import it.epicode.energy.entities.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateUserRequestBody {

  @NotBlank(message = "username cannot be empty")
  private String username;

  @NotBlank(message = "email cannot be empty")
  @Email(message = "email does not have the right format")
  private String email;

  @NotBlank(message = "password cannot be empty")
  private String password;

  @NotBlank(message = "first name cannot be empty")
  private String firstName;

  @NotBlank(message = "last name cannot be empty")
  private String lastName;

  @NotBlank(message = "avatar url cannot be empty")
  private String avatarUrl;

  @Size(min = 1, max = 2, message = "user must have at least a role assigned")
  private List<UserRole> userRoles;
}
