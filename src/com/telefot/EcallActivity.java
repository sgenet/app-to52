package com.telefot;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EcallActivity extends Activity{
	
	private Button mButtonCall;
	private Controller controller = new Controller();
	private OnSharedPreferenceChangeListener listenerPrefs;
	private String phoneNumber;
	private CallListener callListener = new CallListener();
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView( R.layout.ecall );
        
        this.controller.setActivityContext(getApplicationContext());

	    // PREFERENCES :
	    
	    SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
	    
	    updatePreferences(sharedPrefs);
	    
	    listenerPrefs = new SharedPreferences.OnSharedPreferenceChangeListener() {
	    	  public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
	    		  updatePreferences(prefs);
	    	  }
	    };
	    
	    sharedPrefs.registerOnSharedPreferenceChangeListener(listenerPrefs);
	    
	    // BUTTONS :
        
        mButtonCall = ( Button )findViewById( R.id.buttonCall );
 
        mButtonCall.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick( View view ) {
				askForEcall();
            }
        });
    }
    
    public void processEcall(){
    	
    	// Thread 1 : call operator
    	Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
		    	startActivity(intent);
		    	
//				TelephonyManager mTM = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//				mTM.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);
			}
		});	
		
    	// Thread2 : send alert to web service
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				controller.sendALert("al_eu_acc");
				
//				Log.e("CALL", "Network : "+callListener.getTypeNetwork());
//				Log.e("CALL", "Time Ringing : "+callListener.getTimeRinging());
			}
		});
		
		thread1.setPriority(1);
		thread2.setPriority(10);

		thread1.start();
		thread2.start();
    }   
    
    public void askForEcall(){
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage(R.string.ecall_confirm_msg)
    	       .setCancelable(false)
    	       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	                EcallActivity.this.processEcall();
    	           }
    	       })
    	       .setNegativeButton("No", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	                dialog.cancel();
    	           }
    	       });
    	AlertDialog alert = builder.create();
    	alert.show();
    }

    public void updatePreferences(SharedPreferences prefs)
    {
    	phoneNumber = prefs.getString("phone_number_ecall", "+33684684394");
		((TextView) findViewById(R.id.settings_text_view)).setText("Numero a appeler : " + phoneNumber);
    }
    
}