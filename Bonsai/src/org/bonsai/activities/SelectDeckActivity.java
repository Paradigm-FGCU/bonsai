package org.bonsai.activities;

import java.util.ArrayList;

import org.bonsai.util.CActionBarActivity;
import org.bonsai.util.CustomListAdapter;
import org.srge.bonsai.R;
import org.srge.card.BonsaiDatabaseHelper;
import org.srge.card.DeckInfo;
import org.srge.card.RunningInfo;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


public class SelectDeckActivity extends CActionBarActivity {
	private BonsaiDatabaseHelper dbHelper;
	private Context mContext;
	private CustomListAdapter listAdapter;
	private ArrayList<String> deckNames;
	private ListView mListView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar ab = getActionBar();
	    ab.setTitle("Bonsai: Select A Deck");
	    
		mContext = this.getApplicationContext();
		dbHelper = new BonsaiDatabaseHelper(mContext);
				
		setContentView(R.layout.activity_select_a_deck);

		ListView listView = getListView();
		//deckNames = TestingDriver.deckNames;
		deckNames = dbHelper.getDecksList();


		
		listAdapter = new CustomListAdapter(SelectDeckActivity.this , R.layout.custom_list , deckNames);
		listView.setAdapter(listAdapter);
				
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
				String temp = "New active deck is: " + deckNames.get(position);
			    Toast.makeText(getApplicationContext(),
				temp, Toast.LENGTH_SHORT).show();

			    DeckInfo tempDeck = new DeckInfo(dbHelper.getDeckName(position), dbHelper.getAllCardsFromDeck(position+1));
			    RunningInfo.setSelectedDeck(tempDeck);
			}
		});
		
		

	}
	
	protected ListView getListView() {
	    if (mListView == null) {
	        mListView = (ListView) findViewById(android.R.id.list);
	    }
	    return mListView;
	}
		
	
}
