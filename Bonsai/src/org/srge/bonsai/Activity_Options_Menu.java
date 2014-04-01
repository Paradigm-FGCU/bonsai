package org.srge.bonsai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

public class Activity_Options_Menu extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options_menu);
		final TextView currentDeckNameField = (TextView) findViewById(R.id.current_deck_text);
		
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
						Activity_Select_A_Deck.class);
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
