package pl.wpulik.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Simulation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_simulation")
	private Long id;
	private String name;
	private Long populationSize;
	private Long initialInfected;
	private BigDecimal transmissionFactor;
	private BigDecimal mortalityRate;
	private Integer recoveryTime;
	private Integer deadTime;
	private Integer durationTime;
	@OneToMany(mappedBy="simulation", fetch=FetchType.EAGER)
	private List <DailySimulation> dailySimulations = new ArrayList<>();
	
	public Simulation() {}

	public Simulation(String name, Long populationSize, Long initialInfected, BigDecimal transmissionFactor,
			BigDecimal mortalityRate, Integer recoveryTime, Integer deadTime, Integer durationTime) {
		this.name = name;
		this.populationSize = populationSize;
		this.initialInfected = initialInfected;
		this.transmissionFactor = transmissionFactor;
		this.mortalityRate = mortalityRate;
		this.recoveryTime = recoveryTime;
		this.deadTime = deadTime;
		this.durationTime = durationTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(Long populationSize) {
		this.populationSize = populationSize;
	}

	public Long getInitialInfected() {
		return initialInfected;
	}

	public void setInitialInfected(Long initialInfected) {
		this.initialInfected = initialInfected;
	}

	public BigDecimal getTransmissionFactor() {
		return transmissionFactor;
	}

	public void setTransmissionFactor(BigDecimal transmissionFactor) {
		this.transmissionFactor = transmissionFactor;
	}

	public BigDecimal getMortalityRate() {
		return mortalityRate;
	}

	public void setMortalityRate(BigDecimal mortalityRate) {
		this.mortalityRate = mortalityRate;
	}

	public Integer getRecoveryTime() {
		return recoveryTime;
	}

	public void setRecoveryTime(Integer recoveryTime) {
		this.recoveryTime = recoveryTime;
	}

	public Integer getDeadTime() {
		return deadTime;
	}

	public void setDeadTime(Integer deadTime) {
		this.deadTime = deadTime;
	}

	public Integer getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(Integer durationTime) {
		this.durationTime = durationTime;
	}

	public List<DailySimulation> getDailySimulation() {
		return dailySimulations;
	}

	public void setDailySimulation(List<DailySimulation> dailySimulation) {
		this.dailySimulations = dailySimulation;
	}

	@Override
	public String toString() {
		return "Simulation [id=" + id + ", name=" + name + ", populationSize=" + populationSize + ", initialInfected="
				+ initialInfected + ", transmissionFactor=" + transmissionFactor + ", mortalityRate=" + mortalityRate
				+ ", recoveryTime=" + recoveryTime + ", deadTime=" + deadTime + ", durationTime=" + durationTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deadTime == null) ? 0 : deadTime.hashCode());
		result = prime * result + ((durationTime == null) ? 0 : durationTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((initialInfected == null) ? 0 : initialInfected.hashCode());
		result = prime * result + ((mortalityRate == null) ? 0 : mortalityRate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((populationSize == null) ? 0 : populationSize.hashCode());
		result = prime * result + ((recoveryTime == null) ? 0 : recoveryTime.hashCode());
		result = prime * result + ((transmissionFactor == null) ? 0 : transmissionFactor.hashCode());
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
		Simulation other = (Simulation) obj;
		if (deadTime == null) {
			if (other.deadTime != null)
				return false;
		} else if (!deadTime.equals(other.deadTime))
			return false;
		if (durationTime == null) {
			if (other.durationTime != null)
				return false;
		} else if (!durationTime.equals(other.durationTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (initialInfected == null) {
			if (other.initialInfected != null)
				return false;
		} else if (!initialInfected.equals(other.initialInfected))
			return false;
		if (mortalityRate == null) {
			if (other.mortalityRate != null)
				return false;
		} else if (!mortalityRate.equals(other.mortalityRate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (populationSize == null) {
			if (other.populationSize != null)
				return false;
		} else if (!populationSize.equals(other.populationSize))
			return false;
		if (recoveryTime == null) {
			if (other.recoveryTime != null)
				return false;
		} else if (!recoveryTime.equals(other.recoveryTime))
			return false;
		if (transmissionFactor == null) {
			if (other.transmissionFactor != null)
				return false;
		} else if (!transmissionFactor.equals(other.transmissionFactor))
			return false;
		return true;
	}
	
	
	
	
	
}
