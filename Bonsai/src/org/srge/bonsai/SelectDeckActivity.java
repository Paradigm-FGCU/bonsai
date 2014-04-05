package org.srge.bonsai;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;

import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.TextView;

public class SelectDeckActivity extends ListActivity {
	private BonsaiDatabaseHelper dbHelper;
	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		mContext = this.getApplicationContext();
		dbHelper = new BonsaiDatabaseHelper(mContext);
		
		
		
		setContentView(R.layout.activity_select_a_deck);
	    //Log.v("log", "" + dbHelper.queryDecks());

		ListView listView = getListView();
		//ArrayList<String> deckNames = TestingDriver.deckNames;
		
		ArrayList<String> deckNames = dbHelper.queryDecks();
	
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
