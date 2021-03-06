package org.bonsai.activities;

import java.util.ArrayList;
import java.util.Locale;

import org.bonsai.util.CActionBarActivity;
import org.srge.bonsai.R;
import org.srge.card.BonsaiDatabaseHelper;
import org.srge.card.CardInfo;
import org.srge.card.DeckInfo;
import org.srge.card.RunningInfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDeckActivity extends CActionBarActivity{
	SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    EditText deckNameEditText;
    ArrayList<CardInfo> newList;
    BonsaiDatabaseHelper dbHelper;
    
    @SuppressWarnings("unchecked")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_deck);

        android.app.ActionBar temp = getActionBar();
        temp.setTitle("Bonsai: Edit Deck");
        dbHelper = new BonsaiDatabaseHelper(this.getApplicationContext());
        newList = (ArrayList<CardInfo>)RunningInfo.getSelectedDeck().getCardList().clone();
        		
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.activityParent = this;
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager_edit);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        
        mViewPager.setOffscreenPageLimit(1);
        
        deckNameEditText = (EditText)findViewById(R.id.editable_deckname);
        deckNameEditText.setText(RunningInfo.getSelectedDeck().getDeckName());
        
        final Button edit_delete_deck = (Button) findViewById(R.id.edit_delete_deck);
        edit_delete_deck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	AlertDialog.Builder builder = new AlertDialog.Builder(EditDeckActivity.this);
            	builder
            	.setTitle("Delete")
            	.setMessage("Are you sure you want to delete the deck?")
            	.setIcon(android.R.drawable.ic_dialog_alert)
            	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            	    public void onClick(DialogInterface dialog, int which) {			      	
            	    	deleteDeck();
            	    }
            	})
            	.setNegativeButton("No", null)						
            	.show();
            }
        });
        
    }
    
    public void deleteDeck(){
    	updateDeck();
    	dbHelper.deleteDeck(RunningInfo.getSelectedDeck());
    	RunningInfo.setSelectedDeck(null);
    	finish();
    }
    
    public ArrayList<CardInfo> getDeck(){
    	return this.newList;
    }
    
    @Override
    public void onStop(){
    	DeckInfo temp = RunningInfo.getSelectedDeck();
    	if(temp!=null && temp.getCardList()!=null && temp.getCardList().size()>0) updateDeck();
    	super.onStop();
    }
    
    private void updateDeck(){
    	
    	RunningInfo.getSelectedDeck().setCardList(newList);
    	String readDeckName = deckNameEditText.getText().toString();
    	if(readDeckName.trim().length()!=0) RunningInfo.getSelectedDeck().setDeckName(readDeckName);

		//TODO

    	if(readDeckName!=null) RunningInfo.getSelectedDeck().setDeckName(readDeckName);
    	
		BonsaiDatabaseHelper dbHelper = new BonsaiDatabaseHelper(this.getApplicationContext());
		RunningInfo.getSelectedDeck().getCardList().get(0);

		dbHelper.updateDeckName(RunningInfo.getSelectedDeck());
		dbHelper.updateAllCards(RunningInfo.getSelectedDeck());
    	Toast.makeText(getApplicationContext(),
				"Deck Pushed", Toast.LENGTH_SHORT).show();
    	dbHelper.close();
    }
    
    public void deleteCard(int id){
    	int i = 0;
    	while(i<newList.size()){
    		if(newList.get(i).getId()==id) break;
    		i++;
    	}
    	int temp = newList.get(i).getId();
    	newList.remove(i);
		
		dbHelper.deleteCard(temp);
    	mSectionsPagerAdapter.notifyDataSetChanged();
    	//mSectionsPagerAdapter.setPrimaryItem(mViewPager, 0, mSectionsPagerAdapter.getItem(0));
    	
    	mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.activityParent = this;
        mViewPager.setAdapter(mSectionsPagerAdapter);
        
        
        
    }
    
    public void updateCard(CardInfo temp,int index){
    	newList.set(index,temp);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_action_bar_menu, menu);
        return true;
    }
    

    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.addCard:
	        	AlertDialog.Builder builder = new AlertDialog.Builder(EditDeckActivity.this);
            	builder
            	.setTitle("Add Card To End")
            	.setMessage("Are you sure?")
            	.setIcon(android.R.drawable.ic_dialog_alert)
            	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            	    public void onClick(DialogInterface dialog, int which) {			      	
            	    	addCard();
            	    }
            	})
            	.setNegativeButton("No", null)						
            	.show();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
    
    public void addCard(){
    	if(newList.size()>=50){
    		Toast.makeText(getApplicationContext(),
    				"Max Cards Reached", Toast.LENGTH_SHORT).show();
    		return;
    	}
    	dbHelper.insertCard(RunningInfo.getSelectedDeck().getDeckId(), "term", "definition", 0, 0, "fake 1","fake 2", "fake 3");
    	newList = dbHelper.getAllCardsFromDeck(RunningInfo.getSelectedDeck().getDeckId());
    	mSectionsPagerAdapter.notifyDataSetChanged();
    }
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
    	EditDeckActivity activityParent;
    	
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a DummySectionFragment (defined as a static inner class
            // below) with the page number as its lone argument.
        	RunningInfo.parent2 = this;
        	Fragment fragment = new EditDeckFragment();
            Bundle args = new Bundle();

            args.putInt(EditDeckFragment.ARG_SECTION_NUMBER, position + 1);
            fragment.setArguments(args);

            return fragment;
        }
        

        @Override
        public int getCount() {
            return newList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            position++;
            return getString(R.string.title_section).toUpperCase(l)+" "+position;

        }
        
       
    }
}
