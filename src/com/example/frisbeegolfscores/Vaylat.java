package com.example.frisbeegolfscores;

public class Vaylat {

	private int id;
	private int rata_id;
	private int jarjestys;
	private int pituus;
	private int par;
	private String kuvaus;
	private String paivityspvm;
	
	public Vaylat() {
	}
	
	public Vaylat(int rata_id, int jarjestys) {
		this.rata_id = rata_id;
		this.jarjestys = jarjestys;
	}

	public Vaylat(int id, int rata_id, int jarjestys, int pituus, int par, String kuvaus, String paivityspvm) {
		this.id = id;
		this.rata_id = rata_id;
		this.jarjestys = jarjestys;
		this.pituus = pituus;
		this.par = par;
		this.kuvaus = kuvaus;
		this.paivityspvm = paivityspvm;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getRata_id() {
		return rata_id;
	}
	
	public void setRata_id(int rata_id) {
		this.rata_id = rata_id;
	}
	
	public int getJarjestys() {
		return jarjestys;
	}
	
	public void setJarjestys(int jarjestys) {
		this.jarjestys = jarjestys;
	}
	
	public int getPituus() {
		return pituus;
	}
	
	public void setPituus(int pituus) {
		this.pituus = pituus;
	}
	
	public int getPar() {
		return par;
	}
	
	public void setPar(int par) {
		this.par = par;
	}
	
	public String getKuvaus() {
		return kuvaus;
	}
	
	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}
	
	public String getPaivityspvm() {
		return paivityspvm;
	}
	
	public void setPaivityspvm(String paivityspvm) {
		this.paivityspvm = paivityspvm;
	}
	
}
