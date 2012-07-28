package com.example.frisbeegolfscores;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import android.util.Log;


public class Http {
	
	public InputStream GetUrl(String osoite) {
		HttpURLConnection con = null;
		URL url;
		InputStream is=null;
		try {
			url = new URL(osoite);
			con = (HttpURLConnection) url.openConnection();
			con.setReadTimeout(10000 /* milliseconds */);
			con.setConnectTimeout(15000 /* milliseconds */);
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.addRequestProperty("Referer", "http://www.inter.net");
			Log.d("osoite: ", osoite);
			is = con.getInputStream();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return is;
	}
	
	public InputStream PostUrl(String osoite) {
        InputStream myInputStream =null;
		StringBuilder sb = new StringBuilder();
		        //adding some data to send along with the request to the server
		sb.append("name=Anthony");
		URL url;
		try {
			url = new URL(osoite);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    // this is were we're adding post data to the request
			wr.write(sb.toString());
			wr.flush();
			myInputStream = conn.getInputStream();
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return myInputStream;
	}

}
