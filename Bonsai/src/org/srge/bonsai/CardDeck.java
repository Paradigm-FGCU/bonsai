package org.srge.bonsai;

import java.util.ArrayList;

public class CardDeck {
	private String mdeckName;
	private ArrayList<CardContent> cardList;
	private double mQuizAverage;
	private int mQuizCount;
	
	CardDeck(){
		
	}
	
	CardDeck(String deckNameIn, ArrayList<CardContent> cardListIn){
		mdeckName = deckNameIn;
		cardList = cardListIn;
	}
	
	/*
	 * Instance Methods
	 */
	
	//Takes in a new quiz grade and weighted averages it with the old grade
	public void reaverageQuiz(double quizAverageIn){
		if(mQuizCount!=0){
			mQuizAverage = (mQuizAverage*mQuizCount)+quizAverageIn;
			mQuizCount++;
		}
		else{
			mQuizAverage = quizAverageIn;
			mQuizCount++;
		}
	}
	
	/*
	 * Setters and Getters
	 */
	public ArrayList<CardContent> getCardList(){
		return cardList;
	}
	
	public void setCardList(ArrayList<CardContent> cardListIn){
		cardList = cardListIn;
	}
	
	public double getQuizAverage() {
		return mQuizAverage;
	}

	public void setQuizAverage(double mQuizAverage) {
		this.mQuizAverage = mQuizAverage;
	}

	public int getQuizCount() {
		return mQuizCount;
	}

	public void setQuizCount(int mQuizCount) {
		this.mQuizCount = mQuizCount;
	}

	public String getDeckName() {
		return mdeckName;
	}

	public void setDeckName(String mdeckName) {
		this.mdeckName = mdeckName;
	}
	
	
}
