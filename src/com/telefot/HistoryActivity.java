package com.telefot;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;

public class HistoryActivity extends ListActivity{
	
    private ArrayList<TelefotAlert> m_messages = null;
    private HistoryListAdapter m_adapter;
    private TelefotAlertDB db;
    
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);

        setContentView( R.layout.history );
        
        db = new TelefotAlertDB(this);
        m_messages = new ArrayList<TelefotAlert>();

        db.open();
        m_messages = db.getMessages();
        db.close();
        
        m_adapter = new HistoryListAdapter(this, R.layout.history_row, m_messages);
        setListAdapter(this.m_adapter);
    }

}
