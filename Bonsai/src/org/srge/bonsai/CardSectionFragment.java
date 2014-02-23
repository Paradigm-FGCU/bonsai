package org.srge.bonsai;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



public class CardSectionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
	private Button mKnowButton;
	private Button mDontKnowButton;
	private TextView mQuestionTextView;
	
    public static final String ARG_SECTION_NUMBER = "section_number";

    public CardSectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
  	
        View rootView = inflater.inflate(R.layout.fragment_card, container, false);
        TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
        dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
        
           	
        mKnowButton = (Button) rootView.findViewById(R.id.know_button);
        mKnowButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
        
        return rootView;
    }
	
}