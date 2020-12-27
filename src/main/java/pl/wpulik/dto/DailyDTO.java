package pl.wpulik.dto;

import java.io.Serializable;

public class DailyDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long Pi;
	private long Pv;
	private long Pm;
	private long Pr;
	
	public DailyDTO() {}

	public long getPi() {
		return Pi;
	}

	public void setPi(long pi) {
		Pi = pi;
	}

	public long getPv() {
		return Pv;
	}

	public void setPv(long pv) {
		Pv = pv;
	}

	public long getPm() {
		return Pm;
	}

	public void setPm(long pm) {
		Pm = pm;
	}

	public long getPr() {
		return Pr;
	}

	public void setPr(long pr) {
		Pr = pr;
	}

	@Override
	public String toString() {
		return "DailyDTO [Pi=" + Pi + ", Pv=" + Pv + ", Pm=" + Pm + ", Pr=" + Pr + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Pi ^ (Pi >>> 32));
		result = prime * result + (int) (Pm ^ (Pm >>> 32));
		result = prime * result + (int) (Pr ^ (Pr >>> 32));
		result = prime * result + (int) (Pv ^ (Pv >>> 32));
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
		DailyDTO other = (DailyDTO) obj;
		if (Pi != other.Pi)
			return false;
		if (Pm != other.Pm)
			return false;
		if (Pr != other.Pr)
			return false;
		if (Pv != other.Pv)
			return false;
		return true;
	}
	
	
	

}
