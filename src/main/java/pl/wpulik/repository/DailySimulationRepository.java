package pl.wpulik.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.wpulik.model.DailySimulation;

@Repository
public interface DailySimulationRepository extends JpaRepository<DailySimulation, Long>{
	
	@Query("SELECT ds from DailySimulation ds WHERE simulation_id=:simulationId ")
	List<DailySimulation>findAllBySimulationId(Long simulationId);

}
