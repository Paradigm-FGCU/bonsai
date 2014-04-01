package org.srge.bonsai;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class SplashActivity extends Activity {

	// Splash screen timer
	// http://www.androidhive.info/2013/07/how-to-implement-android-splash-screen-2/
	final static int SPLASH_TIME_OUT = 1000;
	private BonsaiDatabaseHelper dbHelper;
	private Context mAppContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_start_screen);
		
		mAppContext = this.getApplicationContext();
		dbHelper = new BonsaiDatabaseHelper(mAppContext);
		
		new Handler().postDelayed(new Runnable() {

			/*
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

			@Override
			public void run() {
				// This method will be executed once the timer is over
				// Start your app main activity
				Intent i = new Intent(SplashActivity.this, MainMenuActivity.class);
				startActivity(i);

				// close this activity
				finish();
			}
		}, SPLASH_TIME_OUT);
	}



}
