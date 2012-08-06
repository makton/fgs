package com.example.frisbeegolfscores;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

import android.app.AlertDialog;
import android.R.drawable;

public class Sekalaiset {

	final int DIALOG_ENABLE_GPS = 1;
	
	public String ConvertInputStream(InputStream data) {
		BufferedReader rd = new BufferedReader(new InputStreamReader(data), 4096);
		String line;
		StringBuilder sb =  new StringBuilder();
		try {
			while ((line = rd.readLine()) != null) {
					sb.append(line);
			}
			rd.close();
		}
		catch (Exception e) {
			e.printStackTrace();		}
		String tulos = sb.toString();
		return tulos;

	}
	
	public boolean isNetworkAvailable(Context context) {
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				NetworkInfo[] info = connectivity.getAllNetworkInfo();
				if (info != null) {
					for (int i = 0; i < info.length; i++) {
						if (info[i].getState() == NetworkInfo.State.CONNECTED) {
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return false;
	}
	
	public void disableConnectionReuseIfNecessary() {
	    // HTTP connection reuse which was buggy pre-froyo
	    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
	        System.setProperty("http.keepAlive", "false");
	    }
	}
	
	public void showDialog(Context context, int nro) {	
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
        switch(nro){
        	case DIALOG_ENABLE_GPS:
				builder.setCancelable(true);
				builder.setIcon(drawable.dialog_frame);
				builder.setTitle("TyhmŠ kysymys");
				builder.setInverseBackgroundForced(true);
				builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					  //@Override
					  public void onClick(DialogInterface dialog, int which) {
					    dialog.dismiss();
					  }
					});
					builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
					  //@Override
					  public void onClick(DialogInterface dialog, int which) {
				    dialog.dismiss();
				}
				});
				AlertDialog alert = builder.create();
				alert.show();
		        break;
            default:
                break;
        }
	}
	
	public void showToast(Context context, String message) {
		Toast msg = Toast.makeText(context, message, Toast.LENGTH_LONG);
		//msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
		msg.show();
	}
	
	public String haeAika() {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss:SSS");   
        Date date = new Date();
        String now = formatter.format(date);
        return now;
	}
}
