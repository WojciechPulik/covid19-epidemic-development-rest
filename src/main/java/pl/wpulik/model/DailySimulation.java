package pl.wpulik.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DailySimulation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_daily_sim")
	private Long id;
	private Integer dayOfSymulation;
	private Long currentPopulation;
	private Long infected;
	private Long newInfected;
	private Long healthySusceptible;
	private Long died;
	private Long dailyDeaths;
	private Long recoveredResistant;
	@ManyToOne
	@JoinColumn(name="simulation_id")
	private Simulation simulation;
	
	public DailySimulation() {}
	
	public DailySimulation(Integer dayOfSymulation, Long currentPopulation, Long infected, Long newInfected, 
			Long healthySusceptible, Long died, Long dailyDeaths, Long recoveredResistant) {
		this.dayOfSymulation = dayOfSymulation;
		this.currentPopulation = currentPopulation;
		this.infected = infected;
		this.newInfected = newInfected;
		this.healthySusceptible = healthySusceptible;
		this.died = died;
		this.dailyDeaths = dailyDeaths;
		this.recoveredResistant = recoveredResistant;
	}

	
	public Long getNewInfected() {
		return newInfected;
	}

	public void setNewInfected(Long newInfected) {
		this.newInfected = newInfected;
	}

	public Long getDailyDeaths() {
		return dailyDeaths;
	}

	public void setDailyDeaths(Long dailyDeaths) {
		this.dailyDeaths = dailyDeaths;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDayOfSymulation() {
		return dayOfSymulation;
	}

	public void setDayOfSymulation(Integer dayOfSymulation) {
		this.dayOfSymulation = dayOfSymulation;
	}

	public Long getCurrentPopulation() {
		return currentPopulation;
	}

	public void setCurrentPopulation(Long currentPopulation) {
		this.currentPopulation = currentPopulation;
	}

	public Long getInfected() {
		return infected;
	}

	public void setInfected(Long infected) {
		this.infected = infected;
	}

	public Long getHealthySusceptible() {
		return healthySusceptible;
	}

	public void setHealthySusceptible(Long healthySusceptible) {
		this.healthySusceptible = healthySusceptible;
	}

	public Long getDied() {
		return died;
	}

	public void setDied(Long died) {
		this.died = died;
	}

	public Long getRecoveredResistant() {
		return recoveredResistant;
	}

	public void setRecoveredResistant(Long recoveredResistant) {
		this.recoveredResistant = recoveredResistant;
	}

	public Simulation getSimulation() {
		return simulation;
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

	@Override
	public String toString() {
		return "dayOfSymulation=" + dayOfSymulation + ", infected=" + infected
				+ ", healthySusceptible=" + healthySusceptible + ", died=" + died + ", recoveredResistant="
				+ recoveredResistant;
	}

	
	
	
	
	
	
}
