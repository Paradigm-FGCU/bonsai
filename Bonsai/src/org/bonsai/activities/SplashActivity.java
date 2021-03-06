package org.bonsai.activities;

import org.bonsai.util.LoadingPhrases;
import org.srge.bonsai.R;
import org.srge.card.BonsaiDatabaseHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends Activity {

	// Splash screen timer
	// static int SPLASH_TIME_OUT = 4100;
	static int SPLASH_TIME_OUT = 1100;
	// if its the first time they are seeing the splash screen
	static boolean first = true;

	// number of moves it will take for the progress bar to get to 100%
	// larger number = smoother moving
	static int number_of_moves = 50;
	static int progress_timer;
	static int mover_temp;

	public BonsaiDatabaseHelper dbHelper;
	private Context mAppContext;
	ProgressBar progressBar;
	int progress;
	TextView loading_message;

	/**
	 * FIRST DEMO DECK ORGANIC CHEMISTRY DO NOT MESS WITH THIS PLEASE
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

	private String[][] chemFakes = {
			{ "Non-polar grouping", "Covalent", "Enthalpy" },
			{ "Hemiacetals", "Aldehyde", "Carbonyl" },
			{ "Acetal", "Ketone", "HCI" },
			{ "Aldehyde", "Carbon-nitrogen", "Tetramine" },
			{ "Halogen", "Carbon-nitrogen", "Cyanohydrin" },
			{ "Halogen", "Hydrazone", "Aromatic" },
			{ "HCN", "Alkense", "Halide" },
			{ "Carboxlyic", "Acyl Chloride", "Amide" },
			{ "Cyclic Anhydride", "NAOH", "Amide" },
			{ "Nitrile", "Aromatic", "Thiol" } };

	/**
	 * SECOND DEMO DECK BIOLOGY DO NOT MESS WITH THIS PLEASE
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

	private String[][] bioFakes = {
			{ "Viruses", "Prokaryotes", "Eurokaryotes" },
			{ "Thalpoid", "Tetraploid", "Two Diploids" },
			{ "Binary Fission", "Translocation", "Diffusion" },
			{ "Bacillus", "Cereus", "Mesoplodon" },
			{ "Starch", "Transpiration", "Glycolysis" },
			{ "Halogen", "Hydrazone", "Cyanohydrin" },
			{ "Humidty", "Temperate", "Tempurature" },
			{ "Blood pressure", "Atrial contractions", "Heart valve force" },
			{ "Cyclic Anhydride", "NAOH", "Amide" },
			{ "Ectothermic organisms", "Keystone species",
					"r-selected organisms" } };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_start_screen);

		loading_message = (TextView) findViewById(R.id.loading_message);
		progressBar = (ProgressBar) findViewById(R.id.splash_progress_bar);

		mAppContext = this.getApplicationContext();
		dbHelper = new BonsaiDatabaseHelper(mAppContext);

		/*
		 * MAKE DATABASE ENTRIES IF DECKS LIST DOES NOT EXIST...
		 */
		if (dbHelper.getDecksList() == null) {

			dbHelper.insertDeckName("Organic Chemistry");
			for (int i = 0; i < chemDefs.length; i++) {
				dbHelper.insertCard(1, chemTerms[i], chemDefs[i], 0, 0,
						chemFakes[i][0], chemFakes[i][1], chemFakes[i][2]);
			}

			dbHelper.insertDeckName("Biology");
			for (int i = 0; i < bioDefs.length; i++) {
				dbHelper.insertCard(2, bioTerms[i], bioDefs[i], 0, 0,
						bioFakes[i][0], bioFakes[i][1], bioFakes[i][2]);
			}
		}
		dbHelper.close();

		// creates the loading bar movements
		progress = 0;
		progress_timer = 0;
		mover_temp = 0;
		while (mover_temp <= number_of_moves) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					ProgressBar myProgressBar = (ProgressBar) findViewById(R.id.splash_progress_bar);
					myProgressBar.setProgress(progress);
					if (progress == 30 || progress == 70) {
						loading_message.setText(LoadingPhrases
								.generatePhrase(loading_message.getText()));
					}
					progress = progress + (100 / number_of_moves);
				}
			}, progress_timer);
			mover_temp = mover_temp + 1;
			progress_timer = progress_timer + (SPLASH_TIME_OUT - 100)
					/ number_of_moves;
		}

		// dbHelper.close();

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

	protected void onResume() {
		super.onResume();
		if (!first) {
			progressBar.setVisibility(View.GONE);
			TextView loading = (TextView) findViewById(R.id.loading_message);
			loading.setVisibility(View.GONE);
		}
	}

	protected void onPause() {
		super.onPause();
		first = false;
		SPLASH_TIME_OUT = 1500;
	}
}
