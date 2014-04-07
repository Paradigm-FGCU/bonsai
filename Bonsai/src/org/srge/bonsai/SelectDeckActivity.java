package org.srge.bonsai;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;


public class SelectDeckActivity extends ActionBarActivity {
	private BonsaiDatabaseHelper dbHelper;
	private Context mContext;
	private CustomListAdapter listAdapter;
	private ArrayList<String> deckNames;
	private ListView mListView;
	
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar ab = getActionBar();
	    ab.setTitle("Bonsai: Select A Deck");
	    
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
	
	protected ListView getListView() {
	    if (mListView == null) {
	        mListView = (ListView) findViewById(android.R.id.list);
	    }
	    return mListView;
	}
		
	
}
