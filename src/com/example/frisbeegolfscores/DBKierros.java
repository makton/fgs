package com.example.frisbeegolfscores;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBKierros {

	private SQLiteDatabase database;
	//private DBKierros dbKierros;
	private DBAvaus dbAvaus;

	public DBKierros(Context context) {
		dbAvaus = new DBAvaus(context);
	}
	
	private String[] allKierrosColumns = { 
			DBAlustus.COLUMN_Kierros_ID,
			DBAlustus.COLUMN_Kierros_rata_ID,
			DBAlustus.COLUMN_Kierros_pvm};

	public void setDBInstance(SQLiteDatabase database) {
		this.database = database;
	}

	// Adding new
    public void addKierros(Kierros kierros) {
        ContentValues values = new ContentValues();
        Log.d("DBKierros: ", "Lis‰t‰‰n kierros alkaa..");
        //values.put(DBAlustus.COLUMN_Kierros_ID,
		values.put(DBAlustus.COLUMN_Kierros_rata_ID, kierros.getRata_id());
		values.put(DBAlustus.COLUMN_Kierros_pvm, kierros.getPvm());

        // Inserting Row
		long insertId = database.insert(DBAlustus.TABLE_Kierros, null, values);
        //database.close(); // Closing database connection
		Log.d("DBKierros: ", "Lis‰t‰‰n kierros loppuu..");
    }
 
    // Getting single
    public Kierros getKierros(int id) { 
        Cursor cursor = database.query(DBAlustus.TABLE_Kierros, new String[] { DBAlustus.COLUMN_Kierros_ID, DBAlustus.COLUMN_Kierros_rata_ID, DBAlustus.COLUMN_Kierros_pvm }, DBAlustus.COLUMN_Kierros_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Kierros kierros = new Kierros(cursor.getInt(0), cursor.getInt(1), cursor.getString(2));
        cursor.close();
        return kierros;
    }
    
    // Updating single
	public int updateKierros(Kierros kierros) {
	    ContentValues values = new ContentValues();	 
	  //values.put(DBAlustus.COLUMN_Kierros_ID,
		values.put(DBAlustus.COLUMN_Kierros_rata_ID, kierros.getRata_id());
		values.put(DBAlustus.COLUMN_Kierros_pvm, kierros.getPvm());
	 
	    // updating row
	    return database.update(DBAlustus.TABLE_Kierros, values, DBAlustus.COLUMN_Kierros_ID + " = ?", new String[] { String.valueOf(kierros.getId()) });
	}
    
    public void deleteKierros(Kierros kierros) {
		int id = kierros.getId();

		database.delete(DBAlustus.TABLE_Kierros, DBAlustus.COLUMN_Kierros_ID + " = " + id, null);
	}
	
 // Getting All Contacts
    public List<Kierros> haeKierros() {
        List<Kierros> listKierros = new ArrayList<Kierros>();

        Cursor cursor = database.query(DBAlustus.TABLE_Kierros, allKierrosColumns, null, null, null, null, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Kierros kierros = new Kierros();
            	kierros.setId(cursor.getInt(0));
            	kierros.setRata_id(cursor.getInt(1));
            	kierros.setPvm(cursor.getString(2));
                // Adding contact to list
            	listKierros.add(kierros);
            } while (cursor.moveToNext());
        }
 
		// Make sure to close the cursor
		cursor.close();
        // return contact list        
        return listKierros;
    }

}