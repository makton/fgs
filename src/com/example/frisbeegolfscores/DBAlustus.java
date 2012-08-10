package com.example.frisbeegolfscores;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAlustus extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "frisbeegolfscores.db";
	private static final int DATABASE_VERSION = 9;

	// taulut
	public static final String TABLE_Asetukset = "asetukset";
	public static final String TABLE_Pelaajat = "pelaajat";
	public static final String TABLE_Joukkueet = "joukkueet";
	public static final String TABLE_Kaupungit = "kaupungit";
	public static final String TABLE_Radat = "radat";
	public static final String TABLE_Vaylat = "vaylat";
	public static final String TABLE_Kierros = "kierros";
	public static final String TABLE_Tulokset = "tulokset";
	public static final String TABLE_Gpsdata = "gpsdata";

	// asetukset taulun kent‰t
	public static final String COLUMN_Asetukset_ID = "_ID";
	public static final String COLUMN_Asetukset_nimi = "nimi";
	public static final String COLUMN_Asetukset_versio = "versio";
	public static final String COLUMN_Asetukset_db_versio = "db_versio";
	public static final String COLUMN_Asetukset_vuoronvaihto = "vuoronvaihto";
	public static final String COLUMN_Asetukset_pelijarjestys = "pelijarjestys";
	public static final String COLUMN_Asetukset_kieli = "kieli";
	public static final String COLUMN_Asetukset_metric = "metric";
	public static final String COLUMN_Asetukset_raportinmuoto = "raportinmuoto";
	public static final String COLUMN_Asetukset_useGPS = "useGPS";

	// pelaajat taulun kent‰t
	public static final String COLUMN_Pelaajat_ID = "_ID";
	public static final String COLUMN_Pelaajat_nimi = "nimi";
	public static final String COLUMN_Pelaajat_nayttonimi = "nayttonimi";
	public static final String COLUMN_Pelaajat_info = "info";
	public static final String COLUMN_Pelaajat_kayttajatunnus = "kayttajatunnus";
	public static final String COLUMN_Pelaajat_salasana = "salasana";
	public static final String COLUMN_Pelaajat_joukkue = "joukkue";
	public static final String COLUMN_Pelaajat_luontipvm = "luontipvm";
	public static final String COLUMN_Pelaajat_paivityspvm = "paivityspvm";

	// joukkueet taulun kent‰t	
	public static final String COLUMN_Joukkueet_ID = "_ID";
	public static final String COLUMN_Joukkueet_nimi = "nimi";
	
	// kaupungit taulun kent‰t	
	public static final String COLUMN_Kaupungit_ID = "_ID";
	public static final String COLUMN_Kaupungit_nimi = "nimi";
	public static final String COLUMN_Kaupungit_paivityspvm = "paivityspvm";
	
	// radat taulun kent‰t
	public static final String COLUMN_Radat_ID = "_ID";
	public static final String COLUMN_Radat_kaupunki_ID = "kaupunki_ID";
	public static final String COLUMN_Radat_nimi = "nimi";
	public static final String COLUMN_Radat_info = "info";
	public static final String COLUMN_Radat_luokitus = "luokitus";
	public static final String COLUMN_Radat_osoite = "osoite";
	public static final String COLUMN_Radat_sijainti = "sijainti";
	public static final String COLUMN_Radat_maksullinen = "maksullinen";
	public static final String COLUMN_Radat_vaylia = "vaylia";
	public static final String COLUMN_Radat_par = "par";
	public static final String COLUMN_Radat_kartta = "kartta";
	public static final String COLUMN_Radat_kuvaus = "kuvaus";
	public static final String COLUMN_Radat_linkki = "linkki";
	public static final String COLUMN_Radat_paivityspvm = "paivityspvm";
	
	// vaylat taulun kent‰t	
	public static final String COLUMN_Vaylat_ID = "_ID";
	public static final String COLUMN_Vaylat_rata_ID = "rata_ID";
	public static final String COLUMN_Vaylat_jarjestys = "jarjestys";
	public static final String COLUMN_Vaylat_pituus = "pituus";
	public static final String COLUMN_Vaylat_par = "par";
	public static final String COLUMN_Vaylat_kuvaus = "kuvaus";
	public static final String COLUMN_Vaylat_paivityspvm = "paivityspvm";
	
	// kierros taulun kent‰t	
	public static final String COLUMN_Kierros_ID = "_ID";
	public static final String COLUMN_Kierros_rata_ID = "rata_ID";
	public static final String COLUMN_Kierros_pvm = "pvm";
	
	// tulokset taulun kent‰t	
	public static final String COLUMN_Tulokset_ID = "_ID";
	public static final String COLUMN_Tulokset_pelaaja_ID = "pelajaa_ID";
	public static final String COLUMN_Tulokset_rata_ID = "rata_ID";
	public static final String COLUMN_Tulokset_vayla_ID = "vayla_ID";	
	public static final String COLUMN_Tulokset_kierros_ID = "kierros_ID";
	public static final String COLUMN_Tulokset_tulos = "tulos";	
	public static final String COLUMN_Tulokset_pvm = "pvm";
	public static final String COLUMN_Tulokset_kesto = "kesto";

	// GPSdata taulun kent‰t	
	public static final String COLUMN_Gpsdata_ID = "_ID";
	public static final String COLUMN_Gpsdata_x = "x";
	public static final String COLUMN_Gpsdata_y = "y";
	public static final String COLUMN_Gpsdata_tulokset_ID = "tulokset_ID";
	public static final String COLUMN_Gpsdata_pvm = "pvm";
	
	// Database creation sql statements
	private static final String DATABASE_CREATE_Asetukset = "create table " 
			+ TABLE_Asetukset + "("
			+ COLUMN_Asetukset_ID + " INTEGER primary key autoincrement, "
			+ COLUMN_Asetukset_nimi + " TEXT not null, "
			+ COLUMN_Asetukset_versio + " TEXT not null, "
			+ COLUMN_Asetukset_db_versio + " INTEGER not null, "
			+ COLUMN_Asetukset_vuoronvaihto + " INTEGER not null, "
			+ COLUMN_Asetukset_pelijarjestys + " INTEGER not null, "
			+ COLUMN_Asetukset_kieli + " INTEGER not null, "
			+ COLUMN_Asetukset_metric + " INTEGER not null, "
			+ COLUMN_Asetukset_raportinmuoto + " INTEGER not null, "
			+ COLUMN_Asetukset_useGPS + " INTEGER not null "
			+ ");";

	// Database creation sql statements
	private static final String DATABASE_INSERT_Asetukset = "insert into " 
			+ TABLE_Asetukset + "("
			+ COLUMN_Asetukset_nimi + ", "
			+ COLUMN_Asetukset_versio + ", "
			+ COLUMN_Asetukset_db_versio + ", "
			+ COLUMN_Asetukset_vuoronvaihto + ", "
			+ COLUMN_Asetukset_pelijarjestys + ", "
			+ COLUMN_Asetukset_kieli  + ", "
			+ COLUMN_Asetukset_metric  + ", "
			+ COLUMN_Asetukset_raportinmuoto + ", "
			+ COLUMN_Asetukset_useGPS
			+ ") values ("
			+ "'FrisbeeGolf Scores',"
			+ "'0.0.1',"
			+ DATABASE_VERSION + ","
			+ "1,"
			+ "1,"
			+ "1," // kieli
			+ "1,"
			+ "0,"
			+ "0"
			+ ");";
	
	private static final String DATABASE_CREATE_Pelaajat = "create table "
			+ TABLE_Pelaajat + "("
			+ COLUMN_Pelaajat_ID + " INTEGER primary key autoincrement, "
			+ COLUMN_Pelaajat_nimi + " TEXT not null, "
			+ COLUMN_Pelaajat_nayttonimi + " TEXT, "
			+ COLUMN_Pelaajat_info + " TEXT, "
			+ COLUMN_Pelaajat_kayttajatunnus + " TEXT, "
			+ COLUMN_Pelaajat_salasana + " TEXT, "
			+ COLUMN_Pelaajat_joukkue + " INTEGER, "
			+ COLUMN_Pelaajat_luontipvm + " TEXT, " 
			+ COLUMN_Pelaajat_paivityspvm + " TEXT" 
			+ ");";

	private static final String DATABASE_CREATE_Joukkueet = "create table "
			+ TABLE_Joukkueet + "("
			+ COLUMN_Joukkueet_ID + " INTEGER primary key autoincrement, "
			+ COLUMN_Joukkueet_nimi + " TEXT not null "
			+ ");";
	
	private static final String DATABASE_CREATE_Kaupungit = "create table "
			+ TABLE_Kaupungit + "("
			+ COLUMN_Kaupungit_ID + " INTEGER primary key autoincrement, "
			+ COLUMN_Kaupungit_nimi + " TEXT not null, "
			+ COLUMN_Kaupungit_paivityspvm + " TEXT"
			+ ");";

	private static final String DATABASE_CREATE_Radat = "create table "
			+ TABLE_Radat + "("
			+ COLUMN_Radat_ID + " INTEGER primary key autoincrement, "
			+ COLUMN_Radat_nimi + " TEXT not null, "
			+ COLUMN_Radat_kaupunki_ID + " INTEGER, "
			+ COLUMN_Radat_info + " TEXT, "
			+ COLUMN_Radat_luokitus + " TEXT, "
			+ COLUMN_Radat_osoite + " TEXT, "			
			+ COLUMN_Radat_sijainti + " TEXT, "
			+ COLUMN_Radat_maksullinen + " NUMERIC, "
			+ COLUMN_Radat_vaylia + " INTEGER, "
			+ COLUMN_Radat_par + " INTEGER, "
			+ COLUMN_Radat_kartta + " TEXT, "
			+ COLUMN_Radat_kuvaus + " TEXT, "
			+ COLUMN_Radat_linkki + " TEXT, "
			+ COLUMN_Radat_paivityspvm + " TEXT"
			+ ");";

	private static final String DATABASE_CREATE_Vaylat = "create table "
			+ TABLE_Vaylat +"("
			+ COLUMN_Vaylat_ID + " INTEGER primary key autoincrement, "
			+ COLUMN_Vaylat_rata_ID + " INTEGER, "
			+ COLUMN_Vaylat_jarjestys + " INTEGER, "
			+ COLUMN_Vaylat_pituus + " INTEGER, "
			+ COLUMN_Vaylat_par + " INTEGER, "
			+ COLUMN_Vaylat_kuvaus + " TEXT, "
			+ COLUMN_Vaylat_paivityspvm + " TEXT"
			+ ");";

	private static final String DATABASE_CREATE_Kierros = "create table "
			+ TABLE_Kierros +"("
			+ COLUMN_Kierros_ID + " INTEGER primary key autoincrement, "
			+ COLUMN_Kierros_rata_ID + " INTEGER, "
			+ COLUMN_Kierros_pvm + " TEXT"
			+ ");";

	private static final String DATABASE_CREATE_Tulokset = "create table "
			+ TABLE_Tulokset +"("
			+ COLUMN_Tulokset_ID + " INTEGER primary key autoincrement, "
			+ COLUMN_Tulokset_pelaaja_ID + " INTEGER not null, "			
			+ COLUMN_Tulokset_rata_ID + " INTEGER, "
			+ COLUMN_Tulokset_vayla_ID + " INTEGER, "
			+ COLUMN_Tulokset_kierros_ID + " INTEGER, "
			+ COLUMN_Tulokset_tulos + " INTEGER, "
			+ COLUMN_Tulokset_pvm + " TEXT, "
			+ COLUMN_Tulokset_kesto + " TEXT"
			+ ");";

	private static final String DATABASE_CREATE_Gpsdata = "create table "
			+ TABLE_Gpsdata +"("
			+ COLUMN_Gpsdata_ID + " INTEGER primary key autoincrement, "
			+ COLUMN_Gpsdata_x + " TEXT, "
			+ COLUMN_Gpsdata_y + " TEXT, "
			+ COLUMN_Gpsdata_tulokset_ID + " INTEGER, "
			+ COLUMN_Gpsdata_pvm + " TEXT"
			+ ");";
	
	public DBAlustus(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		Log.d("DBAlustus: ", "Luodaan kannat..");
		//kantojen luonti
		database.execSQL(DATABASE_CREATE_Asetukset);
		database.execSQL(DATABASE_CREATE_Pelaajat);
		database.execSQL(DATABASE_CREATE_Joukkueet);
		database.execSQL(DATABASE_CREATE_Kaupungit);
		database.execSQL(DATABASE_CREATE_Radat);		
		database.execSQL(DATABASE_CREATE_Vaylat);
		database.execSQL(DATABASE_CREATE_Kierros);
		database.execSQL(DATABASE_CREATE_Tulokset);
		database.execSQL(DATABASE_CREATE_Gpsdata);
		
		//perusasetukset
		database.execSQL(DATABASE_INSERT_Asetukset);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d("DBAlustus: ", "P‰ivitet‰‰n kannat..");
		// tarkista mit‰ on muuttunut, ettei tarvitse aina ladata tietoja uudelleen
		
		db.execSQL("DROP TABLE IF EXISTS asetukset");
		db.execSQL("DROP TABLE IF EXISTS pelaajat");
		db.execSQL("DROP TABLE IF EXISTS joukkueet");
		db.execSQL("DROP TABLE IF EXISTS kaupungit");
		db.execSQL("DROP TABLE IF EXISTS radat");
		db.execSQL("DROP TABLE IF EXISTS vaylat");
		db.execSQL("DROP TABLE IF EXISTS kierros");
		db.execSQL("DROP TABLE IF EXISTS tulokset");
		db.execSQL("DROP TABLE IF EXISTS Gpsdata");
		onCreate(db);		
	}

}
