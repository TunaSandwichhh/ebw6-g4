package it.epicode.energy.repositories;
import it.epicode.energy.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {
}