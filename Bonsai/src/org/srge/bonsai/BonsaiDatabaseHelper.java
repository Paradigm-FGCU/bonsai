package org.srge.bonsai;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
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
		db.execSQL("CREATE TABLE DECKS ("
				+ "DECK_ID INTEGER PRIMARY KEY AUTOINCREMENT, DECK_NAME TEXT, DECK_STATS INTEGER)");

		db.execSQL("CREATE TABLE CARDS ("
				+ "CARD_ID INTEGER PRIMARY KEY AUTOINCREMENT, TERM TEXT, DEFN TEXT, SEEN INTEGER,"
				+ "CORRECT INTEGER, ALT_DEFN1 TEXT, ALT_DEFN2 TEXT, ALT_DEFN3 TEXT,"
				+ "DECK_ID INTEGER REFERENCES BONSAI(DECK_ID))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public long insertDeck(String deck) {
		ContentValues cv = new ContentValues();
		cv.put("DECK_NAME", deck);
		return getWritableDatabase().insert("DECKS", null, cv);
	}

	
	public ArrayList<String> queryDecks() {
		ArrayList<String> allDecks = new ArrayList<String>();
		
		Cursor crs = getReadableDatabase().query("DECKS", null, null,
				null, null, null, "deckId" + " asc");

		while(crs.moveToNext()){
		    String uname = crs.getString(crs.getColumnIndex("DECK_NAME"));
		    allDecks.add(uname);
		}
		
		return allDecks;
	}
	

	public CardCursor queryCard(long id) {
		Cursor wrapped = getReadableDatabase().query("CARDS", null, // all
																		// columns
				"DECK_ID" + " = ?", // look for a run ID
				new String[] { String.valueOf(id) }, // with this value
				null, // group by
				null, // order by
				null, // having
				"1"); // limit 1 row
		return new CardCursor(wrapped);
	}

	public long insertCard(int deckId, String term, String defn, int seen,
			int correct, String alt1, String alt2, String alt3) {

		ContentValues cv = new ContentValues();
		cv.put("DECK_ID", deckId);
		cv.put("TERM", term);
		cv.put("DEFN", defn);
		cv.put("SEEN", 0);
		cv.put("CORRECT", 0);
		cv.put("ALT_DEFN1", alt1);
		cv.put("ALT_DEFN2", alt2);
		cv.put("ALT_DEFN3", alt3);

		return getWritableDatabase().insert("CARDS", null, cv);

	}
	
    public static class CardCursor extends CursorWrapper {
        
        public CardCursor(Cursor c) {
            super(c);
        }
        
 
        public CardContent getCard() {

            CardContent card = new CardContent();
            //card.setId(getLong(getColumnIndex(COLUMN_RUN_ID)));
            //card.setStartDate(new Date(getLong(getColumnIndex(COLUMN_RUN_START_DATE))));
            return card;
        }
    }
	
}
