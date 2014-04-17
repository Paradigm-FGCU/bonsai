package org.bonsai.activities;

import org.bonsai.util.CActionBarActivity;
import org.srge.bonsai.R;
import org.srge.card.RunningInfo;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class ResultActivity extends CActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		// get score object
		TextView scoreText = (TextView) findViewById(R.id.text_score);

		// get text view
		TextView t = (TextView) findViewById(R.id.textResult);
		// get score
		Bundle b = getIntent().getExtras();
		int score = b.getInt("score");
		boolean[] quizResults = b.getBooleanArray("quizResults");
		// display score
		int deckSize = RunningInfo.getWorkingCardList().size();	
		//ArrayList<CardInfo> cards = RunningInfo.getWorkingCardList();
		
		scoreText.setText("Score: " + score + " out of " + deckSize);
		String result="Quiz Results: \n\n";
		for(int i =0;i<quizResults.length;i++){
			//int per = cards.get(i).getNumberSeen() / cards.get(i).getNumberCorrect();
			result = result +(" Question:"+(i+1)+" "+quizResults[i]+ "\n");
				
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
