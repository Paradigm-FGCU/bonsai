package org.srge.bonsai;

public class RunningInfo {
	//Static class used to find running info during the program
	
	private static CardDeck selectedDeck;
	private static boolean timedQuiz;
	
	
	//setters and getters
	public static CardDeck getSelectedDeck() {
		return selectedDeck;
	}
	public static void setSelectedDeck(CardDeck selectedDeck) {
		RunningInfo.selectedDeck = selectedDeck;
	}
	public static boolean isTimedQuiz() {
		return timedQuiz;
	}
	public static void setTimedQuiz(boolean timedQuiz) {
		RunningInfo.timedQuiz = timedQuiz;
	}
	
}
