package org.bonsai.activities;

import org.bonsai.util.CActionBarActivity;
import org.srge.bonsai.R;
import org.srge.card.RunningInfo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.RatingBar;
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

		// display score
		int deckSize = RunningInfo.getWorkingCardList().size();
		scoreText.setText("Score: " + score + " out of " + deckSize);

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
		else if (percentScore == 0)
			t.setText("Kill Yourself: Your a Failure in LIFE");
		else
			t.setText("Try Again, Maybe this time open the book :(");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
	}
}
