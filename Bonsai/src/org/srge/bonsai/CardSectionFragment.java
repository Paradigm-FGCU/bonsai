package org.srge.bonsai;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;



public class CardSectionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
	private Button mKnowButton;
	private Button mDontKnowButton;
	private SectionsPagerAdapter parent;
	private ArrayList<String> defs;
	private TextView answerTextView; 
	private ImageView up_arrow;
	private ImageView down_arrow;

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
        final ViewSwitcher viewFlipper = (ViewSwitcher)rootView.findViewById(R.id.viewFlipper);
        answerTextView = (TextView) rootView.findViewById(R.id.question_text_view);
        mKnowButton = (Button) rootView.findViewById(R.id.know_button);
        mDontKnowButton = (Button) rootView.findViewById(R.id.donotknow_button);
        up_arrow = (ImageView)rootView.findViewById(R.id.up_arrow);
        down_arrow = (ImageView)rootView.findViewById(R.id.down_arrow);
        down_arrow.setVisibility(View.VISIBLE);
        up_arrow.setVisibility(View.GONE);
        mDontKnowButton.setVisibility(View.GONE);  
    	mKnowButton.setVisibility(View.GONE);    
        viewFlipper.setOnTouchListener(new OnSwipeTouchListener() {
            public void onSwipeTop() {
            	up_arrow.setVisibility(View.VISIBLE);
            	down_arrow.setVisibility(View.GONE);
            	mKnowButton.setVisibility(View.VISIBLE);
            	mDontKnowButton.setVisibility(View.VISIBLE);
            	answerTextView.setText(RunningInfo.getWorkingCardList().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getAnswer());
              
            }

            public void onSwipeRight() {


            }

            public void onSwipeLeft() {



            }

            public void onSwipeBottom() {
            	down_arrow.setVisibility(View.VISIBLE);
                up_arrow.setVisibility(View.GONE);
            	questionTextView.setText(RunningInfo.getWorkingCardList().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getQuestion());
            	mDontKnowButton.setVisibility(View.GONE);  
            	mKnowButton.setVisibility(View.GONE);  
            }
            	
        });

        
        
        mKnowButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// need to use SQLite to fetch questions/answers
				
		        Toast.makeText(getActivity(), "Good job, my friend!", Toast.LENGTH_SHORT).show();

			}
		});
        
        
        
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