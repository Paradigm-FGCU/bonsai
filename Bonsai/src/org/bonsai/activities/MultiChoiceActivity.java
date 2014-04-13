package org.bonsai.activities;

import java.util.ArrayList;
import java.util.List;

import org.bonsai.util.CActionBarActivity;
import org.srge.bonsai.R;
import org.srge.card.CardInfo;
import org.srge.card.RunningInfo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MultiChoiceActivity extends CActionBarActivity {
	//List<MultiInfo> quesList;
	ArrayList<CardInfo> cards = RunningInfo.getWorkingCardList();
	int score = 0;
	int qid = 0;

	//MultiInfo currentQ;

	TextView txtQuestion;
	RadioButton rda, rdb, rdc;
	Button butNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi);
		
		
		//quesList = RunningInfo.getMuiltiList();
		
		
		//currentQ = quesList.get(qid);
		txtQuestion = (TextView) findViewById(R.id.textView_Multi_Question);
		rda = (RadioButton) findViewById(R.id.radio_Multi0);
		rdb = (RadioButton) findViewById(R.id.radio_Multi1);
		rdc = (RadioButton) findViewById(R.id.radio_Multi2);
		butNext = (Button) findViewById(R.id.button_multi);
		setQuestionView();

		butNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RadioGroup grp = (RadioGroup) findViewById(R.id.radioMulti);
				RadioButton answer = (RadioButton) findViewById(grp
						.getCheckedRadioButtonId());
				//Log.e("yourans", cards.get(qid).getAnswer() + " " + answer.getText());

				if (cards.get(qid).getAnswer().equals(answer.getText())) {
					score++;
					//Log.e("score", "Your score" + score);
				}
				
				if (qid < (cards.size())) {
					setQuestionView();
				} else {
					 Intent intent = new Intent(MultiChoiceActivity.this,ResultActivity.class); Bundle b = new Bundle();
					 b.putInt("score", score); //Your score
					 intent.putExtras(b); //Put your score to your next Intent
					 startActivity(intent); 
					 finish();
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_multi, menu);
		return true;
	}

	private void setQuestionView() {
		txtQuestion.setText(cards.get(qid).getQuestion());
		rda.setText("currentQ.getOPTA()"+qid);
		rdb.setText("currentQ.getOPTB()"+qid);
		rdc.setText("currentQ.getOPTC()"+score);
		qid++;
	}
}

