package org.srge.bonsai;

import java.util.ArrayList;

import android.app.ListActivity;

import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.TextView;

public class Activity_Select_A_Deck extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_select_a_deck);
		
		ListView listView = getListView();
		ArrayList<String> deckNames = TestingDriver.deckNames;
	
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,deckNames));
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
				String temp = "New active deck is: " + ((TextView) view).getText().toString();
			    Toast.makeText(getApplicationContext(),
				temp, Toast.LENGTH_SHORT).show();
			    RunningInfo.setSelectedDeck(TestingDriver.getDeckList().get(position));
			}
		});
		
		

	}
	
	
		
	
}
