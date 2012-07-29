package com.example.frisbeegolfscores;

import java.io.InputStream;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.Button;

import com.google.android.maps.GeoPoint;

public class FrisbeeGolfScores extends Activity {

	private Kielisyys kielisyys = new Kielisyys();
	private Sekalaiset sekalaiset = new Sekalaiset();
	private GPS gps;
	private boolean gpsStatus = false;
	private Handler handler;
	private Boolean connectionStatus = false; // network status?
	public Context appContext;
	public Context actContext;
	
    private Asetukset asetukset;
    private SQLiteDatabase database;
	private DBAvaus dbAvaus = new DBAvaus(this);
    private DBAsetukset dbAsetukset = new DBAsetukset(this);        
	
	//näytön objektit
	private Button btnAsetukset;
	private Button btnPelaajat;
	private Button btnTiimit;
	private ProgressBar progress;
	private TextView textView;
	
	//asetukset
	public int kieli = 0;
	
	//staattiset muuttujat
    public static final int DIALOG_GPS_DISABLED = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frisbee_golf_scores);
        
        Log.d("FrisbeeGolfScores: ", "Ohjelma alkaa..");
        Log.d("onCreate: ", String.valueOf(1));

    	//näytön objektien alustukset
        progress = (ProgressBar) findViewById(R.id.progressBar1);
        textView = (TextView) findViewById(R.id.textView1);
        btnAsetukset = (Button) findViewById(R.id.btnAsetukset);
        btnPelaajat = (Button) findViewById(R.id.btnPelaajat);
        btnTiimit = (Button) findViewById(R.id.btnTiimit);
        
        //määritellään context
        actContext = this;
        appContext = this.getApplicationContext();

        handler = new Handler();

        /*
        dbAsetukset.addAsetus(new Asetukset("Frisbee Golf Scores","0.0.1",2));
        dbPelaajat.addPelaaja(new Pelaajat("Ravi3"));
        dbKierros.addKierros(new Kierros(1,"15.7.2012 16:24"));
        */
    }

    @Override
    protected void onStart() {
    	// The activity is about to become visible.
        super.onStart();
        
        Log.d("onStart ",String.valueOf(1));

        //luetaan asetukset
        if (!dbAvaus.status()){
	        database = dbAvaus.open();
	        dbAsetukset.setDBInstance(database);
        }
        asetukset = dbAsetukset.getAsetus(1);
        kieli = asetukset.getKieli();
        
        Log.d("kieli : ", String.valueOf(kieli));
        
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
        if (dbAvaus.status()){
        	dbAvaus.close();
        }
    }
/*   
    @Override
    protected void onDestroy() {
    	// The activity is about to be destroyed.
        super.onDestroy();
        Log.d("onDestroy ",String.valueOf(1));
        if (gpsStatus == true) {
        	gps.disableMyLocation();
        }
        if (dbAvaus.status()){
        	dbAvaus.close();
        }
    }
    /*
    private Handler popupHandler = new Handler() {
    	@Override
    	public void handleMessage(Message msg) {
    		switch (msg.what) {
    		case DIALOG_GPS_DISABLED:
    			showDialog(DIALOG_GPS_DISABLED);
    			break;
    		}
    	}
    };*/
    
    //näkymien vaihdot
	public void onClick(View view) {
		Log.d("Activity ",String.valueOf(0));
		if (view == this.btnAsetukset) {
			Log.d("Activity vaihtuu",String.valueOf(1));
			Intent prefIntent = new Intent(this, ActivityAsetukset.class);
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
		httpget.execute(new String[] { "http://www.google.fi" });

	}
	
        /*
        DBPelaajat dbPelaajat = new DBPelaajat(this);
        DBJoukkueet dbJoukkueet = new DBJoukkueet(this);
        DBKaupungit dbKaupungit = new DBKaupungit(this);
        DBRadat dbRadat = new DBRadat(this);
        DBVaylat dbVaylat = new DBVaylat(this);
        DBKierros dbKierros = new DBKierros(this);
        

        dbPelaajat.setDBInstance(database);
        dbJoukkueet.setDBInstance(database);
        dbKaupungit.setDBInstance(database);
        dbRadat.setDBInstance(database);
        dbVaylat.setDBInstance(database);
        dbKierros.setDBInstance(database);
        
        dbAsetukset.addAsetus(new Asetukset("Frisbee Golf Scores","0.0.1",2));
        dbPelaajat.addPelaaja(new Pelaajat("Ravi3"));
        dbKierros.addKierros(new Kierros(1,"15.7.2012 16:24"));
        
        // Reading all contacts
        Log.d("FrisbeeGolfScores: ", "Haetaan kaikki pelaajat..");
        List<Pelaajat> pelaajat = dbPelaajat.haePelaajat();

        for (Pelaajat cn : pelaajat) {
            String log = "Id: "+cn.getId()+" ,Nimi: " + cn.getNimi();// + " ,Phone: " + cn.getPhoneNumber();
                // Writing Contacts to log
            Log.d("Nimi: ", log);
        }
        */
}
