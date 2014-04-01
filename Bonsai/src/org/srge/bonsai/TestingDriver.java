package org.srge.bonsai;

import java.util.ArrayList;
import android.util.Log;

public class TestingDriver {
	
	private static ArrayList<CardDeck> deckList;
	public static ArrayList<String> deckNames;
	
	TestingDriver(){
		initializeCardDeck();
		initializeDeckNames();
		
	}
	
	public static void initializeDeckNames(){
		for(int i =0;i<deckList.size();i++){
			deckNames.add(deckList.get(i).getDeckName());
		}
	}
	
	//initiallizes 3 decks for the deck list
	public static void initializeCardDeck() {
		deckList = new ArrayList<CardDeck>();
		deckNames = new ArrayList<String>();
		
		CardDeck tempDeck1 = new CardDeck();
		tempDeck1.setDeckName("My Test Deck 1");
		initializeCards(tempDeck1);
		
		deckList.add(tempDeck1);
		Log.w("in driver", deckList.get(0).getDeckName());
		
		CardDeck tempDeck2 = new CardDeck();
		tempDeck2.setDeckName("My Test Deck 2");
		initializeCards(tempDeck2);
		
		deckList.add(tempDeck2);

		Log.w("in driver", deckList.get(1).getDeckName() + " 2");
		
		CardDeck tempDeck3 = new CardDeck();
		tempDeck3.setDeckName("My Test Deck 3");
		initializeCards(tempDeck3);
		
		deckList.add(tempDeck3);

		Log.w("in driver", deckList.get(2).getDeckName() + " 3" );
		
		
	}
	
	private static void initializeCards(CardDeck tempDeck) {
		CardContent temp1 = new CardContent("This is Question 1","Answer1", tempDeck);
		CardContent temp2 = new CardContent("This is Question 2","Answer2", tempDeck);
		CardContent temp3 = new CardContent("This is Question 3","Answer3", tempDeck);
		CardContent temp4 = new CardContent("This is Question 4","Answer4", tempDeck);
		
		ArrayList<CardContent> templist = new ArrayList();
		templist.add(temp1);
		templist.add(temp2);
		templist.add(temp3);
		templist.add(temp4);
		tempDeck.setCardList(templist);
		
	}
	
	public static ArrayList<CardDeck> getDeckList(){
		return deckList;
	}
}
