package it.epicode.energy.repositories;

import it.epicode.energy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
