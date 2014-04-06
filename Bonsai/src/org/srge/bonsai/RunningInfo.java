package org.srge.bonsai;

import java.util.ArrayList;

public class RunningInfo {
	//Static class used to find running info during the program
	
	private static DeckInfo selectedDeck;
	private static ArrayList<CardInfo> workingCardList;
	private static boolean timedQuiz;
	private static int quizTime = 10;
	private static boolean flashCardRepeat = false;
	
	//setters and getters
	public static DeckInfo getSelectedDeck() {
		return selectedDeck;
	}
	public static ArrayList<CardInfo> getWorkingCardList() {
		return workingCardList;
	}
	public static void addCardByIndex(int index){
		workingCardList.add(workingCardList.get(index));
	}
	public static void setSelectedDeck(DeckInfo selectedDeck) {
		RunningInfo.selectedDeck = selectedDeck;
		RunningInfo.workingCardList = new ArrayList<CardInfo>();
		workingCardList = (ArrayList<CardInfo>)selectedDeck.getCardList().clone();
	}
	public static boolean getTimedQuiz() {
		return timedQuiz;
	}
	public static boolean getFlashCardRepeat(){
		return flashCardRepeat;
	}
	public static void setFlashCardRepeat(boolean in){
		flashCardRepeat = in;
	}
	public static void setTimedQuiz(boolean timedQuiz) {
		RunningInfo.timedQuiz = timedQuiz;
	}
	public static int getQuizTime() {
		return quizTime;
	}
	public static void setQuizTime(int quizTime) {
		RunningInfo.quizTime = quizTime;
	}
	
	
}
