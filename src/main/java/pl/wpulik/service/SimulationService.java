package pl.wpulik.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wpulik.dto.DTOMapper;
import pl.wpulik.dto.SimulationDTO;
import pl.wpulik.model.DailySimulation;
import pl.wpulik.model.Simulation;
import pl.wpulik.repository.DailySimulationRepository;
import pl.wpulik.repository.SimulationRepository;

@Service
@Transactional
public class SimulationService {
	
	private SimulationRepository simulationRepository;
	private DailySimulationRepository dailySimulationRepository;
	private DTOMapper dtoMapper;

	@Autowired
	public SimulationService(SimulationRepository simulationRepository, 
			DailySimulationRepository dailySimulationRepository, DTOMapper dtoMapper) {
		this.simulationRepository = simulationRepository;
		this.dailySimulationRepository = dailySimulationRepository;
		this.dtoMapper = dtoMapper;
	}
	
	public Simulation save(Simulation initValues) {
		return simulationRepository.save(initValues);
	}
	
	public Simulation findById(Long simulationId) {
		return simulationRepository.getOne(simulationId);
	}
	
	public SimulationDTO getDtoById(Long simulationId) {
		return dtoMapper.toSimulationDTOMapping(findById(simulationId));
	}
	
	public Simulation getAllCourseById(Long simulationId) {
		Simulation simulation = simulationRepository.getOne(simulationId);
		simulation.getDailySimulation();
		return simulation;
	}
	
	

}
