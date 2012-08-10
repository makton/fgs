package com.example.frisbeegolfscores;

public class HtmlData {
	public String id;
    public String luokitus;
    public String rata;
    public String link;
    public String kaupunki;
    public String vaylia;
    public String kartta;

    public HtmlData() {
    	
    }
    
    public HtmlData(String id, String luokitus, String rata, String link, String kaupunki, String vaylia, String kartta) {
    	this.id = id;
    	this.luokitus = luokitus;
        this.rata = rata;
        this.link = link;
        this.kaupunki = kaupunki;
        this.vaylia = vaylia;
        this.kartta = kartta;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLuokitus() {
		return luokitus;
	}

	public void setLuokitus(String luokitus) {
		this.luokitus = luokitus;
	}

	public String getRata() {
		return rata;
	}

	public void setRata(String rata) {
		this.rata = rata;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getKaupunki() {
		return kaupunki;
	}

	public void setKaupunki(String kaupunki) {
		this.kaupunki = kaupunki;
	}

	public String getVaylia() {
		return vaylia;
	}

	public void setVaylia(String vaylia) {
		this.vaylia = vaylia;
	}

	public String getKartta() {
		return kartta;
	}

	public void setKartta(String kartta) {
		this.kartta = kartta;
	}

}