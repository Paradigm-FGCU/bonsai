package org.srge.bonsai;

import java.util.ArrayList;

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
		initializeCards1(tempDeck1);
		
		deckList.add(tempDeck1);
		
		CardDeck tempDeck2 = new CardDeck();
		tempDeck2.setDeckName("My Test Deck 2");
		initializeCards2(tempDeck2);
		
		deckList.add(tempDeck2);
		
		CardDeck tempDeck3 = new CardDeck();
		tempDeck3.setDeckName("My Test Deck 3");
		initializeCards3(tempDeck3);
		
		deckList.add(tempDeck3);
		
		
	}
	
	private static void initializeCards1(CardDeck tempDeck) {
		CardContent temp1 = new CardContent("Deck1: This is Question 1","Answer1", tempDeck);
		CardContent temp2 = new CardContent("Deck1: This is Question 2","Answer2", tempDeck);
		CardContent temp3 = new CardContent("Deck1: This is Question 3","Answer3", tempDeck);
		CardContent temp4 = new CardContent("Deck1: This is Question 4","Answer4", tempDeck);
		
		ArrayList<CardContent> templist = new ArrayList<CardContent>();
		templist.add(temp1);
		templist.add(temp2);
		templist.add(temp3);
		templist.add(temp4);
		tempDeck.setCardList(templist);
		
	}
	
	private static void initializeCards2(CardDeck tempDeck) {
		CardContent temp1 = new CardContent("Deck2: This is Question 1","Answer1", tempDeck);
		CardContent temp2 = new CardContent("Deck2: This is Question 2","Answer2", tempDeck);
		CardContent temp3 = new CardContent("Deck2: This is Question 3","Answer3", tempDeck);
		CardContent temp4 = new CardContent("Deck2: This is Question 4","Answer4", tempDeck);
		CardContent temp5 = new CardContent("Deck2: This is Question 5","Answer5", tempDeck);
		CardContent temp6 = new CardContent("Deck2: This is Question 6","Answer6", tempDeck);
		
		ArrayList<CardContent> templist = new ArrayList<CardContent>();
		templist.add(temp1);
		templist.add(temp2);
		templist.add(temp3);
		templist.add(temp4);
		templist.add(temp5);
		templist.add(temp6);
		tempDeck.setCardList(templist);
		
	}
	
	private static void initializeCards3(CardDeck tempDeck) {
		CardContent temp1 = new CardContent("Deck3: This is Question 1","Answer1", tempDeck);
		CardContent temp2 = new CardContent("Deck3: This is Question 2","Answer2", tempDeck);
		CardContent temp3 = new CardContent("Deck3: This is Question 3","Answer3", tempDeck);
		CardContent temp4 = new CardContent("Deck3: This is Question 4","Answer4", tempDeck);
		CardContent temp5 = new CardContent("Deck3: This is Question 5","Answer5", tempDeck);
		
		ArrayList<CardContent> templist = new ArrayList<CardContent>();
		templist.add(temp1);
		templist.add(temp2);
		templist.add(temp3);
		templist.add(temp4);
		templist.add(temp5);
		tempDeck.setCardList(templist);
		
	}
	
	public static ArrayList<CardDeck> getDeckList(){
		return deckList;
	}
}
