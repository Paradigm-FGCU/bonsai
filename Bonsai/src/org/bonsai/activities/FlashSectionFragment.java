package org.bonsai.activities;

import org.bonsai.activities.FlashActivity.Passing;
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
	private TextView definitionTextView; 
	private TextView termTextView; 
	//private ImageView up_arrow;
	//private ImageView down_arrow;

    public static final String ARG_SECTION_NUMBER = "section_number";
  

    public FlashSectionFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    	Passing temp = (Passing)getArguments().getSerializable("parent");
    	parent = temp.getParent();

        final View rootView = inflater.inflate(R.layout.fragment_card, container, false);

        final ViewSwitcher viewFlipper = (ViewSwitcher)rootView.findViewById(R.id.viewFlipper);

        
        TextView termTextView = (TextView)(((RelativeLayout)viewFlipper.getChildAt(0)).getChildAt(0));
        
        TextView definitionTextView = (TextView)(((RelativeLayout)viewFlipper.getChildAt(1)).getChildAt(1));

        definitionTextView.setText(RunningInfo.getWorkingCardList().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getAnswer());
        termTextView.setText(RunningInfo.getWorkingCardList().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getQuestion());
    
        mKnowButton = (ImageView)((RelativeLayout)((RelativeLayout)(viewFlipper.getChildAt(1))).getChildAt(2)).getChildAt(0);
        mDontKnowButton = (ImageView)((RelativeLayout)((RelativeLayout)(viewFlipper.getChildAt(1))).getChildAt(2)).getChildAt(1);
        
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
				
		        Toast.makeText(getActivity(), "Good job, my friend!", Toast.LENGTH_SHORT).show();

			}
		});
        
        
        
        mDontKnowButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
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