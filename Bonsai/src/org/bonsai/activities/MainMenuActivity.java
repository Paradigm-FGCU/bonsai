package org.bonsai.activities;

import org.bonsai.util.CActionBarActivity;
import org.srge.bonsai.R;
import org.srge.card.RunningInfo;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenuActivity extends CActionBarActivity  {
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main_menu);
	
	ActionBar ab = getActionBar();
    ab.setTitle("Bonsai: Main Menu");
	
	final Button button_ReviewMode = (Button) findViewById(R.id.button_Review_Mode);
	button_ReviewMode.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	
        	if(RunningInfo.getSelectedDeck()!=null){
        		Intent intent = new Intent(v.getContext(),ReviewActivity.class);
            	startActivityForResult(intent,0);
        	}
        	else{
        		Toast.makeText(getApplicationContext(),
        				"Please Select A Deck", Toast.LENGTH_SHORT).show();
        	}
        }
    });
	
	final Button button_flashcard = (Button) findViewById(R.id.button_flashcard);
    button_flashcard.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	
        	if(RunningInfo.getSelectedDeck()!=null){
        		Intent intent = new Intent(v.getContext(),FlashActivity.class);
            	startActivityForResult(intent,0);
        	}
        	else{
        		Toast.makeText(getApplicationContext(),
        				"Please Select A Deck", Toast.LENGTH_SHORT).show();
        	}
        }
    });
    
	
	final Button button_multi = (Button) findViewById(R.id.button_multi);
    button_multi.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	if(RunningInfo.getSelectedDeck()!=null){
	        	Intent intent = new Intent(v.getContext(),MultiChoiceActivity.class);
	        	startActivityForResult(intent,0);
	        }
	    	else{
	    		Toast.makeText(getApplicationContext(),
	    				"Please Select A Deck", Toast.LENGTH_SHORT).show();
	    	}
        }
    });
    
	final Button button_options = (Button) findViewById(R.id.button_options);
    button_options.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	Intent intent = new Intent(v.getContext(),OptionsActivity.class);
        	startActivityForResult(intent,0);
        }
    });
	}
}
