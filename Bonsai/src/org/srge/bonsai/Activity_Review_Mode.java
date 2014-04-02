package org.srge.bonsai;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Activity_Review_Mode extends Activity{
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
        ArrayList<CardContent> cardList = RunningInfo.getSelectedDeck().getCardList();
        // Adding child data
        for(int i=0;i<cardList.size()-1;i++){
        	listDataHeader.add(cardList.get(i).getQuestion());
        	listDataChild.put(listDataHeader.get(i), cardList.get(i).getAnswer());
        }
        
    }
		
}
		
		
