package com.example.frisbeegolfscores;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBJoukkueet {

	private SQLiteDatabase database;
	//private DBJoukkueet dbJoukkueet;
	private DBAvaus dbAvaus;

	public DBJoukkueet(Context context) {
		dbAvaus = new DBAvaus(context);
	}
	
	private String[] allJoukkueetColumns = { 
			DBAlustus.COLUMN_Joukkueet_ID,
			DBAlustus.COLUMN_Joukkueet_nimi};

	public void setDBInstance(SQLiteDatabase database) {
		this.database = database;
	}

	// Adding new
    public void addJoukkue(Joukkueet joukkueet) {
        ContentValues values = new ContentValues();
        Log.d("DBJoukkueet: ", "Lis‰t‰‰n kaupunki alkaa..");
        //values.put(DBAlustus.COLUMN_Joukkueet_ID,
		values.put(DBAlustus.COLUMN_Joukkueet_nimi, joukkueet.getNimi());

        // Inserting Row
		long insertId = database.insert(DBAlustus.TABLE_Joukkueet, null, values);
        //database.close(); // Closing database connection
		Log.d("DBJoukkueet: ", "Lis‰t‰‰n kaupunki loppuu..");
    }
 
    // Getting single
    public Joukkueet getJoukkue(int id) { 
        Cursor cursor = database.query(DBAlustus.TABLE_Joukkueet, new String[] { DBAlustus.COLUMN_Joukkueet_ID, DBAlustus.COLUMN_Joukkueet_nimi }, DBAlustus.COLUMN_Joukkueet_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Joukkueet joukkueet = new Joukkueet(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        return joukkueet;
    }
    
    // Updating single
	public int updateJoukkue(Joukkueet joukkueet) {
	    ContentValues values = new ContentValues();	 
	  //values.put(DBAlustus.COLUMN_Joukkueet_ID,
		values.put(DBAlustus.COLUMN_Joukkueet_nimi, joukkueet.getNimi());
	 
	    // updating row
	    return database.update(DBAlustus.TABLE_Joukkueet, values, DBAlustus.COLUMN_Joukkueet_ID + " = ?", new String[] { String.valueOf(joukkueet.getId()) });
	}
    
    public void deleteJoukkue(Joukkueet joukkueet) {
		int id = joukkueet.getId();

		database.delete(DBAlustus.TABLE_Joukkueet, DBAlustus.COLUMN_Joukkueet_ID + " = " + id, null);
	}
	
 // Getting All Contacts
    public List<Joukkueet> haeJoukkueet() {
        List<Joukkueet> listJoukkueet = new ArrayList<Joukkueet>();

        Cursor cursor = database.query(DBAlustus.TABLE_Joukkueet, allJoukkueetColumns, null, null, null, null, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Joukkueet joukkueet = new Joukkueet();
            	joukkueet.setId(Integer.parseInt(cursor.getString(0)));
            	joukkueet.setNimi(cursor.getString(1));
                // Adding contact to list
            	listJoukkueet.add(joukkueet);
            } while (cursor.moveToNext());
        }
 
		// Make sure to close the cursor
		cursor.close();
        // return contact list        
        return listJoukkueet;
    }

}
