package pl.wpulik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.wpulik.model.DailySimulation;

@Repository
public interface DailySimulationRepository extends JpaRepository<DailySimulation, Long>{

}
