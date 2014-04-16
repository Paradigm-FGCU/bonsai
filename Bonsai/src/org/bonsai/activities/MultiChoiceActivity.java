package org.bonsai.activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.bonsai.util.CActionBarActivity;
import org.srge.bonsai.R;
import org.srge.card.CardInfo;
import org.srge.card.RunningInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MultiChoiceActivity extends CActionBarActivity {
	
	ArrayList<CardInfo> cards = RunningInfo.getWorkingCardList();
	//ArrayList<CardInfo> cards = RunningInfo.getSelectedDeck().getCardList();
	int score = 0;
	int qid = 0;
	

	TextView txtQuestion;
	RadioButton rda, rdb, rdc, rdd;
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
		rdd = (RadioButton) findViewById(R.id.radio_Multi3);
		butNext = (Button) findViewById(R.id.button_multi);
		setQuestionView();
		
		butNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RadioGroup grp = (RadioGroup) findViewById(R.id.radioMulti);
				
				//Get radio button answer
				RadioButton answer = (RadioButton) findViewById(grp
						.getCheckedRadioButtonId());
				
				
				//Update cards score and # of times seen
				if (cards.get(qid).getAnswer().equals(answer.getText())) {
					score++;
					cards.get(qid).answeredCorrect();
				}
				else{
					cards.get(qid).answeredIncorrect();
				}
				
				if ((qid != (cards.size()-1))) {
					qid++;
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
		//Create array List with Answers
		ArrayList<String> text =new ArrayList<String>(4);
		
		
		/*
		//Add Fake Answer
		ArrayList<String> fake = cards.get(qid).getFakeAnswers();
		for(String f:fake){
			text.add(f);
		}
		*/
		
		//remove
		for(int i = 0;i<3;i++){
			text.add("fake answer"+i);
		}
		
		text.add(cards.get(qid).getAnswer());
		
		//Randomize Radio Text Array
		long seed = System.nanoTime();
		Collections.shuffle(text, new Random(seed));
		
		//Set Text For Activity
		txtQuestion.setText(cards.get(qid).getQuestion());
		rda.setText(text.get(0));
		rdb.setText(text.get(1));
		rdc.setText(text.get(2));
		rdd.setText(text.get(3));		
	}
}

