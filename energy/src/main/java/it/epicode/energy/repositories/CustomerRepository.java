package it.epicode.energy.repositories;

import it.epicode.energy.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<UUID, Customer> {
}
