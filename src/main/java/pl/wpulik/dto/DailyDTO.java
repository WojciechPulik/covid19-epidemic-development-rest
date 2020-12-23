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
	
	
	

}
