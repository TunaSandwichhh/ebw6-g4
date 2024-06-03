package it.epicode.energy.entities;

import it.epicode.energy.entities.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue
  private UUID id;

  private String username;

  private String email;

  private String password;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "avatar_url")
  private String avatarUrl;

  private List<UserRole> userRoles;

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", avatarUrl='" + avatarUrl + '\'' +
            '}';
  }
}