package org.srge.card;

import java.util.ArrayList;

public class CardInfo {
	private String mDefinition;
	private String mTerm;
	private ArrayList<String> mFakeAnswers = new ArrayList<String>(3);
	private DeckInfo parentDeck;
	private int mNumberSeen;
	private int mNumberCorrect;
	private int mId;
	private String mAlt1, mAlt2, mAlt3;
	
	public CardInfo(int id, String question, String answer, String alt1, String alt2, String alt3, DeckInfo parentDeckIn) {
		mId=id;
		mDefinition = question;
		mTerm = answer;
		parentDeck = parentDeckIn;
		mAlt1 = alt1;
		mAlt2 = alt2;
		mAlt3 = alt3;
		mFakeAnswers.add(mAlt1);
		mFakeAnswers.add(mAlt2);
		mFakeAnswers.add(mAlt3);
	}
	
	public CardInfo() {

	}
	
	public void setId(int id) {
		mId = id;
	}
	
	public int getId(){
		return this.mId;
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

