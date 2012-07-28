package com.example.frisbeegolfscores;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBGpsdata {

	private SQLiteDatabase database;
	//private DBGpsdata dbGpsdata;
	private DBAvaus dbAvaus;

	public DBGpsdata(Context context) {
		dbAvaus = new DBAvaus(context);
	}
	
	private String[] allGpsdataColumns = { 
			DBAlustus.COLUMN_Gpsdata_ID,
			DBAlustus.COLUMN_Gpsdata_x,
			DBAlustus.COLUMN_Gpsdata_y,
			DBAlustus.COLUMN_Gpsdata_tulokset_ID,
			DBAlustus.COLUMN_Gpsdata_pvm};

	public void setDBInstance(SQLiteDatabase database) {
		this.database = database;
	}

	// Adding new
    public void addGpsdata(Gpsdata gpsdata) {
        ContentValues values = new ContentValues();
        Log.d("DBGpsdata: ", "Lis‰t‰‰n gps alkaa..");
        //values.put(DBAlustus.COLUMN_Gpsdata_ID,
		values.put(DBAlustus.COLUMN_Gpsdata_x, gpsdata.getX());
		values.put(DBAlustus.COLUMN_Gpsdata_y, gpsdata.getY());
		values.put(DBAlustus.COLUMN_Gpsdata_tulokset_ID, gpsdata.getTulokset_id());
		values.put(DBAlustus.COLUMN_Gpsdata_pvm, gpsdata.getPvm());

        // Inserting Row
		long insertId = database.insert(DBAlustus.TABLE_Gpsdata, null, values);
        //database.close(); // Closing database connection
		Log.d("DBGpsdata: ", "Lis‰t‰‰n gps loppuu..");
    }
 
    // Getting single
    public Gpsdata getGpsdata(long id) { 
        Cursor cursor = database.query(DBAlustus.TABLE_Gpsdata, new String[] { DBAlustus.COLUMN_Gpsdata_ID, DBAlustus.COLUMN_Gpsdata_x, DBAlustus.COLUMN_Gpsdata_y, DBAlustus.COLUMN_Gpsdata_tulokset_ID, DBAlustus.COLUMN_Gpsdata_pvm }, DBAlustus.COLUMN_Gpsdata_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Gpsdata gpsdata = new Gpsdata(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), cursor.getString(4));
        return gpsdata;
    }
    
    // Updating single
	public int updateGpsdata(Gpsdata gpsdata) {
	    ContentValues values = new ContentValues();	 
	  //values.put(DBAlustus.COLUMN_Gpsdata_ID,
		values.put(DBAlustus.COLUMN_Gpsdata_x, gpsdata.getX());
		values.put(DBAlustus.COLUMN_Gpsdata_y, gpsdata.getY());
		values.put(DBAlustus.COLUMN_Gpsdata_tulokset_ID, gpsdata.getTulokset_id());
		values.put(DBAlustus.COLUMN_Gpsdata_pvm, gpsdata.getPvm());
	 
	    // updating row
	    return database.update(DBAlustus.TABLE_Gpsdata, values, DBAlustus.COLUMN_Gpsdata_ID + " = ?", new String[] { String.valueOf(gpsdata.getId()) });
	}
    
	// poistaa tietyn yksittäisen datan
    public void deleteGpsdata(Gpsdata gpsdata) {
		long id = gpsdata.getId();

		database.delete(DBAlustus.TABLE_Gpsdata, DBAlustus.COLUMN_Gpsdata_ID + " = " + id, null);
	}

    // poistaa tietyn tuloksen datan
    public void deleteGpsdataTulos(Gpsdata gpsdata) {
		int tulokset_id = gpsdata.getTulokset_id();

		database.delete(DBAlustus.TABLE_Gpsdata, DBAlustus.COLUMN_Gpsdata_ID + " = " + tulokset_id, null);
	}
    
 // Getting All Contacts
    public List<Gpsdata> haeGpsdata() {
        List<Gpsdata> listGpsdata = new ArrayList<Gpsdata>();

        Cursor cursor = database.query(DBAlustus.TABLE_Gpsdata, allGpsdataColumns, null, null, null, null, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Gpsdata gpsdata = new Gpsdata();
            	gpsdata.setId(Long.parseLong(cursor.getString(0)));
            	gpsdata.setX(cursor.getString(1));
            	gpsdata.setY(cursor.getString(2));
            	gpsdata.setTulokset_id(Integer.parseInt(cursor.getString(0)));
            	gpsdata.setPvm(cursor.getString(4));
                // Adding contact to list
            	listGpsdata.add(gpsdata);
            } while (cursor.moveToNext());
        }
 
		// Make sure to close the cursor
		cursor.close();
        // return contact list        
        return listGpsdata;
    }

    // Getting All Contacts
    public List<Gpsdata> haeGpsdataTulos(int tulokset_id) {
        List<Gpsdata> listGpsdata = new ArrayList<Gpsdata>();

        Cursor cursor = database.query(DBAlustus.TABLE_Gpsdata, new String[] { DBAlustus.COLUMN_Gpsdata_ID, DBAlustus.COLUMN_Gpsdata_x, DBAlustus.COLUMN_Gpsdata_y, DBAlustus.COLUMN_Gpsdata_tulokset_ID, DBAlustus.COLUMN_Gpsdata_pvm }, DBAlustus.COLUMN_Gpsdata_tulokset_ID + "=?", new String[] { String.valueOf(tulokset_id) }, null, null, null, null);
//        Cursor cursor = database.query(DBAlustus.TABLE_Gpsdata, allGpsdataColumns, null, null, null, null, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Gpsdata gpsdata = new Gpsdata();
            	gpsdata.setId(Long.parseLong(cursor.getString(0)));
            	gpsdata.setX(cursor.getString(1));
            	gpsdata.setY(cursor.getString(2));
            	gpsdata.setTulokset_id(Integer.parseInt(cursor.getString(0)));
            	gpsdata.setPvm(cursor.getString(4));
                // Adding contact to list
            	listGpsdata.add(gpsdata);
            } while (cursor.moveToNext());
        }
 
		// Make sure to close the cursor
		cursor.close();
        // return contact list        
        return listGpsdata;
    }
}