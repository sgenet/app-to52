package com.telefot;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;

public class HomeActivity extends TabActivity 
{	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    
	    // TABS:
	    
	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, EcallActivity.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("ecall").setIndicator("eCall", res.getDrawable(R.drawable.ic_tab_ecall))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs

	    intent = new Intent().setClass(this, ListAlertsActivity.class);
	    intent.putExtra("type_list", "alerts");
	    spec = tabHost.newTabSpec("alerts").setIndicator("Alertes", res.getDrawable(R.drawable.ic_tab_alerts))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, ListAlertsActivity.class);
	    intent.putExtra("type_list", "incidents");
	    spec = tabHost.newTabSpec("incidents").setIndicator("Incidents", res.getDrawable(R.drawable.ic_tab_incident))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(0);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
	    super.onCreateOptionsMenu(menu);

	    menu.add(0, 1, 0, R.string.menu_text1).setIntent(new Intent(this, SettingsActivity.class));
	    menu.add(0, 2, 1, R.string.menu_text2).setIntent(new Intent(this, HistoryActivity.class));

	    return true;
	}

}

