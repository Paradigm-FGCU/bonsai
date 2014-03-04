package org.srge.bonsai;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class CardSectionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
	private Button mKnowButton;
	private Button mDontKnowButton;
	
    public static final String ARG_SECTION_NUMBER = "section_number";
  

    public CardSectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
  	
        final View rootView = inflater.inflate(R.layout.fragment_card, container, false);
        final TextView questionTextView = (TextView) rootView.findViewById(R.id.section_label);
        questionTextView.setText("Question "+Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
        
           	
        mKnowButton = (Button) rootView.findViewById(R.id.know_button);
        mKnowButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// need to use SQLite to fetch questions/answers
				//questionTextView.setText("testies1234");
				TextView answerTextView = (TextView) rootView.findViewById(R.id.section_label);
				answerTextView.setText("Answer");
		        Toast.makeText(getActivity(), "Good Job", Toast.LENGTH_LONG).show();

			}
		});
        
        
        mDontKnowButton = (Button) rootView.findViewById(R.id.donotknow_button);
        mDontKnowButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView answerTextView = (TextView) rootView.findViewById(R.id.section_label);
				answerTextView.setText("Answer");
		        Toast.makeText(getActivity(), "You should be ashamed!", Toast.LENGTH_LONG).show();
				
			}
		});
    

        
        return rootView;
    }
	
}