package org.srge.bonsai;

import java.io.Serializable;
import java.util.ArrayList;

import org.srge.bonsai.CardActivity.Passing;
import org.srge.bonsai.CardActivity.SectionsPagerAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;



public class CardSectionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
	private Button mKnowButton;
	private Button mDontKnowButton;
	private SectionsPagerAdapter parent;
	private ArrayList<String> defs;
	/*private String[] defs = {"An atom or group of atoms arranged in a particular way that is primarily responsible for the chemical and physical properties of the molecule in which it is found. There are a total of 10 of these.",
							"Unsaturated hydrocarbons containing at least one carbon-carbon triple bond. Noted by the suffix \"-yne\"",
							"The reaction of alkanes, alkenes, or alcohols with excess oxygen yields carbon dioxide, water, and heat.",
							"Flourine (F), Chlorine (Cl), Bromine (Br), and Iodine (I).",
							"An element that has the capacity to share four electrons in order to achieve a more stable configuration.",
							"Atomic Number = 6, Protons = 6, Electrons = 6, Atomic Weight = 12.0. Electrons in first energy level = 2; second energy level = 4.",
							"Contain carbon-to-carbon double or triple bonds.",
							"Contain only only carbon-to-carbon single bonds. The most chemically inert of all organic compounds.",
							"Inter-atomic relationship created by the sharing of at least one pair of electrons.",
							"The branch of chemistry which deals with carbon compounds, including those with no relationship to life."
	};
	*/
	
	private String[] terms = {"Functional Group",
							  "Alkyne",
							  "Hydrocarbon Combustion",
							  "Halogens",
							  "Carbon",
							  "Atomic Structure of Carbon",
							  "Unsaturated Hydrocarbon",
							  "Saturated Hydrocarbond",
							  "Covalent Bond",
							  "Organic Chemistry"
	};

	
    public static final String ARG_SECTION_NUMBER = "section_number";
  

    public CardSectionFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    	Passing temp = (Passing)getArguments().getSerializable("parent");
    	parent = temp.getParent();
        final View rootView = inflater.inflate(R.layout.fragment_card, container, false);
        final TextView questionTextView = (TextView) rootView.findViewById(R.id.question_text_view);
        questionTextView.setText(RunningInfo.getWorkingCardList().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getQuestion());
        
           	
        mKnowButton = (Button) rootView.findViewById(R.id.know_button);
        mKnowButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// need to use SQLite to fetch questions/answers
				TextView answerTextView = (TextView) rootView.findViewById(R.id.question_text_view);
		        answerTextView.setText(RunningInfo.getWorkingCardList().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getAnswer());
		        Toast.makeText(getActivity(), "Good job, my friend!", Toast.LENGTH_SHORT).show();

			}
		});
        
        
        mDontKnowButton = (Button) rootView.findViewById(R.id.donotknow_button);
        mDontKnowButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView answerTextView = (TextView) rootView.findViewById(R.id.question_text_view);
				answerTextView.setText(RunningInfo.getWorkingCardList().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getAnswer());
				if(RunningInfo.getFlashCardRepeat()){
					RunningInfo.addCardByIndex(getArguments().getInt(ARG_SECTION_NUMBER)-1);
			        Toast.makeText(getActivity(), "Card Added To End", Toast.LENGTH_SHORT).show();
			        parent.notifyDataSetChanged();
				}
				else{
					Toast.makeText(getActivity(), "Good Try", Toast.LENGTH_SHORT).show();
				}
			}
		});
    

        
        return rootView;
    }
    
}