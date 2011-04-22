package com.telefot;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListAlertsActivity extends ListActivity {
	
	private ArrayAdapter<String> listAdapter;
	private String type_list;
	private String[] labels;
	private String[] refs;
	
	private Controller controller = new Controller();
	ArrayList<String> references = new ArrayList<String>();
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        type_list = getIntent().getExtras().getString("type_list");

        setContentView( R.layout.list_view );
        
        this.controller.setActivityContext(getApplicationContext());
        
        // If list for alerts :
        if(type_list.contentEquals("alerts"))
        {
        	labels = getResources().getStringArray(R.array.alerts_names);
        	refs = getResources().getStringArray(R.array.alerts_refs);
        }
        // If list for incidents : 
        else if(type_list.contentEquals("incidents"))
        {
        	labels = getResources().getStringArray(R.array.incidents_names);
        	refs = getResources().getStringArray(R.array.incidents_refs);
        }
        
        for(String ref : refs) {
        	references.add(ref);
        }
        
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, labels);
        
        setListAdapter(listAdapter);
    }
    
    protected void onListItemClick(ListView l, View v, final int position, long id) 
    {
        super.onListItemClick(l, v, position, id);          
        
        final String ref = references.get(position);
    	
    	AlertDialog.Builder builder_alert = new AlertDialog.Builder(this);
    	builder_alert.setMessage(R.string.alert_msg)
    	       .setCancelable(false)
    	       .setPositiveButton("Yes", new DialogInterface.OnClickListener() 
    	       {
    	           public void onClick(DialogInterface dialog, int id) 
    	           {
    	        	   new Thread(new Runnable() {
    	       			
    	       			@Override
    	       			public void run() {
        	                controller.sendALert(ref);
    	       			}
    	       		}).start();
    	           }
    	       })
    	       .setNegativeButton("No", new DialogInterface.OnClickListener() 
    	       {
    	           public void onClick(DialogInterface dialog, int id) 
    	           {
    	                dialog.cancel();
    	           }
    	       });
    	AlertDialog alert = builder_alert.create();
    	alert.show();
    }
}