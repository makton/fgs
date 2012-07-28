package com.example.frisbeegolfscores;

public class Tulokset {

	private int id;
	private int pelaaja_id;
	private int rata_id;
	private int vayla_id;	
	private int kierros_id;
	private int tulos;	
	private String pvm;
	private String kesto;
	
	public Tulokset () {
	}
	
	public Tulokset(int pelaaja_id, int kierros_id, int tulos, String pvm) {
		this.pelaaja_id = pelaaja_id;
		this.kierros_id = kierros_id;
		this.tulos = tulos;
		this.pvm = pvm;
	}

	public Tulokset(int id, int pelaaja_id, int rata_id, int vayla_id,
			int kierros_id, int tulos, String pvm, String kesto) {
		this.id = id;
		this.pelaaja_id = pelaaja_id;
		this.rata_id = rata_id;
		this.vayla_id = vayla_id;
		this.kierros_id = kierros_id;
		this.tulos = tulos;
		this.pvm = pvm;
		this.kesto = kesto;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPelaaja_id() {
		return pelaaja_id;
	}
	
	public void setPelaaja_id(int pelaaja_id) {
		this.pelaaja_id = pelaaja_id;
	}
	
	public int getRata_id() {
		return rata_id;
	}
	
	public void setRata_id(int rata_id) {
		this.rata_id = rata_id;
	}
	
	public int getVayla_id() {
		return vayla_id;
	}
	
	public void setVayla_id(int vayla_id) {
		this.vayla_id = vayla_id;
	}
	
	public int getKierros_id() {
		return kierros_id;
	}
	
	public void setKierros_id(int kierros_id) {
		this.kierros_id = kierros_id;
	}
	
	public int getTulos() {
		return tulos;
	}
	
	public void setTulos(int tulos) {
		this.tulos = tulos;
	}
	
	public String getPvm() {
		return pvm;
	}
	
	public void setPvm(String pvm) {
		this.pvm = pvm;
	}
	
	public String getKesto() {
		return kesto;
	}
	
	public void setKesto(String kesto) {
		this.kesto = kesto;
	}
	
}
