package pl.wpulik.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.wpulik.dto.DTOMapper;
import pl.wpulik.dto.DailyDTO;
import pl.wpulik.model.DailySimulation;
import pl.wpulik.model.Simulation;
import pl.wpulik.repository.DailySimulationRepository;

@Service
@Transactional
public class DailySimulationService {
	
	private DailySimulationRepository dailySimulationRepository;
	private DTOMapper dtoMapper;
	
	public DailySimulationService() {}
	
	@Autowired
	public DailySimulationService(DailySimulationRepository dailySimulationRepository, 
			DTOMapper dtoMapper) {
		this.dailySimulationRepository = dailySimulationRepository;
		this.dtoMapper = dtoMapper;
	}
	
	public DailySimulation save(DailySimulation daily) {
		return dailySimulationRepository.save(daily);
	}
	
	public List<DailySimulation> saveAll(List<DailySimulation> dailySimulations){
		return dailySimulationRepository.saveAll(dailySimulations);
	}
	
	public boolean removeAllDailySimualtions(Long simulationId) {
		dailySimulationRepository.deleteAllBySimulationId(simulationId);
		return dailySimulationRepository.findAllBySimulationId(simulationId).isEmpty();
	}
	
	
	public DailySimulation firstDayMapping(Simulation simulation) {
		return new DailySimulation(1,
				simulation.getInitialInfected(),
				simulation.getInitialInfected(),
				simulation.getPopulationSize() - simulation.getInitialInfected(),
				0L, 0L, 0L);
	}
	
	public Simulation persistSimulationCourse(Simulation simulation, List<DailySimulation>epidemicCourse) {
		List<DailySimulation> dailySimulations = saveAll(epidemicCourse);
		dailySimulations.forEach(ds -> ds.setSimulation(simulation));
		saveAll(dailySimulations);
		simulation.setDailySimulation(dailySimulations);
		return simulation;
	}
	
	public List<DailyDTO> getDtoCourseById(Long simulationId){
		List<DailySimulation> dsList = dailySimulationRepository.findAllBySimulationId(simulationId);
		List<DailyDTO> dtos = new ArrayList<>();
		for(DailySimulation ds : dsList) {
			dtos.add(dtoMapper.toDailyDTOMapping(ds));
		}
		return dtos;
	}

	
	

}
