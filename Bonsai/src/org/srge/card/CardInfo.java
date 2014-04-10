package org.srge.card;

import java.util.ArrayList;

public class CardInfo {
	private String mDefinition;
	private String mTerm;
	private ArrayList<String> mFakeAnswers;
	private DeckInfo parentDeck;
	private int mNumberSeen;
	private int mNumberCorrect;
	private int mId;
	
	public CardInfo(String question, String answer, DeckInfo parentDeckIn) {
		mDefinition = question;
		mTerm = answer;
		parentDeck = parentDeckIn; 
	}
	
	public CardInfo(String definition, String term,ArrayList<String> fakeAnswers,DeckInfo parentDeckIn) {
		mDefinition = definition;
		mTerm = term;
		parentDeck = parentDeckIn; 
		mFakeAnswers = fakeAnswers;
	}
	
	public void setId(int id) {
		mId = id;
	}

	public String getQuestion() {
		return mDefinition;
	}

	public void setQuestion(String question) {
		mDefinition = question;
	}

	public String getAnswer() {
		return mTerm;
	}

	public void setAnswer(String answer) {
		mTerm = answer;
	}
	
	public ArrayList<String> getFakeAnswers() {
		return mFakeAnswers;
	}

	public void setFakeAnswers(ArrayList<String> mFakeAnswers) {
		this.mFakeAnswers = mFakeAnswers;
	}
	
	public int getNumberSeen() {
		return mNumberSeen;
	}

	public void setNumberSeen(int mNumberSeen) {
		this.mNumberSeen = mNumberSeen;
	}

	public int getNumberCorrect() {
		return mNumberCorrect;
	}

	public void setNumberCorrect(int mNumberCorrect) {
		this.mNumberCorrect = mNumberCorrect;
	}
	
	public void answeredCorrect() {
		this.mNumberCorrect++;
		this.mNumberSeen++;
	}
	
	public void answeredIncorrect() {
		this.mNumberSeen++;
	}
	
	public DeckInfo getParentDeck(){
		return parentDeck;
	}
}

