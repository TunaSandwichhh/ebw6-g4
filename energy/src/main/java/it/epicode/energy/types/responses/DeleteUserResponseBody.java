package it.epicode.energy.types.responses;

import it.epicode.energy.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteUserResponseBody {
  private String message;
  private User user;
}
