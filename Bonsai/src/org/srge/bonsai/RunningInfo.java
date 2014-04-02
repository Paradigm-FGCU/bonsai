package org.srge.bonsai;

import java.util.ArrayList;

public class RunningInfo {
	//Static class used to find running info during the program
	
	private static CardDeck selectedDeck;
	private static ArrayList<CardContent> workingCardList;
	private static boolean timedQuiz;
	private static int quizTime = 10;
	private static boolean flashCardRepeat = false;
	
	//setters and getters
	public static CardDeck getSelectedDeck() {
		return selectedDeck;
	}
	public static ArrayList<CardContent> getWorkingCardList() {
		return workingCardList;
	}
	public static void addCardByIndex(int index){
		workingCardList.add(workingCardList.get(index));
	}
	public static void setSelectedDeck(CardDeck selectedDeck) {
		RunningInfo.selectedDeck = selectedDeck;
		RunningInfo.workingCardList = new ArrayList<CardContent>();
		workingCardList = (ArrayList<CardContent>)selectedDeck.getCardList().clone();
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
