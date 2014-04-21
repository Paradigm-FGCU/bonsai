package org.srge.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.bonsai.activities.EditDeckActivity;
import org.bonsai.activities.FlashActivity;

public class RunningInfo {
	//Static class used to find running info during the program
	
	private static DeckInfo selectedDeck;
	private static ArrayList<CardInfo> workingCardList; 
	private static boolean timedQuiz;
	private static int quizTime = 10;
	private static boolean flashCardRepeat = false;
	//if card Order ==0, inorder
	//... ==1, hardest first
	//... ==2, random
	private static int cardOrder;
	
	
	@SuppressWarnings("unchecked")
	public static boolean updateCardOrder(){
		if(workingCardList==null) return false;
		switch(cardOrder){
			case 0: workingCardList = (ArrayList<CardInfo>)selectedDeck.getCardList().clone();
					break;
			case 1: workingCardList = (ArrayList<CardInfo>)selectedDeck.getCardList().clone();
					workingCardList = CardSort.sortHardestFirst(workingCardList);
					break;
			case 2: workingCardList = (ArrayList<CardInfo>)selectedDeck.getCardList().clone();
					Collections.shuffle(workingCardList, new Random(System.currentTimeMillis()));
					break;
		}
		return true;
	}
	
	
	//setters and getters
	public static DeckInfo getSelectedDeck() {
		return selectedDeck;
	}
	
	public static int getCardOrder() {
		return cardOrder;
	}
	
	public static void setCardOrder(int in){
		cardOrder = in;
	}

	public static boolean questionAnswered(boolean correct, int id){
		CardInfo temp = getCardById(id);
		if(temp == null) return false;
		if(correct){
			temp.answeredCorrect();
		}
		else{
			temp.answeredIncorrect();
		}
		return true;
	}
	
	public static CardInfo getCardById(int id){
		for(int i=0;i<selectedDeck.getCardList().size();i++){
			if(selectedDeck.getCardList().get(i).getId()==id) return selectedDeck.getCardList().get(i);
		}
		return null;
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
	
	//do not touch
	public static FlashActivity.SectionsPagerAdapter parent;
	public static EditDeckActivity.SectionsPagerAdapter parent2;
}
