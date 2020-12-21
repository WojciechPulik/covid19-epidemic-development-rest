package pl.wpulik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.wpulik.model.Simulation;

@Repository
public interface SimulationRepository extends JpaRepository<Simulation, Long>{

}
