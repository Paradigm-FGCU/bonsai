package org.bonsai.activities;

import java.util.ArrayList;
import java.util.Locale;

import org.bonsai.activities.FlashActivity.SectionsPagerAdapter;
import org.bonsai.util.CActionBarActivity;
import org.srge.bonsai.R;
import org.srge.card.RunningInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class EditDeckActivity extends CActionBarActivity{
	SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_deck);

        android.app.ActionBar temp = getActionBar();
        temp.setTitle("Bonsai: Edit Deck");
        
        
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager_edit);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        
        EditText deckNameEditText = (EditText)findViewById(R.id.editable_deckname);
        deckNameEditText.setText(RunningInfo.getSelectedDeck().getDeckName());
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.card, menu);
        return true;
    }

    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.resources:
	        	Intent intent = new Intent(EditDeckActivity.this,PeriodicTable.class);
            	startActivityForResult(intent,0);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
    	
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
