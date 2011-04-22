package com.telefot;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
 
public class DataBaseSQLite extends SQLiteOpenHelper {
 
	private static final String CREATE_BDD = "CREATE TABLE messages (" 
											+ "id INTEGER PRIMARY KEY AUTOINCREMENT ,"
											+ "reference VARCHAR( 30 ) NOT NULL ,"
											+ "location TEXT NULL ,"
											+ "sent_date INTEGER NOT NULL ,"
											+ "processing_time INTEGER NULL"
											+ ");";
 
	public DataBaseSQLite(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_BDD);
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE messages;");
		onCreate(db);
	}
 
}