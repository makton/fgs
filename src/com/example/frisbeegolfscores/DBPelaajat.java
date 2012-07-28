package com.example.frisbeegolfscores;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class DBPelaajat {

	private SQLiteDatabase database;
	//private DBPelaajat dbPelaajat;
	private DBAvaus dbAvaus;

	public DBPelaajat(Context context) {
		dbAvaus = new DBAvaus(context);
	}
	
	private String[] allPelaajatColumns = { 
			DBAlustus.COLUMN_Pelaajat_ID,
			DBAlustus.COLUMN_Pelaajat_nimi,
			DBAlustus.COLUMN_Pelaajat_nayttonimi,
			DBAlustus.COLUMN_Pelaajat_info,
			DBAlustus.COLUMN_Pelaajat_kayttajatunnus,
			DBAlustus.COLUMN_Pelaajat_salasana,
			DBAlustus.COLUMN_Pelaajat_joukkue,
			DBAlustus.COLUMN_Pelaajat_luontipvm,
			DBAlustus.COLUMN_Pelaajat_paivityspvm};
	
	public void setDBInstance(SQLiteDatabase database) {
		this.database = database;
	}
	
	// Adding new
    public void addPelaaja(Pelaajat pelaajat) {
        ContentValues values = new ContentValues();                
        
        Log.d("DBPelaajat: ", "Lis‰t‰‰n pelaaja alkaa..");
        
        //values.put(DBAlustus.COLUMN_Pelaajat_ID,
		values.put(DBAlustus.COLUMN_Pelaajat_nimi, pelaajat.getNimi());
		values.put(DBAlustus.COLUMN_Pelaajat_nayttonimi, pelaajat.getNayttonimi());
		values.put(DBAlustus.COLUMN_Pelaajat_info, pelaajat.getInfo());
		values.put(DBAlustus.COLUMN_Pelaajat_kayttajatunnus, pelaajat.getKayttajatunnus());
		values.put(DBAlustus.COLUMN_Pelaajat_salasana, pelaajat.getSalasana());
		values.put(DBAlustus.COLUMN_Pelaajat_joukkue, pelaajat.getJoukkue());
		values.put(DBAlustus.COLUMN_Pelaajat_luontipvm, pelaajat.getLuontipvm());
		values.put(DBAlustus.COLUMN_Pelaajat_paivityspvm, pelaajat.getPaivityspvm());
		Log.d("DBPelaajat: ", "Lis‰t‰‰n pelaaja loppuu..");
        // Inserting Row
		long insertId = database.insert(DBAlustus.TABLE_Pelaajat, null, values);
        //database.close(); // Closing database connection
		Log.d("DBPelaajat: ", "pelaaja tallennettu.." + insertId);
    }
 
    // Getting single
    public Pelaajat getPelaaja(int id) { 
    	Log.d("DBPelaajat: ", "Haetaan pelaaja..");

        Cursor cursor = database.query(DBAlustus.TABLE_Pelaajat, new String[] { DBAlustus.COLUMN_Pelaajat_ID, DBAlustus.COLUMN_Pelaajat_nimi, DBAlustus.COLUMN_Pelaajat_nayttonimi, DBAlustus.COLUMN_Pelaajat_info, DBAlustus.COLUMN_Pelaajat_kayttajatunnus, DBAlustus.COLUMN_Pelaajat_salasana, DBAlustus.COLUMN_Pelaajat_joukkue, DBAlustus.COLUMN_Pelaajat_luontipvm, DBAlustus.COLUMN_Pelaajat_paivityspvm }, DBAlustus.COLUMN_Pelaajat_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Pelaajat pelaajat = new Pelaajat(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), Integer.parseInt(cursor.getString(6)), cursor.getString(7), cursor.getString(8));
        return pelaajat;
    }
    
    // Updating single
	public int updatePelaaja(Pelaajat pelaajat) {
	    ContentValues values = new ContentValues();	
	    
	    Log.d("DBPelaajat: ", "P‰ivitet‰‰n pelaaja..");

	  //values.put(DBAlustus.COLUMN_Pelaajat_ID,
		values.put(DBAlustus.COLUMN_Pelaajat_nimi, pelaajat.getNimi());
		values.put(DBAlustus.COLUMN_Pelaajat_nayttonimi, pelaajat.getNayttonimi());
		values.put(DBAlustus.COLUMN_Pelaajat_info, pelaajat.getInfo());
		values.put(DBAlustus.COLUMN_Pelaajat_kayttajatunnus, pelaajat.getKayttajatunnus());
		values.put(DBAlustus.COLUMN_Pelaajat_salasana, pelaajat.getSalasana());
		values.put(DBAlustus.COLUMN_Pelaajat_joukkue, pelaajat.getJoukkue());
		values.put(DBAlustus.COLUMN_Pelaajat_luontipvm, pelaajat.getLuontipvm());
		values.put(DBAlustus.COLUMN_Pelaajat_paivityspvm, pelaajat.getPaivityspvm());
	 
	    // updating row
	    return database.update(DBAlustus.TABLE_Pelaajat, values, DBAlustus.COLUMN_Pelaajat_ID + " = ?", new String[] { String.valueOf(pelaajat.getId()) });
	}
	
    public void deletePelaaja(Pelaajat pelaajat) {
		int id = pelaajat.getId();

		database.delete(DBAlustus.TABLE_Pelaajat, DBAlustus.COLUMN_Pelaajat_ID + " = " + id, null);
	}
	
 // Getting All Contacts
    public List<Pelaajat> haePelaajat() {
        List<Pelaajat> listPelaajat = new ArrayList<Pelaajat>();

        Cursor cursor = database.query(DBAlustus.TABLE_Pelaajat, allPelaajatColumns, null, null, null, null, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Pelaajat pelaajat = new Pelaajat();
            	pelaajat.setId(Integer.parseInt(cursor.getString(0)));
            	pelaajat.setNimi(cursor.getString(1));
            	pelaajat.setNayttonimi(cursor.getString(2));
            	pelaajat.setInfo(cursor.getString(3));
            	pelaajat.setKayttajatunnus(cursor.getString(4));
            	pelaajat.setSalasana(cursor.getString(5));
            	pelaajat.setJoukkue(Integer.parseInt(cursor.getString(6)));
            	pelaajat.setLuontipvm(cursor.getString(7));
            	pelaajat.setPaivityspvm(cursor.getString(8));
                // Adding contact to list
            	listPelaajat.add(pelaajat);
            } while (cursor.moveToNext());
        }
 
		// Make sure to close the cursor
		cursor.close();
        // return contact list        
        return listPelaajat;
    }

}
