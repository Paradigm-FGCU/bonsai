package org.bonsai.activities;


import org.bonsai.activities.FlashActivity.SectionsPagerAdapter;
import org.bonsai.util.OnSwipeTouchListener;
import org.srge.bonsai.R;
import org.srge.card.RunningInfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;


public class FlashSectionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
	private ImageView mKnowButton;
	private ImageView mDontKnowButton;
	private SectionsPagerAdapter parent;
	@SuppressWarnings("unused")
	private TextView definitionTextView; 
	@SuppressWarnings("unused")
	private TextView termTextView; 

    public static final String ARG_SECTION_NUMBER = "section_number";
  

    public FlashSectionFragment() {

    }

    @Override
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	parent = RunningInfo.parent;
    	
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_card, container, false);

        final ViewSwitcher viewFlipper = (ViewSwitcher)rootView.findViewById(R.id.viewFlipper);
        
        
        TextView termTextView = (TextView)(((RelativeLayout)viewFlipper.getChildAt(0)).getChildAt(0));
        
        TextView definitionTextView = (TextView)(((RelativeLayout)viewFlipper.getChildAt(1)).getChildAt(1));

        definitionTextView.setText(RunningInfo.getWorkingCardList().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getAnswer());
        termTextView.setText(RunningInfo.getWorkingCardList().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getQuestion());
    
        mKnowButton = (ImageView)((RelativeLayout)((RelativeLayout)(viewFlipper.getChildAt(1))).getChildAt(2)).getChildAt(0);
        mDontKnowButton = (ImageView)((RelativeLayout)((RelativeLayout)(viewFlipper.getChildAt(1))).getChildAt(2)).getChildAt(1);
        
        if(parent.answered.get(getArguments().getInt(ARG_SECTION_NUMBER)-1)[0]){
        	mKnowButton.setVisibility(View.GONE);
        	mDontKnowButton.setVisibility(View.GONE);
        }
        
        viewFlipper.setOnTouchListener(new OnSwipeTouchListener() {
            public void onSwipeTop() {
            	if(viewFlipper.getNextView().getId()==R.id.Container2) viewFlipper.showNext();}

            public void onSwipeRight() {
            }
            public void onSwipeLeft() {
            }

            public void onSwipeBottom() {
            	if(viewFlipper.getCurrentView().getId()==R.id.Container2) viewFlipper.showNext();
            }
            	
        });

        
        
        mKnowButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// need to use SQLite to fetch questions/answers
				mKnowButton.setVisibility(View.GONE);
	        	mDontKnowButton.setVisibility(View.GONE);
		        Toast.makeText(getActivity(), "Good job, my friend!\n Statictics Updated", Toast.LENGTH_SHORT).show();
		        parent.answered.get(getArguments().getInt(ARG_SECTION_NUMBER)-1)[0] = true;
		        RunningInfo.questionAnswered(true, RunningInfo.getWorkingCardList().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getId());
			}
		});
        
        
        
        mDontKnowButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mKnowButton.setVisibility(View.GONE);
	        	mDontKnowButton.setVisibility(View.GONE);
				parent.answered.get(getArguments().getInt(ARG_SECTION_NUMBER)-1)[0] = true;
				RunningInfo.questionAnswered(false, RunningInfo.getWorkingCardList().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getId());
				if(RunningInfo.getFlashCardRepeat()){
					parent.answered.add(new boolean[1]);
					RunningInfo.addCardByIndex(getArguments().getInt(ARG_SECTION_NUMBER)-1);
			        Toast.makeText(getActivity(), "Card Added To End\n Statictics Updated", Toast.LENGTH_SHORT).show();
			        parent.notifyDataSetChanged();
				}
				else{
					Toast.makeText(getActivity(), "Statictics Updated", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
    

        
        return rootView;
    }
    
    
}
