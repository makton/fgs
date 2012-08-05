package com.example.frisbeegolfscores;

public class poistettuja_koodeja {

	//kielisyys
	
	/*
	String[] spinnerKieliValues = new String[]{};
	
	//näytön objektit
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
   
        //näytön objektien alustukset
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

	//määritellään layoutin tekstit kielisyyden mukaisesti
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
	
    //määritellään valinnat asetuksien mukaisiksi
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
	
	
	
	
	
	
	
	
	//jotain kantaa liittyvää
	
	
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
	
	
	
	/*	
	public DBAsetukset openDBAsetukset() {
		if (dbAvaus.status()) {
			DBAsetukset dbAsetukset = new DBAsetukset(appContext);
			dbAsetukset = new DBAsetukset(appContext);
			dbAsetukset.setDBInstance(database);
		}
		return dbAsetukset;
	}

	public void openDBPelaajat() {
		if (dbAvaus.status()) {
			DBPelaajat dbPelaajat = new DBPelaajat(appContext);
			dbPelaajat = new DBPelaajat(appContext);
			dbPelaajat.setDBInstance(database);
		}
	}

	public void openDBJoukkueet() {
		if (dbAvaus.status()) {
			DBJoukkueet dbJoukkueet = new DBJoukkueet(appContext);
			dbJoukkueet = new DBJoukkueet(appContext);
			dbJoukkueet.setDBInstance(database);
		}
	}

	public void openDBKaupungit() {
		if (dbAvaus.status()) {
			DBKaupungit dbKaupungit = new DBKaupungit(appContext);
			dbKaupungit = new DBKaupungit(appContext);
			dbKaupungit.setDBInstance(database);
		}
	}

	public void openDBRadat() {
		if (dbAvaus.status()) {
			DBRadat dbRadat = new DBRadat(appContext);
			dbRadat = new DBRadat(appContext);
			dbRadat.setDBInstance(database);
		}
	}

	public void openDBVaylat() {
		if (dbAvaus.status()) {
			DBVaylat dbVaylat = new DBVaylat(appContext);
			dbVaylat = new DBVaylat(appContext);
			dbVaylat.setDBInstance(database);
		}
	}

	public void openDBKierros() {
		if (dbAvaus.status()) {
			DBKierros dbKierros = new DBKierros(appContext);
			dbKierros = new DBKierros(appContext);
			dbKierros.setDBInstance(database);
		}
	}
*/
	
	
	
	
	
	
	// preferences
	
	/*
	//näytön objektit
	private ListPreference settings_languages;
	private CheckBoxPreference settings_metric;
	private CheckBoxPreference settings_usegps;
	private CheckBoxPreference settings_vuoro;
	private CheckBoxPreference settings_jarjestys;
	private ListPreference settings_report;
*/

    /*
    settings_languages = ((ListPreference)getPreferenceScreen().findPreference("settings_languages"));
    settings_metric = ((CheckBoxPreference)getPreferenceScreen().findPreference("settings_metric"));
    settings_usegps = ((CheckBoxPreference)getPreferenceScreen().findPreference("settings_usegps"));
    settings_vuoro = ((CheckBoxPreference)getPreferenceScreen().findPreference("settings_vuoro"));
    settings_jarjestys = ((CheckBoxPreference)getPreferenceScreen().findPreference("settings_jarjestys"));
	settings_report = ((ListPreference)getPreferenceScreen().findPreference("settings_report"));
	*/

	// Set up a listener whenever a key changes            
    //getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

	
	// Unregister the listener whenever a key changes            
    //getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

	/*
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		SharedPreferences.Editor prefEditor = sharedPreferences.edit();

		if (key.equals("settings_language")) {
//			String optionValue = sharedPreferences.getString(key, "");
//			prefEditor.putString("", "Default value");
//			prefEditor.commit();
		}
		if (key.equals("settings_metric")) {
//			String optionValue = sharedPreferences.getString(key, "");
//			prefEditor.putBoolean("", "Default value");
//			prefEditor.commit();
		}

	}
*/
	
}
