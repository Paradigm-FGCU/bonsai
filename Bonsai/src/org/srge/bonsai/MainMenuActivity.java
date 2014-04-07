package org.srge.bonsai;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.ShareActionProvider;
import android.widget.Toast;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;

public class MainMenuActivity extends ActionBarActivity  {
	private ShareActionProvider mShareActionProvider;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {


		// Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	 // Set up ShareActionProvider's default share intent
	    MenuItem shareItem = menu.findItem(R.id.resources);
	    mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
	    mShareActionProvider.setShareIntent(getDefaultIntent());
	    
	    inflater.inflate(R.menu.action_bar_menu, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.resources:
	            
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private Intent getDefaultIntent() {
	    Intent intent = new Intent(Intent.ACTION_SEND);
	    intent.setType("image/*");
	    return intent;
	}
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new TestingDriver();
	setContentView(R.layout.activity_main_menu);
	
	android.support.v7.app.ActionBar actionBar = getSupportActionBar();
	
	
	final Button button_ReviewMode = (Button) findViewById(R.id.button_Review_Mode);
	button_ReviewMode.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	
        	if(RunningInfo.getSelectedDeck()!=null){
        		Intent intent = new Intent(v.getContext(),ReviewActivity.class);
            	startActivityForResult(intent,0);
        	}
        	else{
        		Toast.makeText(getApplicationContext(),
        				"Please Select A Deck", Toast.LENGTH_SHORT).show();
        	}
        }
    });
	
	final Button button_flashcard = (Button) findViewById(R.id.button_flashcard);
    button_flashcard.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	
        	if(RunningInfo.getSelectedDeck()!=null){
        		Intent intent = new Intent(v.getContext(),FlashActivity.class);
            	startActivityForResult(intent,0);
        	}
        	else{
        		Toast.makeText(getApplicationContext(),
        				"Please Select A Deck", Toast.LENGTH_SHORT).show();
        	}
        }
    });
    
	
	final Button button_multi = (Button) findViewById(R.id.button_multi);
    button_multi.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	if(RunningInfo.getSelectedDeck()!=null){
	        	Intent intent = new Intent(v.getContext(),MultiMenuActivity.class);
	        	startActivityForResult(intent,0);
	        }
	    	else{
	    		Toast.makeText(getApplicationContext(),
	    				"Please Select A Deck", Toast.LENGTH_SHORT).show();
	    	}
        }
    });
    
	final Button button_options = (Button) findViewById(R.id.button_options);
    button_options.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	Intent intent = new Intent(v.getContext(),OptionsActivity.class);
        	startActivityForResult(intent,0);
        }
    });
	}
}
