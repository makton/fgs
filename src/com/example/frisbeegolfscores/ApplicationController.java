package com.example.frisbeegolfscores;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class ApplicationController extends Application {
	//Application wide instance variables
	private static ApplicationController singleton = null;
	private Context appContext;
	
	public Asetukset asetukset;
    public SQLiteDatabase database;
    public DBAvaus dbAvaus;
    public DBAsetukset dbAsetukset;  
    
    //Yleiset muuttujat
    //public int[] aktiivisetPelaajat;
    List<Integer> aktiivisetPelaajat = new ArrayList<Integer>();
    private int aktiivinenRata = 0;
    private int aktiivinenVayla = 0;

	//@Override
	public void onCreate(Bundle b) {
		super.onCreate();
	
	    //mŠŠritellŠŠn context
		singleton = this;
	    appContext = this.getApplicationContext();
	}
/*
	protected ApplicationController() {	
	}*/
	
	public ApplicationController getInstance() {
		if (singleton == null) {
			singleton = new ApplicationController();
		}
		return singleton;
	}
	
	public Context getAppInstance(){
		return appContext;
	}

	public void openDB(){
        dbAvaus = new DBAvaus(appContext);
        if (!dbAvaus.status()){
	        database = dbAvaus.open();
        }		
	}
	
	public void closeDB() {
        if (dbAvaus.status()){
        	dbAvaus.close();
        }		
	}
	
	public void openDBAsetukset(){
        DBAsetukset dbAsetukset = new DBAsetukset(this);	
        dbAsetukset = new DBAsetukset(appContext);
		dbAsetukset.setDBInstance(database);
	}
	
	public void openDBPelaajat(){
        DBPelaajat dbPelaajat = new DBPelaajat(this);
        dbPelaajat = new DBPelaajat(appContext);
        dbPelaajat.setDBInstance(database);
	}

	public void openDBJoukkueet(){
        DBJoukkueet dbJoukkueet = new DBJoukkueet(this);
        dbJoukkueet = new DBJoukkueet(appContext);
        dbJoukkueet.setDBInstance(database);
	}

	public void openDBKaupungit(){
        DBKaupungit dbKaupungit = new DBKaupungit(this);
        dbKaupungit = new DBKaupungit(appContext);
        dbKaupungit.setDBInstance(database);
	}

	public void openDBRadat(){
        DBRadat dbRadat = new DBRadat(this);
        dbRadat = new DBRadat(appContext);
        dbRadat.setDBInstance(database);
	}

	public void openDBVaylat(){
        DBVaylat dbVaylat = new DBVaylat(this);
        dbVaylat = new DBVaylat(appContext);
        dbVaylat.setDBInstance(database);
	}

	public void openDBKierros(){
        DBKierros dbKierros = new DBKierros(this);
        dbKierros = new DBKierros(appContext);
        dbKierros.setDBInstance(database);
	}

	public SQLiteDatabase getDBInstance() {
		return database;
	}
	
	public void setDBInstance(SQLiteDatabase database) {
		this.database = database;
	}
	
	public boolean getDBstatus() {
		if (database == null) {
			return false;
		}
		return database.isOpen();
	}
	
	public void closeDBInstance() {
		if (database != null && database.isOpen()) {
			dbAvaus.close();
		}
	}
	
    public List<Integer> getAktiivisetPelaajat() {
		return aktiivisetPelaajat;
	}

	public void addAktiivisetPelaajat(int pelaaja) {
		this.aktiivisetPelaajat.add(pelaaja);
	}

	public void deleteAktiivisetPelaajat(int pelaaja) {
		this.aktiivisetPelaajat.remove(pelaaja);
	}

	public void clearAktiivisetPelaajat() {
		this.aktiivisetPelaajat.clear();
	}

	public int getAktiivinenRata() {
		return aktiivinenRata;
	}

	public void setAktiivinenRata(int aktiivinenRata) {
		this.aktiivinenRata = aktiivinenRata;
	}

	public int getAktiivinenVayla() {
		return aktiivinenVayla;
	}

	public void setAktiivinenVayla(int aktiivinenVayla) {
		this.aktiivinenVayla = aktiivinenVayla;
	}

}
