package org.srge.bonsai;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

	// Splash screen timer
	final static int SPLASH_TIME_OUT = 1000;
	public BonsaiDatabaseHelper dbHelper;
	private Context mAppContext;

	private String[] defs = {
			"An atom or group of atoms arranged in a particular way that is primarily responsible for the chemical and physical properties of the molecule in which it is found. There are a total of 10 of these.",
			"Unsaturated hydrocarbons containing at least one carbon-carbon triple bond. Noted by the suffix \"-yne\"",
			"The reaction of alkanes, alkenes, or alcohols with excess oxygen yields carbon dioxide, water, and heat.",
			"Flourine (F), Chlorine (Cl), Bromine (Br), and Iodine (I).",
			"An element that has the capacity to share four electrons in order to achieve a more stable configuration.",
			"Atomic Number = 6, Protons = 6, Electrons = 6, Atomic Weight = 12.0. Electrons in first energy level = 2; second energy level = 4.",
			"Contain carbon-to-carbon double or triple bonds.",
			"Contain only only carbon-to-carbon single bonds. The most chemically inert of all organic compounds.",
			"Inter-atomic relationship created by the sharing of at least one pair of electrons.",
			"The branch of chemistry which deals with carbon compounds, including those with no relationship to life." };

	private String[] terms = { "Functional Group", "Alkyne",
			"Hydrocarbon Combustion", "Halogens", "Carbon",
			"Atomic Structure of Carbon", "Unsaturated Hydrocarbon",
			"Saturated Hydrocarbond", "Covalent Bond", "Organic Chemistry" };

	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_start_screen);

		mAppContext = this.getApplicationContext();
		dbHelper = new BonsaiDatabaseHelper(mAppContext);
		
		//dbHelper.insertDeck("Organic Chemistry");
		
		//for(int i=0; i<defs.length-1; i++) {
		//	dbHelper.insertCard(1, terms[i], defs[i], 0, 0, null, null, null);
		//}

		dbHelper.close();
		
		new Handler().postDelayed(new Runnable() {

			/*
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

			@Override
			public void run() {
				// This method will be executed once the timer is over
				// Start your app main activity
				Intent i = new Intent(SplashActivity.this,
						MainMenuActivity.class);
				startActivity(i);

				// close this activity
				finish();
			}
		}, SPLASH_TIME_OUT);
	}

}
