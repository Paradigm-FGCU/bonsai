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

	
	/**
	* FIRST DEMO DECK
	* ORGANIC CHEMISTRY
	* DO NOT MESS WITH THIS PLEASE
	*/
	private String[] chemDefs = {
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

	private String[] chemTerms = { "Functional Group", "Alkyne",
			"Hydrocarbon Combustion", "Halogens", "Carbon",
			"Atomic Structure of Carbon", "Unsaturated Hydrocarbon",
			"Saturated Hydrocarbond", "Covalent Bond", "Organic Chemistry" };

	/**
	* SECOND DEMO DECK
	* BIOLOGY
	* DO NOT MESS WITH THIS PLEASE
	*/

	private String[] bioDefs = {
			"Mitotic phase during which daughter chromosomes move toward the poles of the spindle",
			"Formation of new blood vessels; one mechanism by which cancer spreads",
			"Programmed cell death involving a cascade of specific cellular events leading to death and destruction of the cells",
			"Reproduction that requires only one parent and does not and does not involve gametes",
			"Short, radiating fibers produced by the centosomes in animal cells",
			"Splitting of a parent cell into two daughter cells; serves as an asexual form of reproduction in bacteria",
			"Malignant tumor whose nondiffentiated cells exhibit loss of contact inhibition",
			"Development of Cancer",
			"Repeating sequence of events in eukaryotes that involves cell growth and nuclear division; consists of the stages G1 and M",
			"Structure across a dividing plant cell that signals the location of new plasma membranes and cell walls" };

	private String[] bioTerms = { "Anaphase", "Angiogenesis", "Apoptosis",
			"Asexual reproduction", "Aster", "Binary Fission", "Cancer",
			"Carcinogenesis", "Cell Cycle", "Cell Plate" };

	/**
	* THIRD DEMO DECK
	* SOFTWARE SPECS
	* DO NOT MESS WITH THIS PLEASE
	*/
	
	private String[] softDefs = {};
	
	private String[] softTerms = {};
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_start_screen);

		mAppContext = this.getApplicationContext();
		dbHelper = new BonsaiDatabaseHelper(mAppContext);
		
		
		
		/*
		 * MAKE DATABASE ENTRIES IF DECKS LIST
		 * DOES NOT EXIST...
		 */
		if (dbHelper.getDecksList() == null) {

			dbHelper.insertDeck("Organic Chemistry");
			for (int i = 0; i < chemDefs.length - 1; i++) {
				dbHelper.insertCard(1, chemTerms[i], chemDefs[i], 0, 0, null,
						null, null);
			}
			
			
			dbHelper.insertDeck("Biology");
			for (int i = 0; i < bioDefs.length - 1; i++) {
				dbHelper.insertCard(2, bioTerms[i], bioDefs[i], 0, 0, null,
						null, null);
			}
		}
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
