package org.bonsai.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bonsai.util.CActionBarActivity;
import org.bonsai.util.ExpandableListAdapter;
import org.srge.bonsai.R;
import org.srge.card.CardInfo;
import org.srge.card.RunningInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListView;

public class ReviewActivity extends Activity{
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String,String> listDataChild;
	
    @Override
	public void onCreate(Bundle savedInstanceState) {

    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review_mode);
		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.resultsEListView);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);
    }
		
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
    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String,String>();
        ArrayList<CardInfo> cardList = RunningInfo.getSelectedDeck().getCardList();
        // Adding child data
        for(int i=0;i<cardList.size();i++){
        	listDataHeader.add(cardList.get(i).getQuestion());
        	listDataChild.put(listDataHeader.get(i), cardList.get(i).getAnswer());
        }
        
    }
		
}
		
		
