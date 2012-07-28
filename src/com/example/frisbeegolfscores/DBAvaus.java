package com.example.frisbeegolfscores;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBAvaus {

	public SQLiteDatabase database;
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
		dbAlustus.close();
	}

	public SQLiteDatabase Instance() {
		return database;
	}
	
}