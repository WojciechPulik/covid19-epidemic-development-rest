package pl.wpulik.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.persistence.NonUniqueResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wpulik.dto.DTOMapper;
import pl.wpulik.dto.DailyDTO;
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
		Optional<Simulation> simulation = simulationRepository.findById(simulationId);
		if(simulation.isEmpty()) {
			return new Simulation();
		}else{
			return simulation.get();
		}
	}
	
	public SimulationDTO getDtoById(Long simulationId) {
		return dtoMapper.toSimulationDTOMapping(findById(simulationId));
	}
	
	public SimulationDTO getDtoByName(String name) {
		SimulationDTO dto = new SimulationDTO();
		Optional<Simulation> simualtion = simulationRepository.findByName(name);
		try {
			dto = dtoMapper.toSimulationDTOMapping(simualtion.get());
		}catch(NoSuchElementException e2) {
			System.err.println(e2.getMessage());
			return dto;
		}
		return dto;
	}
	
	public List<DailyDTO> getEpidemicCourseByName(String name){
		Long id = getSimualtionIdByName(name);
		if(id!=0) {
			List<DailySimulation> dsList = dailySimulationRepository.findAllBySimulationId(id);
			List<DailyDTO> dtos = new ArrayList<>();
			for(DailySimulation ds : dsList) {
				dtos.add(dtoMapper.toDailyDTOMapping(ds));
			}
			return dtos;
		}else {
			return new ArrayList<DailyDTO>();
		}
	}
	
	private Long getSimualtionIdByName(String name) {
		Optional<Simulation> simualtion = simulationRepository.findByName(name);
		Long id = null;
		try {
			id = simualtion.get().getId();
		}catch(NoSuchElementException e2) {
			System.err.println(e2.getMessage());
			return 0L;
		}
		return id;
	}
	
	public Simulation getAllCourseById(Long simulationId) {
		Simulation simulation = simulationRepository.getOne(simulationId);
		simulation.getDailySimulation();
		return simulation;
	}
	
	

}
