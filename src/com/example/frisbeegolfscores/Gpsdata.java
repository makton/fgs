package com.example.frisbeegolfscores;

public class Gpsdata {

	private long id;
	private String x;
	private String y;
	private int tulokset_id;
	private String pvm;

	public Gpsdata() {
	}
	
	public Gpsdata(String x, String y, String pvm) {
		this.x = x;
		this.y = y;
		this.pvm = pvm;
	}

	public Gpsdata(String x, String y, int tulokset_id, String pvm) {
		this.x = x;
		this.y = y;
		this.tulokset_id = tulokset_id;
		this.pvm = pvm;
	}

	public Gpsdata(long id, String x, String y, int tulokset_id, String pvm) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.tulokset_id = tulokset_id;
		this.pvm = pvm;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getX() {
		return x;
	}
	
	public void setX(String x) {
		this.x = x;
	}
	
	public String getY() {
		return y;
	}
	
	public void setY(String y) {
		this.y = y;
	}
	
	public int getTulokset_id() {
		return tulokset_id;
	}
	
	public void setTulokset_id(int tulokset_id) {
		this.tulokset_id = tulokset_id;
	}
	
	public String getPvm() {
		return pvm;
	}
	
	public void setPvm(String pvm) {
		this.pvm = pvm;
	}
		
}
