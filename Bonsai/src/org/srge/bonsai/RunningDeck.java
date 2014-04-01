package org.srge.bonsai;

import java.util.ArrayList;

public class RunningDeck {
	private static CardDeck selectedDeck;
	
	RunningDeck(){
		
	}

	public static CardDeck getSelectedDeck() {
		return selectedDeck;
	}

	public static void setSelectedDeck(CardDeck selectedDeck) {
		RunningDeck.selectedDeck = selectedDeck;
	}
	
	
}
