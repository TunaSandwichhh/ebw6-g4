package it.epicode.energy.types.responses;

import it.epicode.energy.entities.User;
import lombok.Data;

@Data
public class DeleteUserResponseBody {
  private String message;
  private User user;
}
