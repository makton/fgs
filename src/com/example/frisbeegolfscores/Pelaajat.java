package com.example.frisbeegolfscores;

import android.util.Log;

public class Pelaajat {

	private int id;
	private String nimi;
	private String nayttonimi;
	private String info;
	private String kayttajatunnus;
	private String salasana;
	private int joukkue;
	private String luontipvm;
	private String paivityspvm;	

	// Empty constructor
    public Pelaajat(){
 
    }

    // constructor
    public Pelaajat(String nimi){
        this.nimi = nimi;
    }

 // constructor
    public Pelaajat(int id, String nimi){
    	Log.d("Pelaajat: ", "getNimi()..");
        this.id = id;
        this.nimi = nimi;
    }
 
    // constructor
    public Pelaajat(String kayttajatunnus, String salasana){
        this.kayttajatunnus = kayttajatunnus;
        this.salasana = salasana;
    }
    
    // constructor
    public Pelaajat(int id, String nimi, String nayttonimi, String info, String kayttajatunnus, String salasana, int joukkue, String luontipvm, String paivityspvm){
    	this.id = id;
        this.nimi = nimi;
        this.nayttonimi = nayttonimi;
        this.info = info;
        this.kayttajatunnus = kayttajatunnus;
        this.salasana = salasana;
        this.joukkue = joukkue;
        this.luontipvm = luontipvm;
        this.paivityspvm = paivityspvm;
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

	public String getNayttonimi() {
		return nayttonimi;
	}

	public void setNayttonimi(String nayttonimi) {
		this.nayttonimi = nayttonimi;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getKayttajatunnus() {
		return kayttajatunnus;
	}

	public void setKayttajatunnus(String kayttajatunnus) {
		this.kayttajatunnus = kayttajatunnus;
	}

	public String getSalasana() {
		return salasana;
	}

	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}

    public int getJoukkue() {
		return joukkue;
	}

	public void setJoukkue(int joukkue) {
		this.joukkue = joukkue;
	}
	
	public String getLuontipvm() {
		return luontipvm;
	}

	public void setLuontipvm(String luontipvm) {
		this.luontipvm = luontipvm;
	}

	public String getPaivityspvm() {
		return paivityspvm;
	}

	public void setPaivityspvm(String paivityspvm) {
		this.paivityspvm = paivityspvm;
	}

}
