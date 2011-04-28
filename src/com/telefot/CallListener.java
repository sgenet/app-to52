package com.telefot;

import java.util.Calendar;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class CallListener extends PhoneStateListener {
	
	private long timeRinging;
	private String typeNetwork;
	
    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        if(TelephonyManager.CALL_STATE_RINGING == state) {
            setTimeRinging(Calendar.getInstance().getTimeInMillis());
        }
        
        Log.e("STATE", Integer.toString(state));
    }
    
    public void onDataConnectionStateChanged(int state, int networkType)
    {
    	if(TelephonyManager.NETWORK_TYPE_UNKNOWN == networkType)
    		setTypeNetwork("unknown");
    	else if(TelephonyManager.NETWORK_TYPE_GPRS == networkType)
    		setTypeNetwork("gprs");
		else if(TelephonyManager.NETWORK_TYPE_EDGE == networkType)
			setTypeNetwork("edge");
		else if(TelephonyManager.NETWORK_TYPE_UMTS == networkType)
			setTypeNetwork("umts");
		else if(TelephonyManager.NETWORK_TYPE_CDMA == networkType)
			setTypeNetwork("cdma");
		else if(TelephonyManager.NETWORK_TYPE_EVDO_0 == networkType)
			setTypeNetwork("evdo 0");
		else if(TelephonyManager.NETWORK_TYPE_EVDO_A == networkType)
			setTypeNetwork("evdo a");
		else if(TelephonyManager.NETWORK_TYPE_1xRTT == networkType)
			setTypeNetwork("rtt");
		else
    		setTypeNetwork("unknown");
    	
        Log.e("NETWORK", Integer.toString(networkType));
    }

	public void setTimeRinging(long timeRinging) {
		this.timeRinging = timeRinging;
	}

	public long getTimeRinging() {
		return timeRinging;
	}

	public void setTypeNetwork(String typeNetwork) {
		this.typeNetwork = typeNetwork;
	}

	public String getTypeNetwork() {
		return typeNetwork;
	}
}