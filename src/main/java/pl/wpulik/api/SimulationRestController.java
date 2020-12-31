package pl.wpulik.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pl.wpulik.dto.DailyDTO;
import pl.wpulik.dto.SimulationDTO;
import pl.wpulik.service.DailySimulationService;
import pl.wpulik.service.EpidemicCourse;
import pl.wpulik.service.SimulationService;

@RestController
public class SimulationRestController {
	
	private EpidemicCourse epidemicCourse;
	private SimulationService simulationService;
	private DailySimulationService dailySimulationService;
	
	@Autowired
	public SimulationRestController(EpidemicCourse epidemicCourse, SimulationService simulationService, 
			DailySimulationService dailySimulationService) {
		this.epidemicCourse = epidemicCourse;
		this.simulationService = simulationService;
		this.dailySimulationService = dailySimulationService;
	}

	
	@PostMapping(path = "/addnewsimulation", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DailyDTO>> addNewSimulation(@RequestBody SimulationDTO dto){
		List<DailyDTO> result = epidemicCourse.createNewEpidemicSimulation(dto);
		for(DailyDTO dd : result) {
			System.out.print(dd.toString() + " Suma kontrolna: " + (dd.getPi()+dd.getPm()+dd.getPr()+dd.getPv()));		}
		if(!result.isEmpty()) {
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{status}")
					.buildAndExpand("simulation_created")
					.toUri();
			return ResponseEntity.created(location).body(result);			
		}else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}		
	}
	
	@PostMapping(path = "/updatesimulation", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DailyDTO>> updateSimulation(@RequestBody SimulationDTO dto){
		List<DailyDTO> result = epidemicCourse.updateEpidemicSimulation(dto);
		for(DailyDTO dd : result) {
			System.out.print(dd.toString() + " Suma kontrolna: " + (dd.getPi()+dd.getPm()+dd.getPr()+dd.getPv()));		}
		if(!result.isEmpty()) {
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{status}")
					.buildAndExpand("simulation_updated")
					.toUri();
			return ResponseEntity.created(location).body(result);			
		}else {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}		
	}
	
	@RequestMapping("/allsimulations")
	public ResponseEntity<List<SimulationDTO>> getAllSimulations(){
		List<SimulationDTO> dtos = simulationService.getAllSimulationsDto();
		if(dtos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(dtos);
		}
	}
	
	
	@RequestMapping("/simulation/id/{id}")
	public ResponseEntity<SimulationDTO> getSimulationById(@PathVariable long id) {
		SimulationDTO dto = simulationService.getDtoById(id);
		if(dto.getN()==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(dto);
		}
	}
	
	@RequestMapping("/simulation/name/{name}")
	public ResponseEntity<SimulationDTO> getSimulationByName(@PathVariable String name) {
		SimulationDTO dto = simulationService.getDtoByName(name);
		if(dto.getN()==null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(dto);
		}
	}
	
	@RequestMapping("/simulations/id/{id}")
	public ResponseEntity<List<DailyDTO>> getEpidemicCourseBySimulationId(@PathVariable long id){
		List<DailyDTO> result = dailySimulationService.getDtoCourseById(id);
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(result);
		}
	}
	
	@RequestMapping("/simulations/name/{name}")
	public ResponseEntity<List<DailyDTO>> getEpidemicCourseBySimulationName(@PathVariable String name){
		List<DailyDTO> result = simulationService.getEpidemicCourseByName(name);
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(result);
		}
	}

	

}
