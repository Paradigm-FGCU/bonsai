package org.bonsai.activities;

import java.io.Serializable;
import java.util.ArrayList;

import org.bonsai.activities.MultiChoiceActivity.Passing;
import org.bonsai.activities.MultiChoiceActivity.SectionsPagerAdapter;
import org.bonsai.util.OnSwipeTouchListener;
import org.srge.bonsai.R;
import org.srge.bonsai.R.id;
import org.srge.bonsai.R.layout;
import org.srge.card.RunningInfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;
import android.util.Log;



public class MultiSectionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
	private Button buttonMultiSubmit;
	private RadioGroup radioGroupMulti;
	private RadioButton radioMulti0;
	private RadioButton radioMulti1;
	private RadioButton radioMulti2;
	private RadioButton radioMulti3;
	
	private SectionsPagerAdapter parent;
	private ArrayList<String> defs;
	private ImageView up_arrow;
	private ImageView down_arrow;

    public static final String ARG_SECTION_NUMBER = "section_number";
  

    public MultiSectionFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    	Passing temp = (Passing)getArguments().getSerializable("parent");
    	parent = temp.getParent();
    	
        final View rootView = inflater.inflate(R.layout.fragment_multi, container, false);
        final TextView textViewMultiQuestion = (TextView) rootView.findViewById(R.id.textView_Multi_Question);
        textViewMultiQuestion.setText(RunningInfo.getWorkingCardList().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getQuestion());
        final ViewSwitcher viewFlipper = (ViewSwitcher)rootView.findViewById(R.id.viewFlipper);
       
        radioGroupMulti = (RadioGroup) rootView.findViewById(R.id.radioGroup_Multi);
        buttonMultiSubmit = (Button) rootView.findViewById(R.id.button_multi_submit);
        radioMulti0 = (RadioButton) rootView.findViewById(R.id.radio_Multi0);
        radioMulti1 = (RadioButton) rootView.findViewById(R.id.radio_Multi1);
        radioMulti2 = (RadioButton) rootView.findViewById(R.id.radio_Multi2);
        radioMulti3 = (RadioButton) rootView.findViewById(R.id.radio_Multi3);
        
        up_arrow = (ImageView)rootView.findViewById(R.id.up_arrow);
        down_arrow = (ImageView)rootView.findViewById(R.id.down_arrow);
        down_arrow.setVisibility(View.VISIBLE);
        up_arrow.setVisibility(View.GONE);
        buttonMultiSubmit.setVisibility(View.GONE);  
        
        viewFlipper.setOnTouchListener(new OnSwipeTouchListener() {
            public void onSwipeTop() {
            	/*
            	up_arrow.setVisibility(View.VISIBLE);
            	down_arrow.setVisibility(View.GONE);
            	mKnowButton.setVisibility(View.VISIBLE);
            	mDontKnowButton.setVisibility(View.VISIBLE);
            	answerTextView.setText(RunningInfo.getWorkingCardList().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getAnswer());
              */
            }

            public void onSwipeRight() {


            }

            public void onSwipeLeft() {



            }

            public void onSwipeBottom() {
            	/*
            	down_arrow.setVisibility(View.VISIBLE);
                up_arrow.setVisibility(View.GONE);
            	questionTextView.setText(RunningInfo.getWorkingCardList().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getQuestion());
            	mDontKnowButton.setVisibility(View.GONE);  
            	mKnowButton.setVisibility(View.GONE);  
            */
            }
            	
        });

        
        
        radioMulti0.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// need to use SQLite to fetch questions/answers
				
		        Toast.makeText(getActivity(), "Answer 0", Toast.LENGTH_SHORT).show();

			}
		});
        
 radioMulti1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// need to use SQLite to fetch questions/answers
				
		        Toast.makeText(getActivity(), "Answer 1", Toast.LENGTH_SHORT).show();

			}
		});
 radioMulti2.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// need to use SQLite to fetch questions/answers
			
	        Toast.makeText(getActivity(), "Answer 2", Toast.LENGTH_SHORT).show();

		}
	});
 radioMulti3.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// need to use SQLite to fetch questions/answers
			
	        Toast.makeText(getActivity(), "Answer 3", Toast.LENGTH_SHORT).show();

		}
	});
 
        
 buttonMultiSubmit.setOnClickListener(new OnClickListener() {
			
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