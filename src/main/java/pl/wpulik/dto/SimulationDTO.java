package pl.wpulik.dto;

import java.io.Serializable;

public class SimulationDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String N;
	private long P;
	private long I;
	private double R;
	private double M;
	private int Ti;
	private int Tm;
	private int Ts;
	
	
	public SimulationDTO(){}


	public String getN() {
		return N;
	}


	public void setN(String n) {
		N = n;
	}


	public long getP() {
		return P;
	}


	public void setP(long p) {
		P = p;
	}


	public long getI() {
		return I;
	}


	public void setI(long i) {
		I = i;
	}


	public double getR() {
		return R;
	}


	public void setR(double r) {
		R = r;
	}


	public double getM() {
		return M;
	}


	public void setM(double m) {
		M = m;
	}


	public int getTi() {
		return Ti;
	}


	public void setTi(int ti) {
		Ti = ti;
	}


	public int getTm() {
		return Tm;
	}


	public void setTm(int tm) {
		Tm = tm;
	}


	public int getTs() {
		return Ts;
	}


	public void setTs(int ts) {
		Ts = ts;
	}


	@Override
	public String toString() {
		return "SimulationDTO [N=" + N + ", P=" + P + ", I=" + I + ", R=" + R + ", M=" + M + ", Ti=" + Ti + ", Tm=" + Tm
				+ ", Ts=" + Ts + "]";
	}
	
	
	
	
	

}
