package pl.wpulik.dto;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import pl.wpulik.model.DailySimulation;
import pl.wpulik.model.Simulation;

@Service
public class DTOMapper {
	
	public DTOMapper() {}
	
	/*
	private String N;
	private long P; population
	private long I; initialy infected
	private double R;
	private double M;
	private int Ti; time to recover
	private int Tm; time to death
	private int Ts; duration
	 * 
	 */
	
	public Simulation toSimulationMapping(SimulationDTO dto) {
		Simulation simulation = new Simulation();
		simulation.setName(dto.getN());
		simulation.setPopulationSize(dto.getP());
		simulation.setInitialInfected(dto.getI());
		simulation.setTransmissionFactor(BigDecimal.valueOf(dto.getR()));
		simulation.setMortalityRate(BigDecimal.valueOf(dto.getM()));
		simulation.setRecoveryTime(dto.getTi());
		simulation.setDeadTime(dto.getTm());
		simulation.setDurationTime(dto.getTs());
		return simulation;
	}
	/*
	private String name;
	private Long populationSize;
	private Long initialInfected;
	private BigDecimal transmissionFactor;
	private BigDecimal mortalityRate;
	private Integer recoveryTime;
	private Integer deadTime;
	private Integer durationTime;
	 */
	
	public SimulationDTO toSimulationDTOMapping(Simulation simulation) {
		SimulationDTO dto = new SimulationDTO();
		dto.setN(simulation.getName());
		dto.setP(simulation.getPopulationSize());
		dto.setI(simulation.getInitialInfected());
		dto.setR(simulation.getTransmissionFactor().doubleValue());
		dto.setM(simulation.getMortalityRate().doubleValue());
		dto.setTi(simulation.getRecoveryTime());
		dto.setTm(simulation.getDeadTime());
		dto.setTs(simulation.getDurationTime());	
		return dto;
	}
	
	public DailySimulation toDailyMapping(DailyDTO dto) {
		DailySimulation ds = new DailySimulation();
		
		//TODO: to implement
		return ds;
	}
	
	
	public DailyDTO toDailyDTOMapping(DailySimulation ds) {
		DailyDTO dto = new DailyDTO();
		dto.setPi(ds.getInfected());
		dto.setPv(ds.getHealthySusceptible());
		dto.setPm(ds.getDied());
		dto.setPr(ds.getRecoveredResistant());
		return dto;
	}
	

}
