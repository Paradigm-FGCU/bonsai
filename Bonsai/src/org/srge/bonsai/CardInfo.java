package org.srge.bonsai;

import java.util.ArrayList;

public class CardInfo {
	private String mQuestion;
	private String mAnswer;
	private ArrayList<String> mFakeAnswers;
	private DeckInfo parentDeck;
	private int mNumberSeen;
	private int mNumberCorrect;
	private int mId;
	
	public CardInfo(String question, String answer, DeckInfo parentDeckIn) {
		mQuestion = question;
		mAnswer = answer;
		parentDeck = parentDeckIn; 
	}
	
	public CardInfo(String question, String answer,ArrayList<String> fakeAnswers,DeckInfo parentDeckIn) {
		mQuestion = question;
		mAnswer = answer;
		parentDeck = parentDeckIn; 
		mFakeAnswers = fakeAnswers;
	}
	
	public void setId(int id) {
		mId = id;
	}

	public String getQuestion() {
		return mQuestion;
	}

	public void setQuestion(String question) {
		mQuestion = question;
	}

	public String getAnswer() {
		return mAnswer;
	}

	public void setAnswer(String answer) {
		mAnswer = answer;
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

