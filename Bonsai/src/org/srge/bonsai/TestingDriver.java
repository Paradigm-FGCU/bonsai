package org.srge.bonsai;

import java.util.ArrayList;

public class TestingDriver {
	
	private static ArrayList<DeckInfo> deckList;
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
		deckList = new ArrayList<DeckInfo>();
		deckNames = new ArrayList<String>();
		
		DeckInfo tempDeck1 = new DeckInfo();
		tempDeck1.setDeckName("My Test Deck 1");
		initializeCards1(tempDeck1);
		
		deckList.add(tempDeck1);
		
		DeckInfo tempDeck2 = new DeckInfo();
		tempDeck2.setDeckName("My Test Deck 2");
		initializeCards2(tempDeck2);
		
		deckList.add(tempDeck2);
		
		DeckInfo tempDeck3 = new DeckInfo();
		tempDeck3.setDeckName("My Test Deck 3");
		initializeCards3(tempDeck3);
		
		deckList.add(tempDeck3);
		
		
	}
	
	private static void initializeCards1(DeckInfo tempDeck) {
		CardInfo temp1 = new CardInfo("Deck1: This is Question 1","Answer1", tempDeck);
		CardInfo temp2 = new CardInfo("Deck1: This is Question 2","Answer2", tempDeck);
		CardInfo temp3 = new CardInfo("Deck1: This is Question 3","Answer3", tempDeck);
		CardInfo temp4 = new CardInfo("Deck1: This is Question 4","Answer4", tempDeck);
		
		ArrayList<CardInfo> templist = new ArrayList<CardInfo>();
		templist.add(temp1);
		templist.add(temp2);
		templist.add(temp3);
		templist.add(temp4);
		tempDeck.setCardList(templist);
		
	}
	
	private static void initializeCards2(DeckInfo tempDeck) {
		CardInfo temp1 = new CardInfo("Deck2: This is Question 1","Answer1", tempDeck);
		CardInfo temp2 = new CardInfo("Deck2: This is Question 2","Answer2", tempDeck);
		CardInfo temp3 = new CardInfo("Deck2: This is Question 3","Answer3", tempDeck);
		CardInfo temp4 = new CardInfo("Deck2: This is Question 4","Answer4", tempDeck);
		CardInfo temp5 = new CardInfo("Deck2: This is Question 5","Answer5", tempDeck);
		CardInfo temp6 = new CardInfo("Deck2: This is Question 6","Answer6", tempDeck);
		
		ArrayList<CardInfo> templist = new ArrayList<CardInfo>();
		templist.add(temp1);
		templist.add(temp2);
		templist.add(temp3);
		templist.add(temp4);
		templist.add(temp5);
		templist.add(temp6);
		tempDeck.setCardList(templist);
		
	}
	
	private static void initializeCards3(DeckInfo tempDeck) {
		CardInfo temp1 = new CardInfo("Deck3: This is Question 1","Answer1", tempDeck);
		CardInfo temp2 = new CardInfo("Deck3: This is Question 2","Answer2", tempDeck);
		CardInfo temp3 = new CardInfo("Deck3: This is Question 3","Answer3", tempDeck);
		CardInfo temp4 = new CardInfo("Deck3: This is Question 4","Answer4", tempDeck);
		CardInfo temp5 = new CardInfo("Deck3: This is Question 5","Answer5", tempDeck);
		
		ArrayList<CardInfo> templist = new ArrayList<CardInfo>();
		templist.add(temp1);
		templist.add(temp2);
		templist.add(temp3);
		templist.add(temp4);
		templist.add(temp5);
		tempDeck.setCardList(templist);
		
	}
	
	public static ArrayList<DeckInfo> getDeckList(){
		return deckList;
	}
}
