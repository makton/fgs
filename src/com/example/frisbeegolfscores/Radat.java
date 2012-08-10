package com.example.frisbeegolfscores;

public class Radat {

	private int id;
	private int kaupunki_id;
	private String nimi;
	private String info;
	private String luokitus;
	private String osoite;
	private String sijainti;
	private int maksullinen;
	private int vaylia;
	private int par;
	private String kartta;
	private String kuvaus;
	private String linkki;
	private String paivityspvm;

	// Empty constructor
    public Radat(){
 
    }

	public Radat(String nimi) {
		this.nimi = nimi;
	}
	
	public Radat(int id, String nimi) {
		this.id = id;
		this.nimi = nimi;
	}

	public Radat(int kaupunki_id, String nimi, String info, String luokitus,
			String osoite, String sijainti, int maksullinen, int vaylia,
			int par, String kartta, String kuvaus, String linkki, String paivityspvm) {
		this.kaupunki_id = kaupunki_id;
		this.nimi = nimi;
		this.info = info;
		this.luokitus = luokitus;
		this.osoite = osoite;
		this.sijainti = sijainti;
		this.maksullinen = maksullinen;
		this.vaylia = vaylia;
		this.par = par;
		this.kartta = kartta;
		this.kuvaus = kuvaus;
		this.linkki = linkki;
		this.paivityspvm = paivityspvm;
	}

	public Radat(int id, int kaupunki_id, String nimi, String info, String luokitus,
			String osoite, String sijainti, int maksullinen, int vaylia,
			int par, String kartta, String kuvaus, String linkki, String paivityspvm) {
		this.id = id;
		this.kaupunki_id = kaupunki_id;
		this.nimi = nimi;
		this.info = info;
		this.luokitus = luokitus;
		this.osoite = osoite;
		this.sijainti = sijainti;
		this.maksullinen = maksullinen;
		this.vaylia = vaylia;
		this.par = par;
		this.kartta = kartta;
		this.kuvaus = kuvaus;
		this.linkki = linkki;
		this.paivityspvm = paivityspvm;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getKaupunki_id() {
		return kaupunki_id;
	}
	
	public void setKaupunki_id(int kaupunki_id) {
		this.kaupunki_id = kaupunki_id;
	}
	
	public String getNimi() {
		return nimi;
	}
	
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getLuokitus() {
		return luokitus;
	}
	
	public void setLuokitus(String luokitus) {
		this.luokitus = luokitus;
	}
	
	public String getOsoite() {
		return osoite;
	}
	
	public void setOsoite(String osoite) {
		this.osoite = osoite;
	}
	
	public String getSijainti() {
		return sijainti;
	}
	
	public void setSijainti(String sijainti) {
		this.sijainti = sijainti;
	}
	
	public int getMaksullinen() {
		return maksullinen;
	}
	
	public void setMaksullinen(int maksullinen) {
		this.maksullinen = maksullinen;
	}
	
	public int getVaylia() {
		return vaylia;
	}
	
	public void setVaylia(int vaylia) {
		this.vaylia = vaylia;
	}
	
	public int getPar() {
		return par;
	}
	
	public void setPar(int par) {
		this.par = par;
	}
	
	public String getKartta() {
		return kartta;
	}
	
	public void setKartta(String kartta) {
		this.kartta = kartta;
	}
	
	public String getKuvaus() {
		return kuvaus;
	}
	
	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public String getLinkki() {
		return linkki;
	}
	
	public void setLinkki(String linkki) {
		this.linkki = linkki;
	}
	
	public String getPaivityspvm() {
		return paivityspvm;
	}
	
	public void setPaivityspvm(String paivityspvm) {
		this.paivityspvm = paivityspvm;
	}
	
}
