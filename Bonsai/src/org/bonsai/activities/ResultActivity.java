package org.bonsai.activities;

import java.util.ArrayList;

import org.bonsai.util.CActionBarActivity;
import org.srge.bonsai.R;
import org.srge.card.CardInfo;
import org.srge.card.DeckInfo;
import org.srge.card.RunningInfo;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class ResultActivity extends CActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		// get text view
		TextView scoreText = (TextView) findViewById(R.id.text_score);
		TextView t = (TextView) findViewById(R.id.textResult);
		
		//Get Vars
		int deckSize = RunningInfo.getWorkingCardList().size();	
		ArrayList<CardInfo> cards = RunningInfo.getWorkingCardList();
		DeckInfo deck = RunningInfo.getSelectedDeck();
		
		// get score
		Bundle b = getIntent().getExtras();
		int score = b.getInt("score");
		
		boolean[] quizResults = b.getBooleanArray("quizResults");
		int pScore= (score / deckSize) * 100;
		
		
		//Get/Set Quiz Average
		deck.reaverageQuiz(pScore);
		double quizAve = deck.getQuizAverage()*100;
		double quizPercent = score/deckSize * 100;
		// display score
		scoreText.setText("Score: " + score + " out of " + deckSize+ " - " + quizPercent +"%");
		
		String result="Quiz Results: \n"+"Average Quiz Score: "+ quizAve+"%\n";
		
		
		for(int i =0;i<quizResults.length;i++){
				
			String cardCorrectness;
			int cardCorrectPercent= cards.get(i).getNumberCorrect()/cards.get(i).getNumberSeen()*100;
			
			if (quizResults[i])
				cardCorrectness=": Correct ";
			else
				cardCorrectness=": InCorrect ";
			
			result = result +(" Question_"+(i+1)+cardCorrectness+cardCorrectPercent+"%\n");
				
		}
		t.setText(result);
		/*
		// Score Based Text
		int percentScore = (score / deckSize) * 100;
		if (percentScore == 100)
			t.setText("EXCELLENT 100%");
		else if (percentScore >= 90)
			t.setText("So close to perfection");
		else if (percentScore >= 80)
			t.setText("Good BUT not good enough");
		else if (percentScore >= 70)
			t.setText("Hmmmm.. maybe you have been reading a lot, but try to understand");
		else if (percentScore >= 60)
			t.setText("Opps, try again, keep learning");
		else if (percentScore >= 10)
			t.setText("Try Again, Maybe this time open the book :(");
		else
			t.setText("Kill Yourself: Your a Failure in LIFE");
	

	*/
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
	}
}
