package org.srge.bonsai;

public class CardContent {
	public static final String DatabaseName = "bonsai.sqlite3";
	private int mQuestion;
	private int mAnswer;
	
	public CardContent(int question, int answer) {
		mQuestion = question;
		mAnswer = answer;
	}

	public int getQuestion() {
		return mQuestion;
	}

	public void setQuestion(int question) {
		mQuestion = question;
	}

	public int getAnswer() {
		return mAnswer;
	}

	public void setAnswer(int answer) {
		mAnswer = answer;
	}
	
	
}

