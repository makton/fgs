package com.example.frisbeegolfscores;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ActivityPelaajat extends Activity {

	private Context appContext;
	private Context actContext;
	
    private Pelaajat pelaajat;
    private SQLiteDatabase database;
    private DBPelaajat dbPelaajat;
    
	private ApplicationController  mApplication;
    
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (savedInstanceState != null) {
            // Restore value of members from saved state
        } else {

        }
        
        setContentView(R.layout.activity_pelaajat);

        Log.d("onCreate: ", String.valueOf(1));
/*
    	//näytön objektien alustukset
        progress = (ProgressBar) findViewById(R.id.progressBar1);
        textView = (TextView) findViewById(R.id.textView1);
        btnAsetukset = (Button) findViewById(R.id.btnAsetukset);
        btnPelaajat = (Button) findViewById(R.id.btnPelaajat);
        btnTiimit = (Button) findViewById(R.id.btnTiimit);
*/
        //määritellään context
        actContext = this;

        //application controller
        mApplication = ((ApplicationController)getApplicationContext());
        appContext = mApplication.getInstance();
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(appContext);
        sharedPrefsEditor = sharedPrefs.edit();

        //luetaan asetukset
        database = mApplication.getInstance().getDBInstance();
        dbPelaajat = new DBPelaajat(appContext);
	    dbPelaajat.setDBInstance(database);
	    List<Pelaajat> ListPelaajat = dbPelaajat.haePelaajat();
    }

    @Override
    protected void onStart() {
    	// The activity is about to become visible.
        super.onStart();
        
        Log.d("onStart ",String.valueOf(1));
        
    }

/*  @Override
    protected void onRestart() {
    	// The activity is about to become visible again.
        super.onRestart();
    }
 
    @Override
    protected void onResume() {
    	// The activity has become visible (it is now "resumed").
    	super.onResume();
    	Log.d("onResume ",String.valueOf(1));
    }

    @Override
    protected void onPause() {
    	// Another activity is taking focus (this activity is about to be "paused").
    	super.onPause();
    }

    @Override
    protected void onStop() {
    	// The activity is no longer visible (it is now "stopped")
        super.onStop();
        Log.d("onStop ",String.valueOf(1));
    }

    @Override
    protected void onDestroy() {
    	// The activity is about to be destroyed.
        super.onDestroy();
    }*/
}
