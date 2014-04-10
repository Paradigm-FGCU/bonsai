package org.srge.card;

import java.util.ArrayList;

public class TestingDriver {
	
	private static ArrayList<DeckInfo> deckList;
	public static ArrayList<String> deckNames;
	private static String[] defs = {
			"An atom or group of atoms arranged in a particular way that is primarily responsible for the chemical and physical properties of the molecule in which it is found. There are a total of 10 of these.",
			"Unsaturated hydrocarbons containing at least one carbon-carbon triple bond. Noted by the suffix \"-yne\"",
			"The reaction of alkanes, alkenes, or alcohols with excess oxygen yields carbon dioxide, water, and heat.",
			"Flourine (F), Chlorine (Cl), Bromine (Br), and Iodine (I).",
			"An element that has the capacity to share four electrons in order to achieve a more stable configuration.",
			"Atomic Number = 6, Protons = 6, Electrons = 6, Atomic Weight = 12.0. Electrons in first energy level = 2; second energy level = 4.",
			"Contain carbon-to-carbon double or triple bonds.",
			"Contain only only carbon-to-carbon single bonds. The most chemically inert of all organic compounds.",
			"Inter-atomic relationship created by the sharing of at least one pair of electrons.",
			"The branch of chemistry which deals with carbon compounds, including those with no relationship to life." };

	private static String[] terms = { "Functional Group", "Alkyne",
			"Hydrocarbon Combustion", "Halogens", "Carbon",
			"Atomic Structure of Carbon", "Unsaturated Hydrocarbon",
			"Saturated Hydrocarbond", "Covalent Bond", "Organic Chemistry" };

	
	
	public TestingDriver(){
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
		tempDeck1.setDeckName("Chemistry");
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
		ArrayList<CardInfo> templist = new ArrayList();
		
		for(int i=0;i<defs.length;i++){
			CardInfo temp = new CardInfo(terms[i],defs[i],tempDeck);
			templist.add(temp);
		}

		tempDeck.setCardList(templist);
		
	}
	
	private static void initializeCards2(DeckInfo tempDeck) {
		CardInfo temp1 = new CardInfo("Deck2: This is Term 1","Definition 1", tempDeck);
		CardInfo temp2 = new CardInfo("Deck2: This is Term 2","Definition 2", tempDeck);
		CardInfo temp3 = new CardInfo("Deck2: This is Term 3","Definition 3", tempDeck);
		CardInfo temp4 = new CardInfo("Deck2: This is Term 4","Definition 4", tempDeck);
		CardInfo temp5 = new CardInfo("Deck2: This is Term 5","Definition 5", tempDeck);
		CardInfo temp6 = new CardInfo("Deck2: This is Term 6","Definition 6", tempDeck);
		
		ArrayList<CardInfo> templist = new ArrayList();
		templist.add(temp1);
		templist.add(temp2);
		templist.add(temp3);
		templist.add(temp4);
		templist.add(temp5);
		templist.add(temp6);
		tempDeck.setCardList(templist);
		
	}
	
	private static void initializeCards3(DeckInfo tempDeck) {
		CardInfo temp1 = new CardInfo("Deck3: This is Term 1","Definition 1", tempDeck);
		CardInfo temp2 = new CardInfo("Deck3: This is Term 2","Definition 2", tempDeck);
		CardInfo temp3 = new CardInfo("Deck3: This is Term 3","Definition 3", tempDeck);
		CardInfo temp4 = new CardInfo("Deck3: This is Term 4","Definition 4", tempDeck);
		CardInfo temp5 = new CardInfo("Deck3: This is Term 5","Definition 5", tempDeck);
		
		ArrayList<CardInfo> templist = new ArrayList();
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
