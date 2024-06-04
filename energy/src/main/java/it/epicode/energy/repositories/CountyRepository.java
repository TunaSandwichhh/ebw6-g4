package it.epicode.energy.repositories;

import it.epicode.energy.entities.County;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountyRepository extends JpaRepository<County, Integer> {
}
