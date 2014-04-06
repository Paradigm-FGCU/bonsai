package org.srge.bonsai;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        expListView = (ExpandableListView) findViewById(R.id.reviewModeEListView);
 
        // preparing list data
        prepareListData();
 
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
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
		
		
