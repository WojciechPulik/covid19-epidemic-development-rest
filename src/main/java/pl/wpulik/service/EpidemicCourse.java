package pl.wpulik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

import pl.wpulik.model.Simulation;
import pl.wpulik.model.DailySimulation;

@Service
public class EpidemicCourse { 
	
	private static BigDecimal R;
	private static BigDecimal M;
	private static Integer Ti;
	private static Integer Tm;
	private static Integer Ts;
	
	private SimulationService simulationService;
	private DailySimulationService dailySimulationService;
	private List<DailySimulation> epidemicCourse = new ArrayList<>();
	
	public EpidemicCourse() {}

	@Autowired
	public EpidemicCourse(SimulationService simulationService, DailySimulationService dailySimulationService) {
		this.simulationService = simulationService;
		this.dailySimulationService = dailySimulationService;
	}
	
	public Simulation addNewSimulation(Simulation simulation) {
		return simulationService.save(simulation);
	}
	
	public DailySimulation firstDay(Simulation initialData) {
		setFactors(initialData);
		Simulation simulation = addNewSimulation(initialData);
		DailySimulation dailySimulation = dailySimulationService.firstDayMapping(simulation);
		epidemicCourse.add(dailySimulation);
		return dailySimulation;
		
	}
	
	private void setFactors(Simulation simulation) {
		R = simulation.getTransmissionFactor();
		M = simulation.getMortalityRate();
		Ti = simulation.getRecoveryTime();
		Tm = simulation.getDeadTime();
		Ts = simulation.getDurationTime();
	}
	
	public List<DailySimulation> dailyCovidCourse(){
		return epidemicCourse;
	}
	/*
	 * DailySimulation:
	 * (Integer dayOfSymulation, Long currentPopulation, Long infected, Long newInfected, 
			Long healthySusceptible, Long died, Long dailyDeaths, Long recoveredResistant)
	 */
	public void covidCourse() {
		for(int i = 1; i < Ts; i++) {
			long infected = currentInfected(i);
			long newInfected = newInfected(i);
			long susceptible = susceptibleCount(i, infected);
			long dailyDeaths = died(i);
			long deathsSum = formerDeaths(i)+ dailyDeaths;
			long recovered = recovered(i);
			epidemicCourse.add(new DailySimulation(
					i + 1,
					-3L, //to remove?
					infected - dailyDeaths,
					newInfected,
					susceptible,
					deathsSum,
					dailyDeaths,
					recovered
					));
		}
	}
	
	private Long currentInfected(int iter) {
		long formerInfected = epidemicCourse.get(iter - 1).getInfected();
		BigDecimal currentInfected = BigDecimal.valueOf(formerInfected).multiply(R);
		BigDecimal infectedRound = currentInfected.round(new MathContext(0));
		return infectedRound.longValue();
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
	
	
	
	

}
