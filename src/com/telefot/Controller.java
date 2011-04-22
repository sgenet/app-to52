package com.telefot;

import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class Controller {
	private LocationFinder locationFinder;
	private Location location;
	private Context activityContext;
    private TelefotAlertDB db;
    private TelefotAlert alert;
	
	public void setActivityContext(Context context){
		this.activityContext = context;
        this.locationFinder = new LocationFinder(context);
	}
	
	public void sendALert(String reference){
		
		db = new TelefotAlertDB(activityContext);
		
		location = locationFinder.getLocation();
		
		if(location != null){
			String deviceId = "4E504659374443310920";
			String heading = "nc";
			String addressAlert = "";
			
			Address address = locationFinder.getAddress();
			
			if(address != null){
				addressAlert = address.getAddressLine(0) + " ; "+address.getPostalCode() + " ; "+address.getLocality();
			}
			
			alert = new TelefotAlert(reference, deviceId, location.getLatitude(), location.getLongitude(), location.getSpeed(), addressAlert, heading);
			TelefotServices service = new TelefotServices();
			int result = service.StoreAlert(alert);
			handler.sendEmptyMessage(result);
    	}
		else{
        	Toast.makeText(activityContext, "MERCI d'ACTIVER LE GPS", Toast.LENGTH_LONG).show();
		}
	}
	
    private Handler handler = new Handler() {

    	public void handleMessage(android.os.Message msg) {
            
    		alert.setEndTime();
    		
            db.open();
            db.insertMessage(alert);
            db.close();
            
        	Toast.makeText(activityContext, "Reponse du serveur : "+ msg.what, Toast.LENGTH_LONG).show();
    	};
    };
}
