package org.bonsai.activities;

import java.util.ArrayList;
import java.util.Locale;

import org.bonsai.activities.FlashActivity.SectionsPagerAdapter;
import org.bonsai.util.CActionBarActivity;
import org.srge.bonsai.R;
import org.srge.card.CardInfo;
import org.srge.card.RunningInfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class EditDeckActivity extends ActionBarActivity{
	SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    EditText deckNameEditText;
    ArrayList<CardInfo> newList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_deck);

        android.app.ActionBar temp = getActionBar();
        temp.setTitle("Bonsai: Edit Deck");
        
        newList = (ArrayList<CardInfo>)RunningInfo.getSelectedDeck().getCardList().clone();
        		
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.activityParent = this;
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager_edit);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        
        deckNameEditText = (EditText)findViewById(R.id.editable_deckname);
        deckNameEditText.setText(RunningInfo.getSelectedDeck().getDeckName());
        
        final Button button_save_all = (Button) findViewById(R.id.edit_save_all);
        button_save_all.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	AlertDialog.Builder builder = new AlertDialog.Builder(EditDeckActivity.this);
            	builder
            	.setTitle("Saving")
            	.setMessage("Save All Changes?")
            	.setIcon(android.R.drawable.ic_dialog_alert)
            	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            	    public void onClick(DialogInterface dialog, int which) {			      	
            	    	updateDeck();
            	    }
            	})
            	.setNegativeButton("No", null)						
            	.show();
            }
        });
        
    }
    
    public ArrayList<CardInfo> getDeck(){
    	return this.newList;
    }
    
    private void updateDeck(){
    	RunningInfo.getSelectedDeck().setCardList(newList);
    	//TODO update deck with database
    	String readDeckName = deckNameEditText.getText().toString();
    	if(readDeckName!=null) RunningInfo.getSelectedDeck().setDeckName(readDeckName);
    	Toast.makeText(getApplicationContext(),
				"Deck Pushed", Toast.LENGTH_SHORT).show();
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
            	.setTitle("Add Card")
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
    	
    	newList.add(new CardInfo(newList.get(newList.size()-1).getId()+1,"","",newList.get(0).getParentDeck()));
    	mSectionsPagerAdapter.notifyDataSetChanged();
    	mSectionsPagerAdapter.setPrimaryItem(mViewPager, newList.size()-1,mSectionsPagerAdapter.instantiateItem(mViewPager,newList.size()-1));
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
            return RunningInfo.getWorkingCardList().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            position++;
            return getString(R.string.title_section).toUpperCase(l)+" "+position;

        }
        
       
    }
}
