package com.example.frisbeegolfscores;

import java.io.InputStream;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.util.Log;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;

import com.google.android.maps.GeoPoint;

public class FrisbeeGolfScores extends Activity {

	private Kielisyys kielisyys = new Kielisyys();
	private Sekalaiset sekalaiset = new Sekalaiset();
	private GPS gps;
	private boolean gpsStatus = false;
	private Handler handler;
	private Boolean connectionStatus = false; // network status?
	private Context appContext;
	private Context actContext;
	
    private Asetukset asetukset;
    private SQLiteDatabase database;
    private DBAsetukset dbAsetukset;
    
	private ApplicationController  mApplication;
    
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor sharedPrefsEditor;
	
	//näytön objektit
	private Button btnAsetukset;
	private Button btnPelaajat;
	private Button btnTiimit;
	private ProgressBar progress;
	private TextView textView;
	
	//asetukset
	private int kieli = 0;
	private boolean metric = false;
	private boolean usegps = false;
	private boolean vuoro = false;
	private boolean jarjestys = false;
	private int raportti = 0;
	
	//staattiset muuttujat
    public static final int DIALOG_GPS_DISABLED = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (savedInstanceState != null) {
            // Restore value of members from saved state
        } else {
        	// Set default values
        }
        
        setContentView(R.layout.activity_frisbee_golf_scores);

        Log.d("onCreate: ", String.valueOf(1));

    	//näytön objektien alustukset
        progress = (ProgressBar) findViewById(R.id.progressBar1);
        textView = (TextView) findViewById(R.id.textView1);
        btnAsetukset = (Button) findViewById(R.id.btnAsetukset);
        btnPelaajat = (Button) findViewById(R.id.btnPelaajat);
        btnTiimit = (Button) findViewById(R.id.btnTiimit);

        //määritellään context
        actContext = this;
        //appContext = this.getApplicationContext();
        //sqlContext = this.getApplication();

        handler = new Handler();

        //application controller
        mApplication = ((ApplicationController)getApplicationContext());
        appContext = mApplication.getInstance();
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(appContext);
        sharedPrefsEditor = sharedPrefs.edit();

        //luetaan asetukset
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
    	sharedPrefsEditor.putString("settings_language",String.valueOf(kieli));
    	sharedPrefsEditor.putBoolean("settings_metric",metric);
    	sharedPrefsEditor.putBoolean("settings_usegps",usegps);
    	sharedPrefsEditor.putBoolean("settings_vuoro",vuoro);
    	sharedPrefsEditor.putBoolean("settings_jarjestys",jarjestys);
    	sharedPrefsEditor.putString("settings_report",String.valueOf(raportti));
    	sharedPrefsEditor.commit();
    }

    @Override
    protected void onStart() {
    	// The activity is about to become visible.
        super.onStart();
        
        Log.d("onStart ",String.valueOf(1));
        
        // Reading all asetukset
        Log.d("FrisbeeGolfScores: ", "Haetaan kaikki asetukset..");
        List<Asetukset> Listasetukset = dbAsetukset.haeAsetukset();

        for (Asetukset cn : Listasetukset) {
            String log = "Id: " + cn.getId() + " ,Kieli: " + cn.getKieli() + " ,Jarjestys: " + cn.getPelijarjestys() + " ,Metric: " + cn.getMetric() + " ,Vuoro: " + cn.getVuoronvaihto() + " ,DB: " + cn.getDb_versio();
            Log.d("Asetukset (main) : ", log);
        }
        
        //näytön objektien tekstien määritys kielisyyden mukaan
                
        btnAsetukset.setText(kielisyys.strKieli_btnMainAsetukset[kieli]);
        btnPelaajat.setText(kielisyys.strKieli_btnMainPelaajat[kieli]);
        btnTiimit.setText(kielisyys.strKieli_btnMainTiimit[kieli]);
                
        //verkkoyhteys olemassa?
        connectionStatus = sekalaiset.isNetworkAvailable(appContext);

        Log.d("networkstatus: ", String.valueOf(connectionStatus));
    }
/*
    @Override
    protected void onRestart() {
    	// The activity is about to become visible again.
        super.onRestart();
    }
 
    @Override
    protected void onResume() {
    	// The activity has become visible (it is now "resumed").
    	super.onResume();
    	if (gpsStatus == true) {
    		gps.enableMyLocation();
    	}
    	Log.d("onResume ",String.valueOf(1));
    }
/*
    @Override
    protected void onPause() {
    	// Another activity is taking focus (this activity is about to be "paused").
    	super.onPause();
    	if (gpsStatus == true) {
    		gps.disableMyLocation();
    	}
    }
*/
    @Override
    protected void onStop() {
    	// The activity is no longer visible (it is now "stopped")
        super.onStop();
        Log.d("onStop ",String.valueOf(1));
        if (gpsStatus == true) {
        	gps.disableMyLocation();
        }
    }

    @Override
    protected void onDestroy() {
    	// The activity is about to be destroyed.
        super.onDestroy();
        Log.d("onDestroy ",String.valueOf(1));
        if (gpsStatus == true) {
        	gps.disableMyLocation();
        }
        if (mApplication.getInstance().getDBstatus()){
        	mApplication.getInstance().closeDB();
        }
    }
    
    //näkymien vaihdot
	public void onClick(View view) {
		Log.d("Activity ",String.valueOf(0));
		if (view == this.btnAsetukset) {
			Log.d("Activity vaihtuu ","Astukset");
			Intent prefIntent = new Intent(this, ActivityAsetukset.class);
			this.startActivity(prefIntent);
		}
		if (view == this.btnPelaajat) {
			Log.d("Activity vaihtuu ","Pelaajat");
			Intent prefIntent = new Intent(this, ActivityPelaajat.class);
			this.startActivity(prefIntent);
		}

	}
    
    public void startGps(View view) {
    	if (gpsStatus == false) { // onko GPS aktiivinen?
	    	gps = new GPS(appContext);
	    	if (gps.checkLocationSetting(appContext)) { // onko GPS päällä?
		        Runnable runnable = new Runnable() {
		        	//@Override
		        	public void run() {
		        		handler.post(new Runnable() {
							//@Override
							public void run() {
								GeoPoint geoPoint = gps.getMyLocation();
								textView.setText("GPS status : " + String.valueOf(gpsStatus) + " Lat: "+geoPoint.getLatitudeE6()+" Lon: "+geoPoint.getLongitudeE6()+"");
							}
						});
					}
				};
				gps.runOnFirstFix(runnable);
		        gps.runOnLocationUpdate(runnable);
		        gps.enableMyLocation();
		        gpsStatus = gps.getStatus();
	    	} else {
	    		// GPS disabled, näytä dialog
	    		textView.setText("GPS disabled!");
				AlertDialog.Builder builder = new AlertDialog.Builder(actContext);
				builder.setCancelable(true);
				builder.setIcon(drawable.dialog_frame);
				builder.setTitle("GPS is disabled! Do you want to enable GPS?");
				builder.setInverseBackgroundForced(true);
				builder.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {
						//@Override
						public void onClick(DialogInterface dialog, int which) {
							// näytä GPS asetukset
							Intent settingsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						    startActivity(settingsIntent);
							dialog.dismiss();
						}
					});
				builder.setNegativeButton("No",
					new DialogInterface.OnClickListener() {
						//@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
				AlertDialog alert = builder.create();
				alert.show();
	    	}    
    	} else {
    		gps.disableMyLocation();
    		gpsStatus = gps.getStatus();
    		textView.setText("GPS status : " + String.valueOf(gpsStatus));
    	}
    }
    
	public void startProgress(View view) {
		// Do something long
		Runnable runnable = new Runnable() {
			//@Override
			public void run() {
				for (int i = 0; i <= 10; i++) {
					final int value = i;
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					handler.post(new Runnable() {
						//@Override
						public void run() {
							progress.setProgress(value);
						}
					});
				}
			}
		};
		new Thread(runnable).start();
	}

	private class HttpGet extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
	        // ...
	    }

		@Override
		protected String doInBackground(String... urls) {
			String response = "";
			for (String osoite : urls) {
				try {
				    Http http = new Http();
				    InputStream httpData = null;
				    if (connectionStatus == true) {
				    	sekalaiset.disableConnectionReuseIfNecessary(); //FROYO
				    	try {
				    		httpData = http.GetUrl(osoite);
				    	}
				    	catch (Exception e) {
				    		e.printStackTrace();
				    	}
				        finally {
				            //Log
				        }
				    	if (httpData != null) {
				    		String httpDataStr = sekalaiset.ConvertInputStream(httpData);
				    		if (httpDataStr != null) {
				    			response += httpDataStr;
				    		}
				    	}

				    } else {
				    	// ei verkkoyhteyttä
				    }
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			return response;
		}
		
		//@Override
		protected void onProgressUpdate(Integer... progress) {
	        // TODO You are on the GUI thread, and the first element in 
	        // the progress parameter contains the last progress
	        // published from doInBackground, so update your GUI
	    }

		@Override
		protected void onPostExecute(String result) {

			textView.setText(result);
		}
	}
	
	public void readHttpInfo(View view) {
		HttpGet httpget = new HttpGet();
		httpget.execute(new String[] { "http://frisbeegolfradat.fi/radat" });
	}
}
