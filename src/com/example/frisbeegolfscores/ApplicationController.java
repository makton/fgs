package com.example.frisbeegolfscores;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

public class ApplicationController extends Application {
	//Application wide instance variables
	private SharedPreferences mPrefs;
	private static ApplicationController singleton;
	
	public Context appContext;
	public Context actContext;
	
	public Asetukset asetukset;
    public SQLiteDatabase database;
    public DBAvaus dbAvaus;
    public DBAsetukset dbAsetukset;  
    	
    //Preferable to expose them via getter/setter methods
	@Override
	public void onCreate() {
		super.onCreate();
		//Do Application initialization over here
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
	
	    //mŠŠritellŠŠn context
	    actContext = this;
	    appContext = this.getApplicationContext();

        dbAvaus = new DBAvaus(appContext);
    	dbAsetukset = new DBAsetukset(appContext);
        
        //luetaan asetukset
        if (!dbAvaus.status()){
	        database = dbAvaus.open();
	        dbAsetukset.setDBInstance(database);
        }
        asetukset = dbAsetukset.getAsetus(1);
		
	}

	public ApplicationController getInstance(){
		return singleton;
	}
	
	public SQLiteDatabase GetDBInstance() {
		return database;
	}
	
	public boolean GetDBstatus() {
		if (database == null) {
			return false;
		}
		return database.isOpen();
	}
}
