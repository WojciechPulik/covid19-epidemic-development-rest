package pl.wpulik.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.wpulik.dto.DailyDTO;
import pl.wpulik.dto.SimulationDTO;
import pl.wpulik.model.DailySimulation;
import pl.wpulik.model.Simulation;
import pl.wpulik.repository.DailySimulationRepository;
import pl.wpulik.service.DailySimulationService;
import pl.wpulik.service.EpidemicCourse;
import pl.wpulik.service.SimulationService;

@RestController
public class SimulationRestController {
	
	private EpidemicCourse epidemicCourse;
	private SimulationService simulationService;
	private DailySimulationService dailySimulationService;
	private DailySimulationRepository dailySimulationRepository;
	
	@Autowired
	public SimulationRestController(EpidemicCourse epidemicCourse, SimulationService simulationService, 
			DailySimulationService dailySimulationService, DailySimulationRepository dailySimulationRepository) {
		this.epidemicCourse = epidemicCourse;
		this.simulationService = simulationService;
		this.dailySimulationService = dailySimulationService;
		this.dailySimulationRepository = dailySimulationRepository;
	}



	@RequestMapping("/simulation/{id}")
	public SimulationDTO getSimulation(@PathVariable long id) {
		return simulationService.getDtoById(id);
	}
	
	@PostMapping(path = "/addnewsimulation", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<DailyDTO> addNewSimulation(@RequestBody SimulationDTO dto){
		System.out.println(dto.toString());
		List<DailyDTO> result = epidemicCourse.createNewEpidemicSimulation(dto);
		result.forEach(System.out::println);
		return result;
	}
	
	@RequestMapping("/simulations/{id}")
	public List<DailyDTO> getEpidemicCourseBySimulationId(@PathVariable long id){
		return dailySimulationService.getDtoCourseById(id);
	}

	

}
