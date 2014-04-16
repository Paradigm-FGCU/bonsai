package org.srge.card;

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

	public ArrayList<String> getDecksList() {
		ArrayList<String> allDecks = new ArrayList<String>();

		Cursor crs = getReadableDatabase().query("DECKS", null, null, null,
				null, null, "DECK_ID" + " asc");

		while (crs.moveToNext()) {
			String uname = crs.getString(crs.getColumnIndex("DECK_NAME"));
			allDecks.add(uname);
		}

		if (crs.getCount() <= 0)
			return null;

		return allDecks;

	}

	public String getDeckName(int pos) {

		Cursor crs = getReadableDatabase().query("DECKS", null, null, null,
				null, null, "DECK_ID" + "= " + pos);

		crs.moveToNext();

		String deckName = crs.getString(crs.getColumnIndex("DECK_NAME"));

		return deckName;

	}

	public ArrayList<CardInfo> getAllCardsFromDeck(int id) {
		ArrayList<CardInfo> allCardsFromDeck = new ArrayList<CardInfo>();

		Cursor wrapped = getReadableDatabase().query("CARDS", null, // all
																	// columns
				"DECK_ID" + " = ?", // look for a ID
				new String[] { String.valueOf(id) }, // with this value
				null, // group by
				null, // order by
				null, // having
				null); // limit 1 row

		if (wrapped.moveToFirst()) {
			do {
				int ID = Integer.parseInt(wrapped.getString(wrapped.getColumnIndex("CARD_ID")));
				String TERM = wrapped.getString(wrapped.getColumnIndex("TERM"));
				String DEFN = wrapped.getString(wrapped.getColumnIndex("DEFN"));

				allCardsFromDeck.add(new CardInfo(ID, TERM, DEFN, new DeckInfo()));
			} while (wrapped.moveToNext());
		}

		return allCardsFromDeck;
	}

	public DeckInfo getCardDeckFormat(String name,
			ArrayList<CardInfo> cardarraylist) {
		DeckInfo cardDeckFormat = new DeckInfo(name, cardarraylist);

		// add stats later;

		return cardDeckFormat;
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

	public ArrayList<String> getFakeAns(int cardId) {
		ArrayList<String> fake = new ArrayList<String>();
		
		Cursor crs = getReadableDatabase().query("CARDS", null, null, null,
				null, null, "CARD_ID" + "= " + cardId);

		crs.moveToNext();

		fake.add(crs.getString(crs.getColumnIndex("ALT_DEFN1")));
		fake.add(crs.getString(crs.getColumnIndex("ALT_DEFN2")));
		fake.add(crs.getString(crs.getColumnIndex("ALT_DEFN3")));


		return fake;

	}
	
	public boolean deleteCard(String rowId) {
	    return getWritableDatabase().delete("CARDS", "CARD_ID" + "=" + rowId, null) > 0;
	}
	
	
/*	public void editCard(String rowId) {
		getWritableDatabase().update(table, values, whereClause, whereArgs);
	}*/
	
	
	public static class CardCursor extends CursorWrapper {

		public CardCursor(Cursor c) {
			super(c);
		}

		public CardInfo getCard() {
			CardInfo card = new CardInfo();
			card.setId((int) getInt(getColumnIndex("CARD_ID")));
			card.setQuestion(getString(getColumnIndex("TERM")));
			return card;
		}
	}

}
