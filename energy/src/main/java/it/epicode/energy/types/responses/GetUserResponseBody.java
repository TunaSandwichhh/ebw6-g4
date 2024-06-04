package it.epicode.energy.types.responses;

import it.epicode.energy.entities.enums.UserRole;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class GetUserResponseBody {

  private UUID id;

  private String username;

  private String email;

  private String firstName;

  private String lastName;

  private String avatarUrl;

  private List<UserRole> userRoles;
}
