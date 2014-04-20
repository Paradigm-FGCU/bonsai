package org.bonsai.activities;

import java.util.ArrayList;
import java.util.Locale;

import org.bonsai.util.CActionBarActivity;
import org.srge.bonsai.R;
import org.srge.card.RunningInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;


public class FlashActivity extends CActionBarActivity {
	

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        android.app.ActionBar temp = getActionBar();
        temp.setTitle("Bonsai: Flash Card Mode");
        
        
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        
        
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
    	ArrayList<boolean[]> answered;
    	
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            initializeBooleanArrayList();
            
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a DummySectionFragment (defined as a static inner class
            // below) with the page number as its lone argument.
        	RunningInfo.parent = this;
        	Fragment fragment = new FlashSectionFragment();
            Bundle args = new Bundle();

            args.putInt(FlashSectionFragment.ARG_SECTION_NUMBER, position + 1);
            fragment.setArguments(args);

            return fragment;
        }
        
        private void initializeBooleanArrayList(){
        	answered = new ArrayList<boolean[]>();
        	for(int i=0;i<RunningInfo.getWorkingCardList().size();i++){
        		boolean[] temp = new boolean[1];
        		answered.add(temp);
        	}
        }
        
        @Override
        public int getCount() {
            // Show 3 total pages.
            return RunningInfo.getWorkingCardList().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            position++;
            return getString(R.string.title_section).toUpperCase(l)+" "+position;

            //return null;
        }
    }
    /*
    class Passing implements Serializable{

		private static final long serialVersionUID = 1L;
		private FlashActivity.SectionsPagerAdapter parent;
		
		Passing(FlashActivity.SectionsPagerAdapter temp){
			parent = temp;
		}
		public FlashActivity.SectionsPagerAdapter getParent(){
			return parent;
		}
		
    }*/

}
