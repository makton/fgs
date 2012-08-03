package com.example.frisbeegolfscores;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class DBVaylat {

	private SQLiteDatabase database;
	//private DBVaylat dbVaylat;
	private DBAvaus dbAvaus;

	public DBVaylat(Context context) {
		dbAvaus = new DBAvaus(context);
	}
	
	private String[] allVaylatColumns = { 
			DBAlustus.COLUMN_Vaylat_ID,
			DBAlustus.COLUMN_Vaylat_rata_ID,
			DBAlustus.COLUMN_Vaylat_jarjestys,
			DBAlustus.COLUMN_Vaylat_pituus,
			DBAlustus.COLUMN_Vaylat_par,
			DBAlustus.COLUMN_Vaylat_kuvaus,
			DBAlustus.COLUMN_Vaylat_paivityspvm};
	
	public void setDBInstance(SQLiteDatabase database) {
		this.database = database;
	}
	
	// Adding new
    public void addVayla(Vaylat vaylat) {
        ContentValues values = new ContentValues();                
        
        Log.d("DBVaylat: ", "Lis‰t‰‰n vayla alkaa..");
        
        //values.put(DBAlustus.COLUMN_Vaylat_ID,
		values.put(DBAlustus.COLUMN_Vaylat_rata_ID, vaylat.getRata_id());
		values.put(DBAlustus.COLUMN_Vaylat_jarjestys, vaylat.getJarjestys());
		values.put(DBAlustus.COLUMN_Vaylat_pituus, vaylat.getPituus());
		values.put(DBAlustus.COLUMN_Vaylat_par, vaylat.getPar());
		values.put(DBAlustus.COLUMN_Vaylat_kuvaus, vaylat.getKuvaus());
		values.put(DBAlustus.COLUMN_Vaylat_paivityspvm, vaylat.getPaivityspvm());
		Log.d("DBVaylat: ", "Lis‰t‰‰n vayla loppuu..");
        // Inserting Row
		long insertId = database.insert(DBAlustus.TABLE_Vaylat, null, values);
        //database.close(); // Closing database connection
		Log.d("DBVaylat: ", "vayla tallennettu.." + insertId);
    }
 
    // Getting single
    public Vaylat getVayla(int id) { 
    	Log.d("DBVaylat: ", "Haetaan vayla..");

        Cursor cursor = database.query(DBAlustus.TABLE_Vaylat, new String[] { DBAlustus.COLUMN_Vaylat_ID, DBAlustus.COLUMN_Vaylat_rata_ID, DBAlustus.COLUMN_Vaylat_jarjestys, DBAlustus.COLUMN_Vaylat_pituus, DBAlustus.COLUMN_Vaylat_par, DBAlustus.COLUMN_Vaylat_kuvaus, DBAlustus.COLUMN_Vaylat_paivityspvm }, DBAlustus.COLUMN_Vaylat_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Vaylat vaylat = new Vaylat(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6));
        return vaylat;
    }
    
    // Updating single
	public int updateVayla(Vaylat vaylat) {
	    ContentValues values = new ContentValues();	
	    
	    Log.d("DBVaylat: ", "P‰ivitet‰‰n vayla..");

	  //values.put(DBAlustus.COLUMN_Vaylat_ID,
		values.put(DBAlustus.COLUMN_Vaylat_rata_ID, vaylat.getRata_id());
		values.put(DBAlustus.COLUMN_Vaylat_jarjestys, vaylat.getJarjestys());
		values.put(DBAlustus.COLUMN_Vaylat_pituus, vaylat.getPituus());
		values.put(DBAlustus.COLUMN_Vaylat_par, vaylat.getPar());
		values.put(DBAlustus.COLUMN_Vaylat_kuvaus, vaylat.getKuvaus());
		values.put(DBAlustus.COLUMN_Vaylat_paivityspvm, vaylat.getPaivityspvm());
	 
	    // updating row
	    return database.update(DBAlustus.TABLE_Vaylat, values, DBAlustus.COLUMN_Vaylat_ID + " = ?", new String[] { String.valueOf(vaylat.getId()) });
	}
	
    public void deleteVayla(Vaylat vaylat) {
		int id = vaylat.getId();

		database.delete(DBAlustus.TABLE_Vaylat, DBAlustus.COLUMN_Vaylat_ID + " = " + id, null);
	}
	
 // Getting All Contacts
    public List<Vaylat> haeVaylat() {
        List<Vaylat> listVaylat = new ArrayList<Vaylat>();

        Cursor cursor = database.query(DBAlustus.TABLE_Vaylat, allVaylatColumns, null, null, null, null, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Vaylat vaylat = new Vaylat();
            	vaylat.setId(cursor.getInt(0));
            	vaylat.setRata_id(cursor.getInt(1));
            	vaylat.setJarjestys(cursor.getInt(2));
            	vaylat.setPituus(cursor.getInt(3));
            	vaylat.setPar(cursor.getInt(4));
            	vaylat.setKuvaus(cursor.getString(5));
            	vaylat.setPaivityspvm(cursor.getString(6));
                // Adding contact to list
            	listVaylat.add(vaylat);
            } while (cursor.moveToNext());
        }
 
		// Make sure to close the cursor
		cursor.close();
        // return contact list        
        return listVaylat;
    }
}
