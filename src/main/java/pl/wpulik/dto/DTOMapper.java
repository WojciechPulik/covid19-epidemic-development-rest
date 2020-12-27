package pl.wpulik.dto;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import pl.wpulik.model.DailySimulation;
import pl.wpulik.model.Simulation;

@Service
public class DTOMapper {
	
	public DTOMapper() {}
	
	
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
	
	
	public SimulationDTO toSimulationDTOMapping(Simulation simulation) {
		SimulationDTO dto = new SimulationDTO();
		dto.setN(simulation.getName());
		dto.setP(simulation.getPopulationSize());
		dto.setI(simulation.getInitialInfected());
		if(simulation.getTransmissionFactor()!=null) {
			dto.setR(simulation.getTransmissionFactor().doubleValue());
		}else {
			dto.setR(0.0);
		}
		if(simulation.getMortalityRate()!=null) {
			dto.setM(simulation.getMortalityRate().doubleValue());
		}else {
			dto.setM(0.0);
		}
		dto.setTi(simulation.getRecoveryTime());
		dto.setTm(simulation.getDeadTime());
		dto.setTs(simulation.getDurationTime());	
		return dto;
	}
	
	public DailySimulation toDailyMapping(DailyDTO dto) {
		DailySimulation ds = new DailySimulation();
		
		//TODO: implement if needed
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
