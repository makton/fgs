package com.example.frisbeegolfscores;

public class Kierros {

	private int id;
	private int rata_id;
	private String pvm;
	
	public Kierros () {
	}

	public Kierros(int rata_id, String pvm) {
		this.rata_id = rata_id;
		this.pvm = pvm;
	}
	
	public Kierros(int id, int rata_id, String pvm) {
		this.id = id;
		this.rata_id = rata_id;
		this.pvm = pvm;
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
	
	public String getPvm() {
		return pvm;
	}
	
	public void setPvm(String pvm) {
		this.pvm = pvm;
	}
	
}
