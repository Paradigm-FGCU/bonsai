package org.srge.bonsai;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenuActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new TestingDriver();
	setContentView(R.layout.activity_main_menu);
	
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
        		Intent intent = new Intent(v.getContext(),CardActivity.class);
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
	        	Intent intent = new Intent(v.getContext(),MultiMenuActivity.class);
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
