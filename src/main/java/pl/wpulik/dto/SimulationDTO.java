package pl.wpulik.dto;

import java.io.Serializable;

public class SimulationDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String N;
	private Long P;
	private Long I;
	private Double R;
	private Double M;
	private Integer Ti;
	private Integer Tm;
	private Integer Ts;
	
	
	public SimulationDTO(){}


	public String getN() {
		return N;
	}


	public void setN(String n) {
		N = n;
	}


	public Long getP() {
		return P;
	}


	public void setP(Long p) {
		P = p;
	}


	public Long getI() {
		return I;
	}


	public void setI(Long i) {
		I = i;
	}


	public Double getR() {
		return R;
	}


	public void setR(Double r) {
		R = r;
	}


	public Double getM() {
		return M;
	}


	public void setM(Double m) {
		M = m;
	}


	public Integer getTi() {
		return Ti;
	}


	public void setTi(Integer ti) {
		Ti = ti;
	}


	public Integer getTm() {
		return Tm;
	}


	public void setTm(Integer tm) {
		Tm = tm;
	}


	public Integer getTs() {
		return Ts;
	}


	public void setTs(Integer ts) {
		Ts = ts;
	}


	@Override
	public String toString() {
		return "SimulationDTO [N=" + N + ", P=" + P + ", I=" + I + ", R=" + R + ", M=" + M + ", Ti=" + Ti + ", Tm=" + Tm
				+ ", Ts=" + Ts + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (I ^ (I >>> 32));
		long temp;
		temp = Double.doubleToLongBits(M);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((N == null) ? 0 : N.hashCode());
		result = prime * result + (int) (P ^ (P >>> 32));
		temp = Double.doubleToLongBits(R);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Ti;
		result = prime * result + Tm;
		result = prime * result + Ts;
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
		SimulationDTO other = (SimulationDTO) obj;
		if (I != other.I)
			return false;
		if (Double.doubleToLongBits(M) != Double.doubleToLongBits(other.M))
			return false;
		if (N == null) {
			if (other.N != null)
				return false;
		} else if (!N.equals(other.N))
			return false;
		if (P != other.P)
			return false;
		if (Double.doubleToLongBits(R) != Double.doubleToLongBits(other.R))
			return false;
		if (Ti != other.Ti)
			return false;
		if (Tm != other.Tm)
			return false;
		if (Ts != other.Ts)
			return false;
		return true;
	}
	
	
	
	
	

}
