package com.example.frisbeegolfscores;

public class Kaupungit {

	private int id;
	private String nimi;
	private String paivityspvm;

	// Empty constructor
    public Kaupungit(){
 
    }
    
    // constructor
    public Kaupungit(String nimi){
        this.nimi = nimi;
    }
    
	public Kaupungit(int id, String nimi, String paivityspvm) {
		super();
		this.id = id;
		this.nimi = nimi;
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
	
	public String getPaivityspvm() {
		return paivityspvm;
	}
	
	public void setPaivityspvm(String paivityspvm) {
		this.paivityspvm = paivityspvm;
	}

}
