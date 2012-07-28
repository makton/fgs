package com.example.frisbeegolfscores;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class DBRadat {

	private SQLiteDatabase database;
	//private DBRadat dbRadat;
	private DBAvaus dbAvaus;

	public DBRadat(Context context) {
		dbAvaus = new DBAvaus(context);
	}
	
	private String[] allRadatColumns = { 
			DBAlustus.COLUMN_Radat_ID,
			DBAlustus.COLUMN_Radat_kaupunki_ID,
			DBAlustus.COLUMN_Radat_nimi,
			DBAlustus.COLUMN_Radat_info,
			DBAlustus.COLUMN_Radat_luokitus,
			DBAlustus.COLUMN_Radat_osoite,
			DBAlustus.COLUMN_Radat_sijainti,
			DBAlustus.COLUMN_Radat_maksullinen,
			DBAlustus.COLUMN_Radat_vaylia,
			DBAlustus.COLUMN_Radat_par,
			DBAlustus.COLUMN_Radat_kartta,
			DBAlustus.COLUMN_Radat_kuvaus,
			DBAlustus.COLUMN_Radat_paivityspvm};
	
	public void setDBInstance(SQLiteDatabase database) {
		this.database = database;
	}
	
	// Adding new
    public void addRata(Radat radat) {
        ContentValues values = new ContentValues();                
        
        Log.d("DBRadat: ", "Lis‰t‰‰n rata alkaa..");
        
        //values.put(DBAlustus.COLUMN_Radat_ID,
        values.put(DBAlustus.COLUMN_Radat_kaupunki_ID, radat.getKaupunki_id());
        values.put(DBAlustus.COLUMN_Radat_nimi, radat.getNimi());
        values.put(DBAlustus.COLUMN_Radat_info, radat.getInfo());
        values.put(DBAlustus.COLUMN_Radat_luokitus, radat.getLuokitus());
        values.put(DBAlustus.COLUMN_Radat_osoite, radat.getOsoite());
        values.put(DBAlustus.COLUMN_Radat_sijainti, radat.getSijainti());
        values.put(DBAlustus.COLUMN_Radat_maksullinen, radat.getMaksullinen());
        values.put(DBAlustus.COLUMN_Radat_vaylia, radat.getVaylia());
        values.put(DBAlustus.COLUMN_Radat_par, radat.getPar());
        values.put(DBAlustus.COLUMN_Radat_kartta, radat.getKartta());
        values.put(DBAlustus.COLUMN_Radat_kuvaus, radat.getKuvaus());
        values.put(DBAlustus.COLUMN_Radat_paivityspvm, radat.getPaivityspvm());

		Log.d("DBRadat: ", "Lis‰t‰‰n rata loppuu..");
        // Inserting Row
		long insertId = database.insert(DBAlustus.TABLE_Radat, null, values);
        //database.close(); // Closing database connection
		Log.d("DBRadat: ", "rata tallennettu.." + insertId);
    }
 
    // Getting single
    public Radat getRata(int id) { 
    	Log.d("DBRadat: ", "Haetaan rata..");

        Cursor cursor = database.query(DBAlustus.TABLE_Radat, new String[] { DBAlustus.COLUMN_Radat_kaupunki_ID, DBAlustus.COLUMN_Radat_nimi, DBAlustus.COLUMN_Radat_info, DBAlustus.COLUMN_Radat_luokitus, DBAlustus.COLUMN_Radat_osoite, DBAlustus.COLUMN_Radat_sijainti, DBAlustus.COLUMN_Radat_maksullinen, DBAlustus.COLUMN_Radat_vaylia, DBAlustus.COLUMN_Radat_par, DBAlustus.COLUMN_Radat_kartta, DBAlustus.COLUMN_Radat_kuvaus, DBAlustus.COLUMN_Radat_paivityspvm }, DBAlustus.COLUMN_Radat_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Radat radat = new Radat(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9)), cursor.getString(10), cursor.getString(11), cursor.getString(12));
        return radat;
    }
    
    // Updating single
	public int updateRata(Radat radat) {
	    ContentValues values = new ContentValues();	
	    
	    Log.d("DBRadat: ", "P‰ivitet‰‰n rata..");

	  //values.put(DBAlustus.COLUMN_Radat_ID,
        values.put(DBAlustus.COLUMN_Radat_kaupunki_ID, radat.getKaupunki_id());
        values.put(DBAlustus.COLUMN_Radat_nimi, radat.getNimi());
        values.put(DBAlustus.COLUMN_Radat_info, radat.getInfo());
        values.put(DBAlustus.COLUMN_Radat_luokitus, radat.getLuokitus());
        values.put(DBAlustus.COLUMN_Radat_osoite, radat.getOsoite());
        values.put(DBAlustus.COLUMN_Radat_sijainti, radat.getSijainti());
        values.put(DBAlustus.COLUMN_Radat_maksullinen, radat.getMaksullinen());
        values.put(DBAlustus.COLUMN_Radat_vaylia, radat.getVaylia());
        values.put(DBAlustus.COLUMN_Radat_par, radat.getPar());
        values.put(DBAlustus.COLUMN_Radat_kartta, radat.getKartta());
        values.put(DBAlustus.COLUMN_Radat_kuvaus, radat.getKuvaus());
        values.put(DBAlustus.COLUMN_Radat_paivityspvm, radat.getPaivityspvm());
	 
	    // updating row
	    return database.update(DBAlustus.TABLE_Radat, values, DBAlustus.COLUMN_Radat_ID + " = ?", new String[] { String.valueOf(radat.getId()) });
	}
	
    public void deletePelaaja(Radat radat) {
		int id = radat.getId();

		database.delete(DBAlustus.TABLE_Radat, DBAlustus.COLUMN_Radat_ID + " = " + id, null);
	}
	
 // Getting All Contacts
    public List<Radat> haeRadat() {
        List<Radat> listRadat = new ArrayList<Radat>();

        Cursor cursor = database.query(DBAlustus.TABLE_Radat, allRadatColumns, null, null, null, null, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Radat radat = new Radat();
            	radat.setId(Integer.parseInt(cursor.getString(0)));
            	radat.setKaupunki_id(Integer.parseInt(cursor.getString(1)));
            	radat.setNimi(cursor.getString(2));
            	radat.setInfo(cursor.getString(3));
            	radat.setLuokitus(cursor.getString(4));
            	radat.setOsoite(cursor.getString(5));
            	radat.setSijainti(cursor.getString(6));
            	radat.setMaksullinen(Integer.parseInt(cursor.getString(7)));
            	radat.setVaylia(Integer.parseInt(cursor.getString(8)));
            	radat.setPar(Integer.parseInt(cursor.getString(9)));
            	radat.setKartta(cursor.getString(10));
            	radat.setKuvaus(cursor.getString(11));
            	radat.setPaivityspvm(cursor.getString(12));
                // Adding contact to list
            	listRadat.add(radat);
            } while (cursor.moveToNext());
        }
 
		// Make sure to close the cursor
		cursor.close();
        // return contact list        
        return listRadat;
    }
}