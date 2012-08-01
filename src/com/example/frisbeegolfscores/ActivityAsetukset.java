package com.example.frisbeegolfscores;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.Spinner;
import android.widget.TextView;

public class ActivityAsetukset extends PreferenceActivity {
	
    protected Method mLoadHeaders = null;
    protected Method mHasHeaders = null;
	
	private Kielisyys kielisyys = new Kielisyys();
	private SQLiteDatabase database;
	private DBAvaus dbAvaus;// = new DBAvaus(this);
	private DBAsetukset dbAsetukset;// = new DBAsetukset(this);
    private Asetukset asetukset;
	public Context appContext;
	public Context actContext;
	public Context sqlContext;
	
	//asetukset
	public int muutoksia = 0;
	public int kieli = 0;
	public int gps = 0;
	public int vuoro = 0;
	public int jarjestys = 0;
	public int raportti = 0;
	/*
	String[] spinnerKieliValues = new String[]{};
	
	//n�yt�n objektit
	private TextView txtOtsikkoVersio;
	private TextView txtVersio;
	private TextView txtOtsikkoKieli;
	private TextView txtKieli;
    private Spinner spinnerKieli;
	private TextView txtOtsikkoGps;
	private CheckedTextView chktxtGps;
	private TextView txtOtsikkoVuoro;
	private CheckedTextView chktxtVuoro;
	private TextView txtOtsikkoJarjestys;
	private CheckedTextView chktxtJarjestys;
	private TextView txtOtsikkoRaportti;
	private CheckedTextView chktxtRaportti;
	private ArrayAdapter <CharSequence> adapter;
*/

    @SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			mLoadHeaders = getClass().getMethod("loadHeadersFromResource",int.class, List.class);
			mHasHeaders = getClass().getMethod("hasHeaders");
		} catch (NoSuchMethodException e) {
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asetukset);
		if (!isNewV11Prefs()) {
			addPreferencesFromResource(R.xml.app_prefs_cat1);
			addPreferencesFromResource(R.xml.app_prefs_cat2);
			addPreferencesFromResource(R.xml.app_prefs_cat3);
		}
		// addPreferencesFromResource(R.xml.preferences);

		// application controller
		ApplicationController mApplication = (ApplicationController) getApplicationContext();
		// String username = mApplication.getUsername();
		// String password = mApplication.getPassword();

	}
    
    
    @Override
    protected void onStart() {
    	// The activity is about to become visible.
        super.onStart();
        
        //m��ritell��n context
        actContext = this;
        appContext = this.getApplicationContext();
        sqlContext = this.getApplication();
        /*
        //n�yt�n objektien alustukset
        txtOtsikkoVersio = (TextView) findViewById(R.id.txtOtsikkoVersio);
        txtVersio = (TextView) findViewById(R.id.txtVersio);
        txtOtsikkoKieli = (TextView) findViewById(R.id.txtOtsikkoKieli);
        txtKieli = (TextView) findViewById(R.id.txtKieli);
        spinnerKieli = (Spinner) findViewById(R.id.spinnerKieli);
        txtOtsikkoGps = (TextView) findViewById(R.id.txtOtsikkoGps);
        chktxtGps = (CheckedTextView) findViewById(R.id.chktxtGps);
        txtOtsikkoVuoro = (TextView) findViewById(R.id.txtOtsikkoVuoro);
        chktxtVuoro = (CheckedTextView) findViewById(R.id.chktxtVuoro);
        txtOtsikkoJarjestys = (TextView) findViewById(R.id.txtOtsikkoJarjestys);
        chktxtJarjestys = (CheckedTextView) findViewById(R.id.chktxtJarjestys);
        txtOtsikkoRaportti = (TextView) findViewById(R.id.txtOtsikkoRaportti);
        chktxtRaportti = (CheckedTextView) findViewById(R.id.chktxtRaportti);
        */
        
        //luetaan asetukset
        dbAvaus = new DBAvaus(sqlContext);
    	dbAsetukset = new DBAsetukset(sqlContext);
        
    	if (!dbAvaus.status()){
	        database = dbAvaus.open();
	        dbAsetukset.setDBInstance(database);
        }
      
        asetukset = dbAsetukset.getAsetus(1);
        kieli = asetukset.getKieli();
    	gps = asetukset.getUseGPS();
    	vuoro = asetukset.getVuoronvaihto();
    	jarjestys = asetukset.getPelijarjestys();
    	raportti = asetukset.getRaportinmuoto();

        // Reading all asetukset
        Log.d("FrisbeeGolfScores: ", "Haetaan kaikki asetukset..");
        List<Asetukset> Listasetukset = dbAsetukset.haeAsetukset();

        for (Asetukset cn : Listasetukset) {
            String log = "Id: "+cn.getId()+" ,Kieli: " + cn.getKieli() + " ,Jarjestys: " + cn.getPelijarjestys();
                // Writing Contacts to log
            Log.d("Asetukset (settings) : ", log);
        }
/*
    	//m��ritell��n layoutin tekstit kielisyyden mukaisesti
        txtOtsikkoVersio.setText(kielisyys.strKieli_txtAsetuksetOtsikkoVersio[kieli]);
        txtVersio.setText(kielisyys.strKieli_txtAsetuksetVersio[kieli] + " " + asetukset.getVersio());
        txtOtsikkoKieli.setText(kielisyys.strKieli_txtAsetuksetOtsikkoKieli[kieli]);
        txtKieli.setText(kielisyys.strKieli_txtAsetuksetKieli[kieli]);
        txtOtsikkoGps.setText(kielisyys.strKieli_txtAsetuksetOtsikkoGps[kieli]);
        chktxtGps.setText(kielisyys.strKieli_txtAsetuksetGps[kieli]);
        txtOtsikkoVuoro.setText(kielisyys.strKieli_txtAsetuksetOtsikkoVuoro[kieli]);
        chktxtVuoro.setText(kielisyys.strKieli_txtAsetuksetVuoro[kieli]);
        txtOtsikkoJarjestys.setText(kielisyys.strKieli_txtAsetuksetOtsikkoJarjestys[kieli]);
        chktxtJarjestys.setText(kielisyys.strKieli_txtAsetuksetJarjestys[kieli]);
        txtOtsikkoRaportti.setText(kielisyys.strKieli_txtAsetuksetOtsikkoRaportti[kieli]);
        chktxtRaportti.setText(kielisyys.strKieli_txtAsetuksetRaportti[kieli]);
        
        adapter = new ArrayAdapter <CharSequence> (this, android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (int x=0; x<=1;x++) {
        	adapter.add(kielisyys.kielet[x]);
        }
        
        //luodaan kenttien listenerit
		spinnerKieli.setOnItemSelectedListener(new OnItemSelectedListener() {
			// @Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				if (kieli != position) {
					muutoksia = 1;
					kieli = position;
				}
			}

			// @Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// your code here
			}
		});
        spinnerKieli.setAdapter(adapter);
        
		chktxtGps.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				muutoksia = 1;
				CheckedTextView tt = (CheckedTextView) v.findViewById(R.id.chktxtGps);
				if (!tt.isChecked()) {
					tt.setChecked(true);
					gps = 1;
				} else {
					tt.setChecked(false);
					gps = 0;
				}
			}
        });

		chktxtVuoro.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				muutoksia = 1;
				CheckedTextView tt = (CheckedTextView) v.findViewById(R.id.chktxtVuoro);
				if (!tt.isChecked()) {
					tt.setChecked(true);
					vuoro = 1;
				} else {
					tt.setChecked(false);
					vuoro = 0;
				}
			}
        });

		chktxtJarjestys.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				muutoksia = 1;
				CheckedTextView tt = (CheckedTextView) v.findViewById(R.id.chktxtJarjestys);
				if (!tt.isChecked()) {
					tt.setChecked(true);
					jarjestys = 1;
				} else {
					tt.setChecked(false);
					jarjestys = 0;
				}
			}
        });

		chktxtRaportti.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				muutoksia = 1;
				CheckedTextView tt = (CheckedTextView) v.findViewById(R.id.chktxtRaportti);
				if (!tt.isChecked()) {
					tt.setChecked(true);
					raportti = 1;
				} else {
					tt.setChecked(false);
					raportti = 0;
				}
			}
        });
		
        //m��ritell��n valinnat asetuksien mukaisiksi
		spinnerKieli.setSelection(kieli, true);
		
        if (gps == 1) {
        	chktxtGps.setChecked(true);
        }
        
        if (vuoro == 1) {
        	chktxtVuoro.setChecked(true);
        }
        
        if (jarjestys == 1) {
        	chktxtJarjestys.setChecked(true);
        }
        
        if (raportti == 1) {
        	chktxtRaportti.setChecked(true);
        } */       
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
    }

    @Override
    protected void onPause() {
    	// Another activity is taking focus (this activity is about to be "paused").
    	super.onPause();
    }*/

    @Override
    protected void onStop() {
    	// The activity is no longer visible (it is now "stopped")
        super.onStop();
        
        //tallennetaan asetukset
        if (muutoksia == 1) {
            asetukset.setKieli(kieli);
        	asetukset.setUseGPS(gps);
        	asetukset.setVuoronvaihto(vuoro);
        	asetukset.setPelijarjestys(jarjestys);
        	asetukset.setRaportinmuoto(raportti);
        	dbAsetukset.updateAsetukset(asetukset);
        }
        
        //suljetaan kanta
        if (dbAvaus.status()){
        	dbAvaus.close();
        }

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
            mLoadHeaders.invoke(this,new Object[]{R.xml.pref_headers,aTarget});
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
