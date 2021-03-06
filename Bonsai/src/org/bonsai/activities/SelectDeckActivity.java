package org.bonsai.activities;

import java.util.ArrayList;

import org.bonsai.util.CActionBarActivity;
import org.bonsai.util.CustomListAdapter;
import org.srge.bonsai.R;
import org.srge.card.BonsaiDatabaseHelper;
import org.srge.card.DeckInfo;
import org.srge.card.RunningInfo;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
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

		updateListView();
				
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
				String temp = "New active deck is: " + dbHelper.getDeckName(position+1);
			    Toast.makeText(getApplicationContext(),
				temp, Toast.LENGTH_SHORT).show();

			    DeckInfo selectedDeck = new DeckInfo(position+1, dbHelper.getDeckName(position+1), dbHelper.getAllCardsFromDeck(position+1),
			    		dbHelper.getDeckAverage(position+1), dbHelper.getDeckCount(position+1));
			    RunningInfo.setSelectedDeck(selectedDeck);
			    Log.v("Position: ",String.valueOf(position));
			}
		});
		
		

	}
	
	public void updateListView(){
		ListView listView = getListView();
		deckNames = dbHelper.getDecksList();

		listAdapter = new CustomListAdapter(SelectDeckActivity.this , R.layout.custom_list , deckNames);
		listView.setAdapter(listAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.edit_action_bar_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.addCard:
	        	AlertDialog.Builder builder = new AlertDialog.Builder(SelectDeckActivity.this);
            	builder
            	.setTitle("New Deck")
            	.setMessage("Create a New Deck?")
            	.setIcon(android.R.drawable.ic_dialog_alert)
            	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            	    public void onClick(DialogInterface dialog, int which) {			      	
            	    	createDeck();
            	    }
            	})
            	.setNegativeButton("No", null)						
            	.show();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void createDeck(){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Create Deck");
		alert.setMessage("Enter a Name For the Deck");

		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  String value = input.getText().toString();
		  	if(value.trim().length() ==0){ Toast.makeText(getApplicationContext(),
					"Please Insert A Name", Toast.LENGTH_SHORT).show();
		  	}
			dbHelper.insertDeckName(value);
			dbHelper.insertCard(dbHelper.getDecksList().size()+1, "term", "definition", 0, 0, "fake 1", "fake 2", "fake 3");
			updateListView();
		  }
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
		  }
		});

		alert.show();
		
	}
	
	protected ListView getListView() {
	    if (mListView == null) {
	        mListView = (ListView) findViewById(android.R.id.list);
	    }
	    return mListView;
	}
		
	
}
