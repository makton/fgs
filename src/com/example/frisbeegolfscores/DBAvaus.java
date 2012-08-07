package com.example.frisbeegolfscores;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBAvaus {

	public SQLiteDatabase database = null;
	private DBAlustus dbAlustus;
	
	public DBAvaus(Context context) {
		dbAlustus = new DBAlustus(context);
	}	

	public SQLiteDatabase open() throws SQLException {
		Log.d("DBAvaus: ", "Avataan yhteys..");
		database = dbAlustus.getWritableDatabase();
		return database;
	}

	public void close() {
		Log.d("DBAvaus: ", "Suljetaan yhteys..");
		if (database != null) {
			dbAlustus.close();
		}
	}

	public SQLiteDatabase Instance() {
		return database;
	}
	
	public boolean status() {
		if (database == null) {
			return false;
		}
		return database.isOpen();
	}
	
}
