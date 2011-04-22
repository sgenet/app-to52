package com.telefot;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class LocationFinder implements LocationListener{

	private Context context;
	private LocationManager locationManager;
	private String locationProvider;
	
	private Location location;
	
	public LocationFinder(Context context){
		this.context = context;
		try{
			this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			this.updateLocationProvider();
			this.initListeners();
		} catch(Exception e){
			Log.e("LocationFinder", e.getMessage());
		}
	}
	
	public void updateLocationProvider(){
		this.locationProvider = LocationSource.getBestActivLocationManager(this.context);
	}
	
	private void initListeners(){
		
		if(this.locationProvider == null)
			return;
		
		if(this.location == null){
			this.location = this.locationManager.getLastKnownLocation(this.locationProvider);
		}
		
		this.locationManager.requestLocationUpdates(this.locationProvider, 1001, 101, this);
		
	}

	@Override
	public void onLocationChanged(Location location) {
		this.location = location;
	}

	@Override
	public void onProviderDisabled(String provider) {
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extra) {
		this.locationManager.removeUpdates(this);
		this.updateLocationProvider();
		this.initListeners();
	}
	
	public Location getLocation(){
		return this.location;
	}
	
	public Address getAddress() {
		
		if(this.location == null)
			return null;
 
		Geocoder geo = new Geocoder(this.context);
		try {
			List <Address> adresses = geo.getFromLocation(location.getLatitude(), location.getLongitude(),1);
 
			if(adresses != null && adresses.size() == 1){
				Address adresse = adresses.get(0);
				return adresse;
			}
		} catch (IOException e) {
			Log.e("LocationFinder", "GetAdress error");
		}
		
		return null;

	}
	
}
