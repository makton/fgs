package com.example.frisbeegolfscores;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class DBTulokset{

	private SQLiteDatabase database;
	//private DBTulokset dbTulokset;
	private DBAvaus dbAvaus;

	public DBTulokset(Context context) {
		dbAvaus = new DBAvaus(context);
	}
	
	private String[] allTuloksetColumns = { 
			DBAlustus.COLUMN_Tulokset_ID,
			DBAlustus.COLUMN_Tulokset_pelaaja_ID,
			DBAlustus.COLUMN_Tulokset_rata_ID,
			DBAlustus.COLUMN_Tulokset_vayla_ID,
			DBAlustus.COLUMN_Tulokset_kierros_ID,
			DBAlustus.COLUMN_Tulokset_tulos,	
			DBAlustus.COLUMN_Tulokset_pvm,
			DBAlustus.COLUMN_Tulokset_kesto};
	
	public void setDBInstance(SQLiteDatabase database) {
		this.database = database;
	}
	
	// Adding new
    public void addTulos(Tulokset tulokset) {
        ContentValues values = new ContentValues();                
        
        Log.d("DBTulokset: ", "Lis‰t‰‰n tulos alkaa..");
        
        //values.put(DBAlustus.COLUMN_Tulokset_ID,
		values.put(DBAlustus.COLUMN_Tulokset_pelaaja_ID, tulokset.getPelaaja_id());
		values.put(DBAlustus.COLUMN_Tulokset_rata_ID, tulokset.getRata_id());
		values.put(DBAlustus.COLUMN_Tulokset_vayla_ID, tulokset.getVayla_id());
		values.put(DBAlustus.COLUMN_Tulokset_kierros_ID, tulokset.getKierros_id());
		values.put(DBAlustus.COLUMN_Tulokset_tulos, tulokset.getTulos());		
		values.put(DBAlustus.COLUMN_Tulokset_pvm, tulokset.getPvm());
		values.put(DBAlustus.COLUMN_Tulokset_kesto, tulokset.getKesto());
		Log.d("DBTulokset: ", "Lis‰t‰‰n tulos loppuu..");
        // Inserting Row
		long insertId = database.insert(DBAlustus.TABLE_Tulokset, null, values);
        //database.close(); // Closing database connection
		Log.d("DBTulokset: ", "tulos tallennettu.." + insertId);
    }
 
    // Getting single
    public Tulokset getTulos(int id) { 
    	Log.d("DBTulokset: ", "Haetaan tulos..");

        Cursor cursor = database.query(DBAlustus.TABLE_Tulokset, new String[] { DBAlustus.COLUMN_Tulokset_ID, DBAlustus.COLUMN_Tulokset_pelaaja_ID, DBAlustus.COLUMN_Tulokset_rata_ID, DBAlustus.COLUMN_Tulokset_vayla_ID, DBAlustus.COLUMN_Tulokset_kierros_ID, DBAlustus.COLUMN_Tulokset_tulos, DBAlustus.COLUMN_Tulokset_pvm, DBAlustus.COLUMN_Tulokset_kesto }, DBAlustus.COLUMN_Tulokset_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Tulokset tulokset = new Tulokset(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), cursor.getString(6), cursor.getString(7));
        return tulokset;
    }
    
    // Updating single
	public int updateTulos(Tulokset tulokset) {
	    ContentValues values = new ContentValues();	
	    
	    Log.d("DBTulokset: ", "P‰ivitet‰‰n tulos..");

	  //values.put(DBAlustus.COLUMN_Tulokset_ID,
		values.put(DBAlustus.COLUMN_Tulokset_pelaaja_ID, tulokset.getPelaaja_id());
		values.put(DBAlustus.COLUMN_Tulokset_rata_ID, tulokset.getRata_id());
		values.put(DBAlustus.COLUMN_Tulokset_vayla_ID, tulokset.getVayla_id());
		values.put(DBAlustus.COLUMN_Tulokset_kierros_ID, tulokset.getKierros_id());
		values.put(DBAlustus.COLUMN_Tulokset_tulos, tulokset.getTulos());
		values.put(DBAlustus.COLUMN_Tulokset_pvm, tulokset.getPvm());
		values.put(DBAlustus.COLUMN_Tulokset_kesto, tulokset.getKesto());
	    // updating row
	    return database.update(DBAlustus.TABLE_Tulokset, values, DBAlustus.COLUMN_Tulokset_ID + " = ?", new String[] { String.valueOf(tulokset.getId()) });
	}
	
    public void deleteTulos(Tulokset tulokset) {
		int id = tulokset.getId();

		database.delete(DBAlustus.TABLE_Tulokset, DBAlustus.COLUMN_Tulokset_ID + " = " + id, null);
	}
	
 // Getting All Contacts
    public List<Tulokset> haeTulokset() {
        List<Tulokset> listTulokset = new ArrayList<Tulokset>();

        Cursor cursor = database.query(DBAlustus.TABLE_Tulokset, allTuloksetColumns, null, null, null, null, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Tulokset tulokset = new Tulokset();
            	tulokset.setId(Integer.parseInt(cursor.getString(0)));
            	tulokset.setPelaaja_id(Integer.parseInt(cursor.getString(1)));
            	tulokset.setRata_id(Integer.parseInt(cursor.getString(2)));
            	tulokset.setVayla_id(Integer.parseInt(cursor.getString(3)));
            	tulokset.setKierros_id(Integer.parseInt(cursor.getString(4)));
            	tulokset.setTulos(Integer.parseInt(cursor.getString(5)));
            	tulokset.setPvm(cursor.getString(6));
            	tulokset.setKesto(cursor.getString(7));
                // Adding contact to list
            	listTulokset.add(tulokset);
            } while (cursor.moveToNext());
        }
 
		// Make sure to close the cursor
		cursor.close();
        // return contact list        
        return listTulokset;
    }
}