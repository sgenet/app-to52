package com.telefot;

import java.util.Calendar;
import java.util.Date;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import android.util.Log;

public class TelefotServices {
	
	private static final String SOAP_ACTION = "http://telefot-set.utbm.fr/services.php/StoreAlert";
	private static final String METHOD_NAME = "StoreAlert";
	private static final String NAMESPACE = "";
	private static final String URL = "http://telefot-set.utbm.fr/services.php";
	
	public TelefotServices(){
		
	}

	public int StoreAlert(TelefotAlert alert){
		
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
		AndroidHttpTransport aht = new AndroidHttpTransport(URL);
		
		// Get current timestamp
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        long currentTimestamp = new java.sql.Timestamp(now.getTime()).getTime();
        
        // Add request properties
        Log.e("DATAS", alert.getDeviceId() + " " + this.getCoordinatesString(alert.getLatitude(), alert.getLongitude()) + " " + alert.getHeading() + " " + currentTimestamp + " " + alert.getSpeed());
        request.addProperty("alertType", 		alert.getReference());
        request.addProperty("deviceId", 		alert.getDeviceId());
        request.addProperty("coordinates", 		this.getCoordinatesString(alert.getLatitude(), alert.getLongitude()));
        request.addProperty("heading", 			alert.getHeading());
        request.addProperty("sentTime", 		""+currentTimestamp);
        request.addProperty("speed", 			alert.getSpeed());
        
        // Necessary to fix bug with speed property
        MarshalFloat fl = new MarshalFloat();
        fl.register(soapEnvelope);
        
        // Send request
        soapEnvelope.setOutputSoapObject(request);
        soapEnvelope.encodingStyle = "http://schemas.xmlsoap.org/soap/encoding/";
        soapEnvelope.setOutputSoapObject(request);
        soapEnvelope.env="http://schemas.xmlsoap.org/soap/envelope/";
        soapEnvelope.enc="http://schemas.xmlsoap.org/soap/encoding/";
        soapEnvelope.dotNet = false;
        soapEnvelope.xsd = "http://www.w3.org/2001/XMLSchema";
        soapEnvelope.xsi = "http://www.w3.org/2001/XMLSchema-instance";
        
        int result = -1;
        try{
        	aht.call(SOAP_ACTION, soapEnvelope);
        	result = Integer.parseInt(soapEnvelope.getResponse().toString());
        	Log.e("OK", "OKKK : "+result);
        } catch(Exception e){
        	Log.e("TEST", e.toString());
        }
        
        return result;
        
	}
	
	private String getCoordinatesString(double latitude, double longitude){
		return latitude+":"+longitude;
	}
	
}
