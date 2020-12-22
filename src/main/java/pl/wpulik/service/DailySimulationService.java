package pl.wpulik.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wpulik.model.DailySimulation;
import pl.wpulik.model.Simulation;
import pl.wpulik.repository.DailySimulationRepository;

@Service
@Transactional
public class DailySimulationService {
	
	private DailySimulationRepository dailySimulationRepository;
	
	public DailySimulationService() {}
	
	@Autowired
	public DailySimulationService(DailySimulationRepository dailySimulationRepository) {
		this.dailySimulationRepository = dailySimulationRepository;
	}
	
	public DailySimulation save(DailySimulation daily) {
		return dailySimulationRepository.save(daily);
	}
	/*
	 * (Integer dayOfSymulation, Long currentPopulation, Long infected, Long newInfected, 
			Long healthySusceptible, Long died, Long dailyDeaths, Long recoveredResistant)
	 */
	public DailySimulation firstDayMapping(Simulation simulation) {
		return new DailySimulation(1,
				-2L,//currentPopulation: to remove?
				simulation.getInitialInfected(),
				simulation.getInitialInfected(),
				simulation.getPopulationSize() - simulation.getInitialInfected(),
				0L, 0L, 0L);
	}
	
	

}
