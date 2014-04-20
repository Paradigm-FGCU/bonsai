package org.bonsai.activities;

import org.bonsai.activities.FlashActivity.SectionsPagerAdapter;
import org.srge.bonsai.R;
import org.srge.card.RunningInfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class EditDeckFragment extends Fragment{

	private SectionsPagerAdapter parent;
	private TextView definitionTextView; 
	private TextView termTextView; 

    public static final String ARG_SECTION_NUMBER = "section_number";
  

    public EditDeckFragment() {

    }

    @Override
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	parent = RunningInfo.parent;
    	
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_edit_card, container, false);

        
        EditText termEditText = (EditText)rootView.findViewById(R.id.editable_term);
        EditText definitionEditText = (EditText)rootView.findViewById(R.id.editable_definition);
        

        definitionEditText.setText(RunningInfo.getSelectedDeck().getCardList().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getQuestion());
        termEditText.setText(RunningInfo.getSelectedDeck().getCardList().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getAnswer());
    


        
        return rootView;
    }
    
}
