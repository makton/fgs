package com.example.frisbeegolfscores;

public class Asetukset {

	private int id;
	// sisäiset määritykset
	private String nimi;
	private String versio;
	private int db_versio;
	// yleiset asetukset
	private int vuoronvaihto; // miten vaihdetaan vuoro, automaattisesti, manuaalisesti, tms
	private int pelijarjestys; // miten määritellään pelijärjestys? random, handicap, tms
	private int kieli; // ohjelman kieli
	private int raportinmuoto; // html, csv, tms
	private int useGPS; // GPS käytössä?
	
	public Asetukset() {
	}

	public Asetukset(String nimi, String versio, int db_versio) {
		this.nimi = nimi;
		this.versio = versio;
		this.db_versio = db_versio;
	}
	
	public Asetukset(String nimi, String versio, int db_versio,
			int vuoronvaihto, int pelijarjestys, int kieli, int raportinmuoto, int useGPS) {
		this.nimi = nimi;
		this.versio = versio;
		this.db_versio = db_versio;
		this.vuoronvaihto = vuoronvaihto;
		this.pelijarjestys = pelijarjestys;
		this.kieli = kieli;
		this.raportinmuoto = raportinmuoto;
		this.useGPS = useGPS;
	}

	public Asetukset(int id, String nimi, String versio, int db_versio,
			int vuoronvaihto, int pelijarjestys, int kieli, int raportinmuoto, int useGPS) {
		this.id = id;
		this.nimi = nimi;
		this.versio = versio;
		this.db_versio = db_versio;
		this.vuoronvaihto = vuoronvaihto;
		this.pelijarjestys = pelijarjestys;
		this.kieli = kieli;
		this.raportinmuoto = raportinmuoto;
		this.useGPS = useGPS;
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
	
	public String getVersio() {
		return versio;
	}
	
	public void setVersio(String versio) {
		this.versio = versio;
	}
	
	public int getDb_versio() {
		return db_versio;
	}
	
	public void setDb_versio(int db_versio) {
		this.db_versio = db_versio;
	}

	public int getVuoronvaihto() {
		return vuoronvaihto;
	}

	public void setVuoronvaihto(int vuoronvaihto) {
		this.vuoronvaihto = vuoronvaihto;
	}

	public int getPelijarjestys() {
		return pelijarjestys;
	}

	public void setPelijarjestys(int pelijarjestys) {
		this.pelijarjestys = pelijarjestys;
	}

	public int getKieli() {
		return kieli;
	}

	public void setKieli(int kieli) {
		this.kieli = kieli;
	}

	public int getRaportinmuoto() {
		return raportinmuoto;
	}

	public void setRaportinmuoto(int raportinmuoto) {
		this.raportinmuoto = raportinmuoto;
	}

	public int getUseGPS() {
		return useGPS;
	}
	
	public void setUseGPS(int useGPS) {
		this.useGPS = useGPS;
	}

/*
	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return comment;
	}
	*/
}
