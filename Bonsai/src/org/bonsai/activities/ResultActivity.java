package org.bonsai.activities;

import java.util.*;

import org.bonsai.util.CActionBarActivity;
import org.bonsai.util.ExpandableListAdapter;
import org.srge.bonsai.R;
import org.srge.card.CardInfo;
import org.srge.card.DeckInfo;
import org.srge.card.RunningInfo;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class ResultActivity extends CActionBarActivity {
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String,String> listDataChild;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		// get XML components
		TextView scoreText = (TextView) findViewById(R.id.text_score);
		expListView = (ExpandableListView) findViewById(R.id.textResult);
		 
	   
		
		// preparing Results data
				
		//Get Vars
		int deckSize = RunningInfo.getWorkingCardList().size();	
		ArrayList<CardInfo> cards = RunningInfo.getWorkingCardList();
		DeckInfo deck = RunningInfo.getSelectedDeck();
		
		// get score
		Bundle b = getIntent().getExtras();
		int score = b.getInt("score");
		//quiz results
		boolean[] quizResults = b.getBooleanArray("quizResults");
		int pScore= (score / deckSize) * 100;
		//get Selected Answers
		String[] selectedAns = b.getStringArray("selectedAns");
		
		//Get/Set Quiz Average
		double dDeckSize =deckSize;
		double dScore=score;
		deck.reaverageQuiz(pScore);
		double quizAve = deck.getQuizAverage()*100;
		double quizPercent = (dScore/dDeckSize) *100;
		
		
		//Format score	
		String scoreT = String.format("Score: %d out of %d  %3.0f%%\n\nQuiz Results: \nAverage Quiz Score: %3.0f%%\n", score,deckSize, quizPercent,quizAve);
		
		
		//Format ListView Info
		listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String,String>();
        ArrayList<CardInfo> cardList = RunningInfo.getSelectedDeck().getCardList();
		
		for(int i =0;i<quizResults.length;i++){
			int qNum = i+1;
			String cardCorrectness;
			double cardCorrectPercent;
			
			 // Adding child data
	             
			
			
			//Calculate past correct Ave for card
			double nSeen = cards.get(i).getNumberSeen();
			double nCorrect =cards.get(i).getNumberCorrect();
			if(nSeen >0)
				cardCorrectPercent= (nCorrect/nSeen)*100;
			else
				cardCorrectPercent=nCorrect*100;
			
			//Get current result text
			if (quizResults[i])
				cardCorrectness="Correct";
			else
				cardCorrectness="InCorrect";
			
			
			String parText =String.format("Q%3d:%-14sAvg: %3.0f%%",qNum,cardCorrectness,cardCorrectPercent);
			
			String childText = String.format("Term:\n%s\n\n",cardList.get(i).getQuestion());
			if(!cardList.get(i).getAnswer().equals(selectedAns[i])){
				childText += String.format("Your Answer:\n%s\n\n",selectedAns[i]);
			}
			childText += String.format("Correct Answer:\n%s\n\n",cardList.get(i).getAnswer());
			
			//input to Parent Child Arraylists
			listDataHeader.add(parText);
        	listDataChild.put(listDataHeader.get(i), childText);
			
			
		}
		
		
		// OutPut to XML

		// Display Score
		scoreText.setText(scoreT);

		// Display to list View Results
		listAdapter = new ExpandableListAdapter(this, listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
	}
}
