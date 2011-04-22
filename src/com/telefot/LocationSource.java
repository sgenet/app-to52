package com.telefot;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

public class LocationSource {
	
	public static boolean GPSLocationEnabled(Context context){
		try{
			LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		}
		catch(SecurityException e){
			// Problème de permission dans le manifeste
			Log.e("LocationSource", "Veuillez ajouter la permission ACCESS_FINE_LOCATION dans le manifest.xml");
		}
		catch(Exception e){
			Log.e("LocationSource", e.getMessage());
		}
		
		return false;
	}
	
	public static boolean GSMLocationEnabled(Context context){
		try{
			LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		}
		catch(SecurityException e){
			// Problème de permission dans le manifeste
			Log.e("LocationSource", "Veuillez ajouter la permission ACCESS_FINE_LOCATION dans le manifest.xml");
		}
		catch(Exception e){
			Log.e("LocationSource", e.getMessage());
		}
		
		return false;
	}
	
	public static String getBestActivLocationManager(Context context){
		if(LocationSource.GPSLocationEnabled(context))
			return LocationManager.GPS_PROVIDER;
		else if(LocationSource.GSMLocationEnabled(context))
			return LocationManager.NETWORK_PROVIDER;
		else
			return null;
	}
	
}
