package pl.wpulik.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private Long infected;
	private Long newInfected;
	private Long healthySusceptible;
	private Long died;
	private Long dailyDeaths;
	private Long recoveredResistant;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="simulation_id")
	private Simulation simulation;
	
	public DailySimulation() {}
	
	public DailySimulation(Integer dayOfSymulation, Long infected, Long newInfected, 
			Long healthySusceptible, Long died, Long dailyDeaths, Long recoveredResistant) {
		this.dayOfSymulation = dayOfSymulation;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dailyDeaths == null) ? 0 : dailyDeaths.hashCode());
		result = prime * result + ((dayOfSymulation == null) ? 0 : dayOfSymulation.hashCode());
		result = prime * result + ((died == null) ? 0 : died.hashCode());
		result = prime * result + ((healthySusceptible == null) ? 0 : healthySusceptible.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((infected == null) ? 0 : infected.hashCode());
		result = prime * result + ((newInfected == null) ? 0 : newInfected.hashCode());
		result = prime * result + ((recoveredResistant == null) ? 0 : recoveredResistant.hashCode());
		result = prime * result + ((simulation == null) ? 0 : simulation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailySimulation other = (DailySimulation) obj;
		if (dailyDeaths == null) {
			if (other.dailyDeaths != null)
				return false;
		} else if (!dailyDeaths.equals(other.dailyDeaths))
			return false;
		if (dayOfSymulation == null) {
			if (other.dayOfSymulation != null)
				return false;
		} else if (!dayOfSymulation.equals(other.dayOfSymulation))
			return false;
		if (died == null) {
			if (other.died != null)
				return false;
		} else if (!died.equals(other.died))
			return false;
		if (healthySusceptible == null) {
			if (other.healthySusceptible != null)
				return false;
		} else if (!healthySusceptible.equals(other.healthySusceptible))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (infected == null) {
			if (other.infected != null)
				return false;
		} else if (!infected.equals(other.infected))
			return false;
		if (newInfected == null) {
			if (other.newInfected != null)
				return false;
		} else if (!newInfected.equals(other.newInfected))
			return false;
		if (recoveredResistant == null) {
			if (other.recoveredResistant != null)
				return false;
		} else if (!recoveredResistant.equals(other.recoveredResistant))
			return false;
		if (simulation == null) {
			if (other.simulation != null)
				return false;
		} else if (!simulation.equals(other.simulation))
			return false;
		return true;
	}
	
	

	
	
	
	
	
	
}
