package pl.wpulik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

import pl.wpulik.model.Simulation;
import pl.wpulik.repository.SimulationRepository;
import pl.wpulik.dto.DTOMapper;
import pl.wpulik.dto.DailyDTO;
import pl.wpulik.dto.SimulationDTO;
import pl.wpulik.model.DailySimulation;

@Service
public class EpidemicCourse { 
	
	private static BigDecimal R;
	private static BigDecimal M;
	private static Integer Ti;
	private static Integer Tm;
	private static Integer Ts;
	private static Long P;
	
	private SimulationService simulationService;
	private DailySimulationService dailySimulationService;
	private List<DailySimulation> epidemicCourse = new ArrayList<>();
	private DTOMapper dtoMapper;
	private SimulationRepository simulationRepository;
	
	public EpidemicCourse() {}

	@Autowired
	public EpidemicCourse(SimulationService simulationService, DailySimulationService dailySimulationService,
			DTOMapper dtoMapper, SimulationRepository simulationRepository) {
		this.simulationService = simulationService;
		this.dailySimulationService = dailySimulationService;
		this.dtoMapper = dtoMapper;
		this.simulationRepository = simulationRepository;
	}
	
	public Simulation addNewSimulation(Simulation simulation) {
		return simulationService.save(simulation);
	}
	
	public Simulation addNewSimulationDto(SimulationDTO dto) {
		return addNewSimulation(dtoMapper.toSimulationMapping(dto));
	}
	
	public List<DailyDTO> updateEpidemicSimulation(SimulationDTO dto){
		long id = simulationService.getSimualtionIdByName(dto.getN());
		Simulation simulation = simulationService.findById(id);
		if(simulation.getId()!=null) {
			simulation = dtoMapper.toSimulationMapping(dto);
			simulation.setId(id);
			dailySimulationService.removeAllDailySimualtions(id);
			simulationService.save(simulation);
			firstDay(simulation);
			covidCourse();
			persistSimulation(simulation);
			epidemicCourse = new ArrayList<>();
			return dailySimulationService.getDtoCourseById(simulation.getId());
		}else {
			return new ArrayList<DailyDTO>();
		}
		
	}
	
	public List<DailyDTO> createNewEpidemicSimulation(SimulationDTO dto){
		if(simulationRepository.findByName(dto.getN()).isEmpty()) {
			Simulation simulation = addNewSimulationDto(dto);
			firstDay(simulation);
			covidCourse();
			persistSimulation(simulation);
			epidemicCourse = new ArrayList<>();
			return dailySimulationService.getDtoCourseById(simulation.getId());	
		}else {
			return new ArrayList<DailyDTO>();	
		}
	}
	
	public DailySimulation firstDay(Simulation initialData) {
		setFactors(initialData);
		DailySimulation dailySimulation = dailySimulationService.firstDayMapping(initialData);
		epidemicCourse.add(dailySimulation);
		return dailySimulation;
		
	}
	
	private void setFactors(Simulation simulation) {
		R = simulation.getTransmissionFactor();
		M = simulation.getMortalityRate();
		Ti = simulation.getRecoveryTime();
		Tm = simulation.getDeadTime();
		Ts = simulation.getDurationTime();
		P = simulation.getPopulationSize();
	}
	
	public void covidCourse() {
		boolean isSusceptible = true;
		for(int i = 1; i < Ts; i++) {
			long infected = 0;
			if(isSusceptible) {
				infected = currentInfected(i);
			}else {
				infected = currentInfectedDecrease(i);
			}
			long newInfected = newInfected(i);
			long susceptible = susceptibleCount(i, infected);
			if(susceptible <= 0) {
				isSusceptible = false;
				susceptible = 0;
				newInfected = 0;
			}
			
			long dailyDeaths = died(i);
			long deathsSum = formerDeaths(i)+ dailyDeaths;
			long recovered = recovered(i);
			if(infected > 0) {
				epidemicCourse.add(new DailySimulation(
						i + 1,
						infected - dailyDeaths - recovered,
						newInfected,
						susceptible,
						deathsSum,
						dailyDeaths,
						recoveredSum(i)
						));
			}else {
				isSusceptible = false;
			}
		}
	}
	
	public Simulation persistSimulation(Simulation simulation) {
		return dailySimulationService.persistSimulationCourse(simulation, epidemicCourse);
	}
	
	private Long currentInfected(int iter) {
		long formerInfected = epidemicCourse.get(iter - 1).getInfected();
		BigDecimal currentInfected = BigDecimal.valueOf(formerInfected).multiply(R);
		BigDecimal infectedRound = currentInfected.round(new MathContext(0));
		return infectedRound.longValue();
	}
	
	private Long currentInfectedDecrease(int iter) {
		return epidemicCourse.get(iter - 1).getInfected();
	}
	
	private Long newInfected(int iter) {
		long formerInfected = epidemicCourse.get(iter - 1).getInfected();
		BigDecimal currentInfected = BigDecimal.valueOf(formerInfected).multiply(R);
		BigDecimal infectedRound = currentInfected.round(new MathContext(0));
		return infectedRound.longValue() - formerInfected;
	}
	
	private Long susceptibleCount(int iter, long currentInfected) {
		DailySimulation ds = epidemicCourse.get(iter - 1);
		long formerInfected = ds.getInfected();
		long formerSusceptible = ds.getHealthySusceptible();
		return formerSusceptible + formerInfected - currentInfected;
	}
	
	private Long died(int iter) {
		if(iter > Tm - 1) {
			long infected = epidemicCourse.get(iter - Tm).getNewInfected();
			BigDecimal died = BigDecimal.valueOf(infected).multiply(M);
			BigDecimal diedRound = died.round(new MathContext(0));
			return diedRound.longValue();
		}
		return 0L;
	}
	
	private Long formerDeaths(int iter) {
		return epidemicCourse.get(iter - 1).getDied();
	}
	
	private Long recovered(int iter) {
		if(iter > Ti - 1) {
			long infected = epidemicCourse.get(iter - Ti).getNewInfected();
			BigDecimal died = BigDecimal.valueOf(infected).multiply(M);
			BigDecimal diedRound = died.round(new MathContext(0));
			return infected - diedRound.longValue();
		}
		return 0L;
	}
	
	private Long recoveredSum(int iter) {
		if(iter > Ti - 1) {
			long infected = epidemicCourse.get(iter - Ti).getNewInfected();
			long recovered = epidemicCourse.get(iter - 1).getRecoveredResistant();
			BigDecimal died = BigDecimal.valueOf(infected).multiply(M);
			BigDecimal diedRound = died.round(new MathContext(0));
			return recovered + infected - diedRound.longValue();
		}
		return 0L;
	}
	
	
	
	

}
