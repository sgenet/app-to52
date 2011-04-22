package com.telefot;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TelefotAlertDB {
	 
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "telefot2.db";

	private static final String TABLE_MESSAGES = "messages";
	private static final String COL_ID = "id";
	private static final int NUM_COL_ID = 0;
	private static final String COL_REFERENCE = "reference";
	private static final int NUM_COL_REFERENCE = 1;
	private static final String COL_LOCATION = "location";
	private static final int NUM_COL_LOCATION = 2;
	private static final String COL_DATE = "sent_date";
	private static final int NUM_COL_DATE = 3;
	private static final String COL_TIME = "processing_time";
	private static final int NUM_COL_TIME = 4;
 
	private SQLiteDatabase db;
 
	private DataBaseSQLite dbTelefot;
 
	public TelefotAlertDB(Context context){
		dbTelefot = new DataBaseSQLite(context, DB_NAME, null, DB_VERSION);
	}
 
	public void open(){
		db = dbTelefot.getWritableDatabase();
	}
 
	public void close(){
		db.close();
	}
 
	public SQLiteDatabase getDb(){
		return db;
	}
 
	public long insertMessage(TelefotAlert message){
		
		ContentValues values = new ContentValues();
		
		values.put(COL_REFERENCE, message.getReference());
		values.put(COL_LOCATION, message.getAddress());
		values.put(COL_DATE, message.getSentDate());
		values.put(COL_TIME, message.getProcessingTime());
		
		return db.insert(TABLE_MESSAGES, null, values);
	}
 
	public ArrayList<TelefotAlert> getMessages(){
		
		Cursor c = db.query(TABLE_MESSAGES, null, null, null, null, null, COL_ID + " desc");
		
		return cursorToArray(c);
	}
	
	public ArrayList<TelefotAlert> cursorToArray(Cursor c)
	{
		ArrayList<TelefotAlert> results = new ArrayList<TelefotAlert>();
		
		if(c.getCount() != 0)
		{
			c.moveToFirst();
			
			do 
			{ 
				TelefotAlert message = new TelefotAlert();
				
				message.setReference(c.getString(NUM_COL_REFERENCE));
				message.setAddress(c.getString(NUM_COL_LOCATION));
				message.setSentDate(c.getLong(NUM_COL_DATE));
				message.setProcessingTime(c.getInt(NUM_COL_TIME));
				
                results.add(message); 
			} 
			while (c.moveToNext());
		}
		
		c.close();
		
		return results;
	}
}
