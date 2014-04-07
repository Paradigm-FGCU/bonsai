package org.srge.bonsai;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;

import android.os.Bundle;

import android.widget.ListView;
import android.view.View;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;


public class SelectDeckActivity extends ListActivity {
	private BonsaiDatabaseHelper dbHelper;
	private Context mContext;
	private CustomListAdapter listAdapter;
	private ArrayList<String> deckNames;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		mContext = this.getApplicationContext();
		dbHelper = new BonsaiDatabaseHelper(mContext);
				
		setContentView(R.layout.activity_select_a_deck);

		ListView listView = getListView();
		deckNames = TestingDriver.deckNames;
		//ArrayList<String> deckNames = dbHelper.getDecksList();


		
		listAdapter = new CustomListAdapter(SelectDeckActivity.this , R.layout.custom_list , deckNames);
		listView.setAdapter(listAdapter);
				
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
				String temp = "New active deck is: " + deckNames.get(position);
			    Toast.makeText(getApplicationContext(),
				temp, Toast.LENGTH_SHORT).show();

			    RunningInfo.setSelectedDeck(TestingDriver.getDeckList().get(position));

			    //DeckInfo temp2 = new DeckInfo("orgochem", dbHelper.getAllCardsFromDeck(position+1));
			    //RunningInfo.setSelectedDeck(temp2);
			}
		});
		
		

	}
	
	
		
	
}
