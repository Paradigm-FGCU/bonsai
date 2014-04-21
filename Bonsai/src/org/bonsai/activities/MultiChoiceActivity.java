package org.bonsai.activities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.bonsai.util.CActionBarActivity;
import org.srge.bonsai.R;
import org.srge.card.BonsaiDatabaseHelper;
import org.srge.card.CardInfo;
import org.srge.card.RunningInfo;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MultiChoiceActivity extends CActionBarActivity {
	
	ArrayList<CardInfo> cards = RunningInfo.getWorkingCardList();
	int score = 0;
	int qid = 0;
	Context mContext;
	BonsaiDatabaseHelper dbHelper;
	TextView txtQuestion;
	TextView txtQuestionCount;
	RadioButton rda, rdb, rdc, rdd;
	Button butNext;
	ActionBar aBar;
	boolean[] quizResults = new boolean[cards.size()];
	String[] selectedAns = new String[cards.size()];
	private CountDownTimer mCountDown;
	String timerText;
	int[] cardTime = new int[cards.size()+1];
	int remainingTime=0;
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		//Sets Action Bar Title
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multi);
		
		mContext = this.getApplicationContext();
		dbHelper = new BonsaiDatabaseHelper(mContext);
		
		txtQuestion = (TextView) findViewById(R.id.textView_Multi_Question);
		txtQuestionCount = (TextView) findViewById(R.id.quiz_Question_Count);
		aBar = getActionBar();
		rda = (RadioButton) findViewById(R.id.radio_Multi0);
		rdb = (RadioButton) findViewById(R.id.radio_Multi1);
		rdc = (RadioButton) findViewById(R.id.radio_Multi2);
		rdd = (RadioButton) findViewById(R.id.radio_Multi3);
		butNext = (Button) findViewById(R.id.button_multi);
		cardTime[cards.size()]=0;
		setQuestionView();
		aBar.setTitle("Bonsai: " + RunningInfo.getSelectedDeck().getDeckName() + " Quiz");
		//Timer for Quiz Mode
		if (RunningInfo.getTimedQuiz()){
			
		mCountDown = new CountDownTimer((RunningInfo.getQuizTime()*1000), 1000) {

		     public void onTick(long millisUntilFinished) {
		    	remainingTime =(int) millisUntilFinished;
		    	remainingTime = remainingTime /1000;
		    	butNext.setText("Submit\nTime Left: " + millisUntilFinished / 1000+"s");
		     }

		     public void onFinish() {
					
		    	//Update cards score and # of times seen
					selectedAns[qid] ="No Answer: Time Expired";
					cards.get(qid).answeredIncorrect();
					quizResults[qid]=false;
					cardTime[qid]=(RunningInfo.getQuizTime());	
					cardTime[cards.size()] = cardTime[cards.size()] + cardTime[qid];
					Toast.makeText(getApplicationContext(),"Out of Time\nIncorrect", Toast.LENGTH_SHORT).show();
					if ((qid != (cards.size()-1))) {
						
						qid++;	
						
						setQuestionView();
						mCountDown.start();
					} else {
						 Intent intent = new Intent(MultiChoiceActivity.this,ResultActivity.class); 
						 Bundle b = new Bundle();
						 b.putInt("score", score); //Your score
						 b.putBooleanArray("quizResults",quizResults);
						 b.putStringArray("selectedAns",selectedAns);
						 intent.putExtras(b); //Put your score to your next Intent
						 startActivity(intent); 
						 finish();
					}
					
		     }
		     
		  }.start();
		}
		else{
			butNext.setText("Submit");
		}
		butNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				RadioGroup grp = (RadioGroup) findViewById(R.id.radioMulti);
				if (RunningInfo.getTimedQuiz()){
				mCountDown.cancel();
				cardTime[qid]=(RunningInfo.getQuizTime()) - remainingTime;
				cardTime[cards.size()] = cardTime[cards.size()] + cardTime[qid];
				}
				//Get radio button answer
				RadioButton answer = (RadioButton) findViewById(grp
						.getCheckedRadioButtonId());
				
				selectedAns[qid] = (String) answer.getText();
				//Update cards score and # of times seen
				if (cards.get(qid).getQuestion().equals(answer.getText())) {
					score++;
					cards.get(qid).answeredCorrect();
					quizResults[qid]=true;
					Toast.makeText(getApplicationContext(),"Correct", Toast.LENGTH_SHORT).show();
				}
				else{
					cards.get(qid).answeredIncorrect();
					quizResults[qid]=false;
					Toast.makeText(getApplicationContext(),"Incorrect", Toast.LENGTH_SHORT).show();
				}
				
				if ((qid != (cards.size()-1))) {
					qid++;
					setQuestionView();
					if (RunningInfo.getTimedQuiz()){					
					mCountDown.start();
					}
				} else {
					
					 Intent intent = new Intent(MultiChoiceActivity.this,ResultActivity.class); 
					 Bundle b = new Bundle();
					 b.putInt("score", score); //Your score
					 b.putBooleanArray("quizResults",quizResults);
					 b.putStringArray("selectedAns",selectedAns);
					 if (RunningInfo.getTimedQuiz()){
					 b.putIntArray("cardTime",cardTime);
					 }
					 intent.putExtras(b); //Put your score to your next Intent
					 startActivity(intent); 
					 finish();
				}
			}
		});		
	
	}
	
	public boolean onKeyDown(int keycode, KeyEvent event) {
		if (keycode == KeyEvent.KEYCODE_BACK) {

			// Start main Menu activity
			Intent i = new Intent( MultiChoiceActivity.this, MainMenuActivity.class);
			startActivity(i);

			// close this activity
			mCountDown.cancel();
			finish();
		}
		return super.onKeyDown(keycode, event);
	}
	private void setQuestionView() {
		//Create array List with Answers
		ArrayList<String> text = new ArrayList<String>(4);
		
		text.addAll(cards.get(qid).getFakeAnswers());
		text.add(cards.get(qid).getQuestion());
		
		//Randomize Radio Text Array
		long seed = System.nanoTime();
		Collections.shuffle(text, new Random(seed));
		
		//Set Text For Activity
		txtQuestion.setText(cards.get(qid).getAnswer());
		rda.setText(text.get(0));
		rdb.setText(text.get(1));
		rdc.setText(text.get(2));
		rdd.setText(text.get(3));		
		txtQuestionCount.setText("Q: " + (qid+1) + "/" + cards.size());
	}
}

