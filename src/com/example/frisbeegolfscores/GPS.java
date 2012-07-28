package com.example.frisbeegolfscores;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import com.google.android.maps.GeoPoint;

public class GPS implements LocationListener {

	private Context context;
	private GeoPoint mMyLocation;
	private volatile boolean mIsMyLocationEnabled;
	private Queue<Runnable> mRunnableFirstFixQueue = new LinkedList<Runnable>();
	private Queue<Runnable> mRunnable = new LinkedList<Runnable>();
	private static final int TWO_MINUTES = 1000 * 60 * 2;
	
	//private String[] PROVIDERS_NAME = new String[] { LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER };
	private String[] PROVIDERS_NAME = new String[] { LocationManager.GPS_PROVIDER };
	
	public GPS(Context context) {
		this.context = context;
	}
	
	public void runOnFirstFix(Runnable runnable) {
		if (runnable == null) {
			throw new IllegalArgumentException("MyLocation Exception: runnable must not be null.");
		}		
		mRunnableFirstFixQueue.add(runnable);
	}
	
	public void runOnLocationUpdate(Runnable runnable) {
		if (runnable == null) {
			throw new IllegalArgumentException("MyLocation Exception: runnable must not be null");
		}	
		mRunnable.add(runnable);
	}
	
	public synchronized void enableMyLocation() {
		final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
		final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 10; // in Meters

		//Read more: http://www.javacodegeeks.com/2010/09/android-location-based-services.html#ixzz20uXKXVJ6

		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		
		Location[] candidates = new Location[PROVIDERS_NAME.length];
		int count = 0;
		for (String provider : PROVIDERS_NAME) {
			mIsMyLocationEnabled = true;
			Log.d("GPS provider: ", provider);
			locationManager.requestLocationUpdates(provider, MINIMUM_TIME_BETWEEN_UPDATES, MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, this);
			Location location = locationManager.getLastKnownLocation(provider);
			if(location == null) {
				continue;
			}
			candidates[count++] = location;
		}
		
		Location firstFix = null;
		for (int i = 0; i < count; i++) {
			firstFix = candidates[i];
		}
		if(firstFix != null) {
			onLocationChanged(firstFix);
		}
	}
	
	public void disableMyLocation() {
		if (mIsMyLocationEnabled) {
			LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			locationManager.removeUpdates(this);
			mIsMyLocationEnabled = false;
		}
	}
	
	public GeoPoint getMyLocation() {
		return mMyLocation;
	}
	
	public boolean getStatus() {
		return mIsMyLocationEnabled;
	}
	
	//@Override
	public void onLocationChanged(Location location) {
		mMyLocation = new GeoPoint((int)(location.getLatitude()*1E6), (int)(location.getLongitude()*1E6));
		Log.d("gps 1: ",String.valueOf(location.getLatitude())+ " " + String.valueOf(location.getLongitude()));
		//Run on first fix
		Runnable runnable;
		while ((runnable = mRunnableFirstFixQueue.poll()) != null) {
			new Thread(runnable).start();
		}
		
		//Run on any location updates.
		Iterator<Runnable> runnables = mRunnable.iterator();
		while (runnables.hasNext()) {
			Runnable updateRunnable = (Runnable) runnables.next();
			new Thread(updateRunnable).start();
		}
	}
	
	//@Override
	public void onProviderDisabled(String provider) {
	// TODO Auto-generated method stub
		Log.d("provider enabled: ", provider);
	}
	
	//@Override
	public void onProviderEnabled(String provider) {
	// TODO Auto-generated method stub
	Log.d("provider enabled: ", provider);
	}
	
	//@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	// TODO Auto-generated method stub
	Log.d("provider enabled: ", provider + " status: " + String.valueOf(status));
	}
	    
	public boolean checkLocationSetting(Context context) {
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	    final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	
	    return gpsEnabled;
	}

	private void showEnableLocationSettings() {
	    Intent settingsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	    context.startActivity(settingsIntent);
	}

	/** Determines whether one Location reading is better than the current Location fix
	  * @param location  The new Location that you want to evaluate
	  * @param currentBestLocation  The current Location fix, to which you want to compare the new one
	  */
	protected boolean isBetterLocation(Location location, Location currentBestLocation) {
	    if (currentBestLocation == null) {
	        // A new location is always better than no location
	        return true;
	    }

	    // Check whether the new location fix is newer or older
	    long timeDelta = location.getTime() - currentBestLocation.getTime();
	    boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
	    boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
	    boolean isNewer = timeDelta > 0;

	    // If it's been more than two minutes since the current location, use the new location
	    // because the user has likely moved
	    if (isSignificantlyNewer) {
	        return true;
	    // If the new location is more than two minutes older, it must be worse
	    } else if (isSignificantlyOlder) {
	        return false;
	    }

	    // Check whether the new location fix is more or less accurate
	    int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
	    boolean isLessAccurate = accuracyDelta > 0;
	    boolean isMoreAccurate = accuracyDelta < 0;
	    boolean isSignificantlyLessAccurate = accuracyDelta > 200;

	    // Check if the old and new location are from the same provider
	    boolean isFromSameProvider = isSameProvider(location.getProvider(),
	            currentBestLocation.getProvider());

	    // Determine location quality using a combination of timeliness and accuracy
	    if (isMoreAccurate) {
	        return true;
	    } else if (isNewer && !isLessAccurate) {
	        return true;
	    } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
	        return true;
	    }
	    return false;
	}
	
	/** Checks whether two providers are the same */
	private boolean isSameProvider(String provider1, String provider2) {
	    if (provider1 == null) {
	      return provider2 == null;
	    }
	    return provider1.equals(provider2);
	}
}
