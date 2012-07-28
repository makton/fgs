package com.example.frisbeegolfscores;

public class Joukkueet {

	private int id;
	private String nimi;

	public Joukkueet() {
	}

	public Joukkueet(String nimi) {
		this.nimi = nimi;
	}

	public Joukkueet(int id, String nimi) {
		this.id = id;
		this.nimi = nimi;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNimi() {
		return nimi;
	}
	
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

}
