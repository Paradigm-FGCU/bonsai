package org.srge.bonsai;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;

public class OptionsActivity extends ActionBarActivity {
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.action_bar_menu, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.resources:
	        	Intent intent = new Intent(this.findViewById(android.R.id.content).getContext(),PeriodicTable.class);
            	startActivityForResult(intent,0);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options_menu);
		final TextView currentDeckNameField = (TextView) findViewById(R.id.current_deck_text);
		ActionBar ab = getActionBar();
	    ab.setTitle("Bonsai: Options");
		if(RunningInfo.getSelectedDeck()!=null) {
			currentDeckNameField.setText(RunningInfo.getSelectedDeck().getDeckName());
		}
		else {
			currentDeckNameField.setText(getResources().getString(R.string.options_no_deck_selected));
		}

		final Button button_change_decks = (Button) findViewById(R.id.button_change_decks);
		button_change_decks.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						SelectDeckActivity.class);
				startActivityForResult(intent, 0);
			}
		});
		
		final Switch mySwitch = (Switch)findViewById(R.id.repeatFlashCardToggle);
		mySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			    RunningInfo.setFlashCardRepeat(isChecked);
			}     
		});
		
		final Button quizSettings = (Button)findViewById(R.id.button_quiz_settings);
		quizSettings.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						QuizSettings.class);
				startActivityForResult(intent, 0);
			}
		});
	}
	
	protected void onResume(){
		super.onResume();
		final TextView currentDeckNameField = (TextView) findViewById(R.id.current_deck_text);
		if(RunningInfo.getSelectedDeck()!=null) {
			currentDeckNameField.setText(RunningInfo.getSelectedDeck().getDeckName());
		}
		else {
			currentDeckNameField.setText(getResources().getString(R.string.options_no_deck_selected));
		}
	}
	
}
