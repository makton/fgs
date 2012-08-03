package com.example.frisbeegolfscores;

public class Asetukset {

	private int id;
	// sisäiset määritykset
	private String nimi;
	private String versio;
	private int db_versio;
	// yleiset asetukset
	private boolean vuoronvaihto; // miten vaihdetaan vuoro, automaattisesti, manuaalisesti, tms
	private boolean pelijarjestys; // miten määritellään pelijärjestys? random, handicap, tms
	private int kieli; // ohjelman kieli
	private boolean metric; // ohjelman käyttämä merijärjestelmä
	private int raportinmuoto; // html, csv, tms
	private boolean useGPS; // GPS käytössä?
	
	public Asetukset() {
	}

	public Asetukset(String nimi, String versio, int db_versio) {
		this.nimi = nimi;
		this.versio = versio;
		this.db_versio = db_versio;
	}
	
	public Asetukset(String nimi, String versio, int db_versio,
			boolean vuoronvaihto, boolean pelijarjestys, int kieli, boolean metric, int raportinmuoto, boolean useGPS) {
		this.nimi = nimi;
		this.versio = versio;
		this.db_versio = db_versio;
		this.vuoronvaihto = vuoronvaihto;
		this.pelijarjestys = pelijarjestys;
		this.kieli = kieli;
		this.metric = metric;
		this.raportinmuoto = raportinmuoto;
		this.useGPS = useGPS;
	}

	public Asetukset(int id, String nimi, String versio, int db_versio,
			boolean vuoronvaihto, boolean pelijarjestys, int kieli, boolean metric, int raportinmuoto, boolean useGPS) {
		this.id = id;
		this.nimi = nimi;
		this.versio = versio;
		this.db_versio = db_versio;
		this.vuoronvaihto = vuoronvaihto;
		this.pelijarjestys = pelijarjestys;
		this.kieli = kieli;
		this.metric = metric;
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

	public boolean getVuoronvaihto() {
		return vuoronvaihto;
	}

	public void setVuoronvaihto(boolean vuoronvaihto) {
		this.vuoronvaihto = vuoronvaihto;
	}

	public boolean getPelijarjestys() {
		return pelijarjestys;
	}

	public void setPelijarjestys(boolean pelijarjestys) {
		this.pelijarjestys = pelijarjestys;
	}

	public int getKieli() {
		return kieli;
	}

	public void setKieli(int kieli) {
		this.kieli = kieli;
	}

	public boolean getMetric() {
		return metric;
	}

	public void setMetric(boolean metric) {
		this.metric = metric;
	}

	public int getRaportinmuoto() {
		return raportinmuoto;
	}

	public void setRaportinmuoto(int raportinmuoto) {
		this.raportinmuoto = raportinmuoto;
	}

	public boolean getUseGPS() {
		return useGPS;
	}
	
	public void setUseGPS(boolean useGPS) {
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
