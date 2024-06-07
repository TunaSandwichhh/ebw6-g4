package it.epicode.energy.repositories;

import it.epicode.energy.entities.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CountyRepository extends JpaRepository<County, Integer> {
}
