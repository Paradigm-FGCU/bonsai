package org.srge.bonsai;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BonsaiDatabaseHelper extends SQLiteOpenHelper {
	private static final String DB = "bonsai.sqlite";
	private static final int VERSION = 1;
	
	public BonsaiDatabaseHelper(Context context) {
		super(context, DB, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE BONSAI (" + 
				"DECK_ID INTEGER PRIMARY KEY AUTOINCREMENT, DECK_NAME TEXT)");
		
		db.execSQL("CREATE TABLE CARDS (" + 
				"CARD_ID INTEGER PRIMARY KEY AUTOINCREMENT, TERM TEXT, DEFN TEXT, SEEN INTEGER," + 
				"CORRECT INTEGER, ALT_DEFN1 TEXT, ALT_DEFN2 TEXT, ALT_DEFN3 TEXT," +
				"DECK_ID INTEGER REFERENCES BONSAI(DECK_ID))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	public long insertDeck(String deck) {
		ContentValues cv = new ContentValues();
		cv.put("DECK_NAME", deck);
		return getWritableDatabase().insert("BONSAI", null, cv);	
	}
	
	public long insertCard(String term, String defn, int seen, int correct,
							String alt1, String alt2, String alt3) {
		
		
		
		
		return correct;
		
	}
	
	

}
