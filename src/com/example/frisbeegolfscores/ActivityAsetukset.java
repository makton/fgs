package com.example.frisbeegolfscores;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

public class ActivityAsetukset extends PreferenceActivity {
	
    protected Method mLoadHeaders = null;
    protected Method mHasHeaders = null;
	
	private SQLiteDatabase database;
	private DBAsetukset dbAsetukset;
    private Asetukset asetukset;
    private Context appContext;
	private Context actContext;
	
	private ApplicationController mApplication;
	
	private SharedPreferences sharedPrefs;
	private SharedPreferences.Editor sharedPrefsEditor;
	
	//asetukset
	private int kieli = 0;
	private boolean metric = false;
	private boolean usegps = false;
	private boolean vuoro = false;
	private boolean jarjestys = false;
	private int raportti = 0;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			mLoadHeaders = getClass().getMethod("loadHeadersFromResource",int.class, List.class);
			mHasHeaders = getClass().getMethod("hasHeaders");
		} catch (NoSuchMethodException e) {
		} 
		super.onCreate(savedInstanceState);

		if (!isNewV11Prefs()) {
			addPreferencesFromResource(R.xml.app_prefs);
		}

        //application controller
        mApplication = ((ApplicationController)getApplicationContext());
        appContext = mApplication.getInstance();
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(appContext);
        sharedPrefsEditor = sharedPrefs.edit();
	}

    @Override
    protected void onStart() {
    	// The activity is about to become visible.
        super.onStart();
        
        //mŠŠritellŠŠn context
        actContext = this;
        //appContext = this.getApplicationContext();
        //sqlContext = this.getApplication();

        //luetaan asetukset kannasta, varmuuden vuoksi
        database = mApplication.getInstance().getDBInstance();
        dbAsetukset = new DBAsetukset(appContext);
	    dbAsetukset.setDBInstance(database);
        asetukset = dbAsetukset.getAsetus(1);
        kieli = asetukset.getKieli();
        metric = asetukset.getMetric();
    	usegps = asetukset.getUseGPS();
    	vuoro = asetukset.getVuoronvaihto();
    	jarjestys = asetukset.getPelijarjestys();
    	raportti = asetukset.getRaportinmuoto();
    	
        // Reading all asetukset
        Log.d("FrisbeeGolfScores: ", "Haetaan kaikki asetukset..");
        List<Asetukset> Listasetukset = dbAsetukset.haeAsetukset();

        for (Asetukset cn : Listasetukset) {
            String log = "Id: "+cn.getId()+" ,Kieli: " + cn.getKieli() + " ,Jarjestys: " + cn.getPelijarjestys() + " ,Metric: " + cn.getMetric() + " ,Vuoro: " + cn.getVuoronvaihto();
                // Writing Contacts to log
            Log.d("Asetukset (settings) : ", log);
        }     
    }
/*
    @Override
    protected void onRestart() {
    	// The activity is about to become visible again.
        super.onRestart();
    }*/
    
 /*
	@Override
    protected void onResume() {
    	// The activity has become visible (it is now "resumed").
    	super.onResume();
    }
*/
 /*
    @Override
    protected void onPause() {
    	// Another activity is taking focus (this activity is about to be "paused").
    	super.onPause();
    }
*/
    @Override
    protected void onStop() {
    	// The activity is no longer visible (it is now "stopped")
        super.onStop();
        
        //tallennetaan asetukset
        asetukset.setKieli(Integer.parseInt(sharedPrefs.getString("settings_language",String.valueOf(kieli))));
       	asetukset.setMetric(sharedPrefs.getBoolean("settings_metric",metric));
       	asetukset.setUseGPS(sharedPrefs.getBoolean("settings_usegps",usegps));
       	asetukset.setVuoronvaihto(sharedPrefs.getBoolean("settings_vuoro",vuoro));
       	asetukset.setPelijarjestys(sharedPrefs.getBoolean("settings_jarjestys",jarjestys));
       	asetukset.setRaportinmuoto(Integer.parseInt(sharedPrefs.getString("settings_report",String.valueOf(raportti))));
       	int result = dbAsetukset.updateAsetukset(asetukset);
       	Log.d("update result: ", String.valueOf(result)); 	
    }
/*    
    @Override
    protected void onDestroy() {
    	// The activity is about to be destroyed.
        super.onDestroy();
    }
*/    
    /**
     * Checks to see if using new v11+ way of handling PrefsFragments.
     * @return Returns false pre-v11, else checks to see if using headers.
     */
    public boolean isNewV11Prefs() {
        if (mHasHeaders!=null && mLoadHeaders!=null) {
            try {
                return (Boolean)mHasHeaders.invoke(this);
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
        }
        return false;
    }
    
    @Override
    public void onBuildHeaders(List<Header> aTarget) {
        try {
            //mLoadHeaders.invoke(this,new Object[]{R.xml.pref_headers,aTarget});
            mLoadHeaders.invoke(this,new Object[]{R.xml.app_prefs,aTarget});
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }  
    }
 
    @SuppressLint("NewApi")
    static public class PrefsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle aSavedState) {
            super.onCreate(aSavedState);
            Context anAct = getActivity().getApplicationContext();
            int thePrefRes = anAct.getResources().getIdentifier(getArguments().getString("pref-resource"),"xml",anAct.getPackageName());
            addPreferencesFromResource(thePrefRes);
        }
    }
}
