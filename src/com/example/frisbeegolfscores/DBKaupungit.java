package com.example.frisbeegolfscores;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBKaupungit {

	private SQLiteDatabase database;
	//private DBKaupungit dbKaupungit;
	private DBAvaus dbAvaus;

	public DBKaupungit(Context context) {
		dbAvaus = new DBAvaus(context);
	}
	
	private String[] allKaupungitColumns = { 
			DBAlustus.COLUMN_Kaupungit_ID,
			DBAlustus.COLUMN_Kaupungit_nimi,
			DBAlustus.COLUMN_Kaupungit_paivityspvm};

	public void setDBInstance(SQLiteDatabase database) {
		this.database = database;
	}

	// Adding new
    public void addKaupunki(Kaupungit kaupungit) {
        ContentValues values = new ContentValues();
        Log.d("DBKaupungit: ", "Lis‰t‰‰n kaupunki alkaa..");
        //values.put(DBAlustus.COLUMN_Kaupungit_ID,
		values.put(DBAlustus.COLUMN_Kaupungit_nimi, kaupungit.getNimi());
		values.put(DBAlustus.COLUMN_Kaupungit_paivityspvm, kaupungit.getPaivityspvm());

        // Inserting Row
		long insertId = database.insert(DBAlustus.TABLE_Kaupungit, null, values);
        //database.close(); // Closing database connection
		Log.d("DBKaupungit: ", "Lis‰t‰‰n kaupunki loppuu..");
    }
 
    // Getting single
    public Kaupungit getKaupunki(int id) { 
        Cursor cursor = database.query(DBAlustus.TABLE_Kaupungit, new String[] { DBAlustus.COLUMN_Kaupungit_ID, DBAlustus.COLUMN_Kaupungit_nimi, DBAlustus.COLUMN_Kaupungit_paivityspvm }, DBAlustus.COLUMN_Kaupungit_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Kaupungit kaupungit = new Kaupungit(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return kaupungit;
    }
    
    // Updating single
	public int updateKaupunki(Kaupungit kaupungit) {
	    ContentValues values = new ContentValues();	 
	  //values.put(DBAlustus.COLUMN_Kaupungit_ID,
		values.put(DBAlustus.COLUMN_Kaupungit_nimi, kaupungit.getNimi());
		values.put(DBAlustus.COLUMN_Kaupungit_paivityspvm, kaupungit.getPaivityspvm());
	 
	    // updating row
	    return database.update(DBAlustus.TABLE_Kaupungit, values, DBAlustus.COLUMN_Kaupungit_ID + " = ?", new String[] { String.valueOf(kaupungit.getId()) });
	}
    
    public void deleteKaupunki(Kaupungit kaupungit) {
		int id = kaupungit.getId();

		database.delete(DBAlustus.TABLE_Kaupungit, DBAlustus.COLUMN_Kaupungit_ID + " = " + id, null);
	}
	
 // Getting All Contacts
    public List<Kaupungit> haeKaupungit() {
        List<Kaupungit> listKaupungit = new ArrayList<Kaupungit>();

        Cursor cursor = database.query(DBAlustus.TABLE_Kaupungit, allKaupungitColumns, null, null, null, null, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Kaupungit kaupungit = new Kaupungit();
            	kaupungit.setId(Integer.parseInt(cursor.getString(0)));
            	kaupungit.setNimi(cursor.getString(1));
            	kaupungit.setPaivityspvm(cursor.getString(2));
                // Adding contact to list
            	listKaupungit.add(kaupungit);
            } while (cursor.moveToNext());
        }
 
		// Make sure to close the cursor
		cursor.close();
        // return contact list        
        return listKaupungit;
    }

}
