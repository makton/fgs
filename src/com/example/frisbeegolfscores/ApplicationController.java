package com.example.frisbeegolfscores;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.util.Log;

public class ApplicationController extends Application {
	//Application wide instance variables
	private static ApplicationController singleton = null;
	private Context appContext;
	private Configuration config;
	private SQLiteDatabase database;
	private DBAvaus dbAvaus;  
    
    //Yleiset muuttujat
    //public int[] aktiivisetPelaajat;
    List<Integer> aktiivisetPelaajat = new ArrayList<Integer>();
    private int aktiivinenRata = 0;
    private int aktiivinenVayla = 0;
    
    private Locale locale = null;

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
		
		config = getBaseContext().getResources().getConfiguration();

		//mŠŠritellŠŠn ohjelman kielisyys
        int kieli = Integer.parseInt(sharedPrefs.getString("settings_language", "0"));
        String lang = "";
        
		switch (kieli) {
		case 0:
			lang = "en_US";
			break;
		case 1:
			lang = "fi_FI";
			break;
		case 2:
			lang = "sv_SE";
			break;
		default:
			lang = "en_US";
		}
        
        if (! "".equals(lang) && ! config.locale.getLanguage().equals(lang))
        {
            locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
	}

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        if (locale != null)
        {
            newConfig.locale = locale;
            Locale.setDefault(locale);
            getBaseContext().getResources().updateConfiguration(newConfig, getBaseContext().getResources().getDisplayMetrics());
        }
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
