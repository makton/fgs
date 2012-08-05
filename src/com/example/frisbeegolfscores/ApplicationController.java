package com.example.frisbeegolfscores;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.util.Log;

public class ApplicationController extends Application {
	//Application wide instance variables
	private static ApplicationController singleton = null;
	private Context appContext;
	
	private SQLiteDatabase database;
	private DBAvaus dbAvaus;  
    
    //Yleiset muuttujat
    //public int[] aktiivisetPelaajat;
    List<Integer> aktiivisetPelaajat = new ArrayList<Integer>();
    private int aktiivinenRata = 0;
    private int aktiivinenVayla = 0;

	@Override
	public void onCreate() {
		super.onCreate();
		
	    //mŠŠritellŠŠn context
		singleton = this;
	    appContext = this.getApplicationContext();
	    
	    //avataan kantayhteys
		openDB();
		
		//mŠŠritellŠŠn asetukset
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(singleton);
	}
	
	public ApplicationController getInstance() {
		if (singleton == null) {
			singleton = new ApplicationController();
		}
		return singleton;
	}
	
	public Context getAppInstance() {
		return appContext;
	}

	public void openDB() {
        dbAvaus = new DBAvaus(appContext);
        if (!dbAvaus.status()){
	        database = dbAvaus.open();
        }
	}
	
	public void closeDB() {
        if (database != null && dbAvaus.status()){
        	dbAvaus.close();
        }		
	}

	public SQLiteDatabase getDBInstance() {
		return database;
	}
	
	public boolean getDBstatus() {
		if (database == null) {
			return false;
		}
		return database.isOpen();
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
