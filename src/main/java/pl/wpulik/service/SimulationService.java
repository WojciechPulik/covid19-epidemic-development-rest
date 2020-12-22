package pl.wpulik.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wpulik.model.Simulation;
import pl.wpulik.repository.SimulationRepository;

@Service
@Transactional
public class SimulationService {
	
	private SimulationRepository simulationRepository;

	@Autowired
	public SimulationService(SimulationRepository simulationRepository) {
		this.simulationRepository = simulationRepository;
	}
	
	public Simulation save(Simulation initValues) {
		return simulationRepository.save(initValues);
	}
	
	
	

}
