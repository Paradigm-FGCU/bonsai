package org.bonsai.activities;

import org.bonsai.util.LoadingPhrases;
import org.srge.bonsai.R;
import org.srge.bonsai.R.layout;
import org.srge.card.BonsaiDatabaseHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends Activity {

	// Splash screen timer
	static int SPLASH_TIME_OUT = 4100;
	//if its the first time they are seeing the splash screen
	static boolean first = true;
	
	//number of moves it will take for the progress bar to get to 100%
	//larger number = smoother moving
	static int number_of_moves = 50;
	static int progress_timer;
	static int mover_temp;
	
	public BonsaiDatabaseHelper dbHelper;
	private Context mAppContext;
	ProgressBar progressBar;
	int progress;
	TextView loading_message; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_start_screen);
		mAppContext = this.getApplicationContext();
		//dbHelper = new BonsaiDatabaseHelper(mAppContext);
		loading_message = (TextView)findViewById(R.id.loading_message);
		progressBar = (ProgressBar)findViewById(R.id.splash_progress_bar);

		//dbHelper.insertDeck("Organic Chemistry");
		
		//for(int i=0; i<defs.length-1; i++) {
		//	dbHelper.insertCard(1, terms[i], defs[i], 0, 0, null, null, null);
		//}
		
		
		//creates the loading bar movements
		progress = 0;
		progress_timer = 0;
		mover_temp = 0;
		while(mover_temp<=number_of_moves){
			new Handler().postDelayed(new Runnable() {@Override public void run() {
					ProgressBar myProgressBar = (ProgressBar)findViewById(R.id.splash_progress_bar);
					myProgressBar.setProgress(progress);
					Log.w("from loop", "" + mover_temp);
					if(progress==30 || progress==70){
						loading_message.setText(LoadingPhrases.generatePhrase(loading_message.getText()));
					}
					progress = progress + (100/number_of_moves);
					}}, progress_timer);
			mover_temp = mover_temp + 1;
			progress_timer = progress_timer + (SPLASH_TIME_OUT-100)/number_of_moves;
		}

			

	

		//dbHelper.close();
		

		
		new Handler().postDelayed(new Runnable() {

			/*
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

			@Override
			public void run() {
				// This method will be executed once the timer is over
				// Start your app main activity
				Log.w("in second handler","here");
				Intent i = new Intent(SplashActivity.this,
						MainMenuActivity.class);
				startActivity(i);

				// close this activity
				finish();
			}
		}, SPLASH_TIME_OUT);
	}
	
	protected void onResume() {
		super.onResume();
		if(!first){
			progressBar.setVisibility(View.GONE);
			TextView loading = (TextView)findViewById(R.id.loading_message);
			loading.setVisibility(View.GONE);
		}
	}
	
	protected void onPause(){
		super.onPause();
		first = false;
		SPLASH_TIME_OUT = 1500;
	}
}
