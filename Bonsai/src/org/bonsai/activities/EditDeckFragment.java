package org.bonsai.activities;

import java.util.ArrayList;

import org.bonsai.activities.FlashActivity.SectionsPagerAdapter;
import org.bonsai.util.OnSwipeTouchListener;
import org.srge.bonsai.R;
import org.srge.card.CardInfo;
import org.srge.card.DeckInfo;
import org.srge.card.RunningInfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class EditDeckFragment extends Fragment{

	private EditDeckActivity.SectionsPagerAdapter parent;
	private TextView definitionEditText; 
	private TextView termEditText; 
	private TextView idTextView; 
	private ArrayList<CardInfo> deck;

    public static final String ARG_SECTION_NUMBER = "section_number";
  

    public EditDeckFragment() {

    }

    @Override
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	parent = RunningInfo.parent2;
    	
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_edit_card, container, false);
        deck = parent.activityParent.getDeck();
        
        termEditText = (EditText)rootView.findViewById(R.id.editable_term);
        definitionEditText = (EditText)rootView.findViewById(R.id.editable_definition);
        idTextView = (TextView)rootView.findViewById(R.id.textViewID);
        
        String term = deck.get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getQuestion();
        String definition = deck.get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getAnswer();
        if(term == null) term = "";
        if(definition ==null) definition = "";
        definitionEditText.setText(definition);
        termEditText.setText(term);
    
        final Button button_save_card = (Button) rootView.findViewById(R.id.button_save_card);
        button_save_card.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	String term = termEditText.getText().toString();
            	String definition = definitionEditText.getText().toString();
            	if(term==null){
        			Toast.makeText(getActivity().getApplicationContext(),
            				"Error: term text on card", Toast.LENGTH_SHORT).show();
        			return;
        		}
        		if(definition==null){
        			Toast.makeText(getActivity().getApplicationContext(),
            				"Error: definition text on card", Toast.LENGTH_SHORT).show();
        			return;
        		}
        		CardInfo oldCard = deck.get(getArguments().getInt(ARG_SECTION_NUMBER)-1);
        		CardInfo tempCard = new CardInfo(oldCard.getId(),definition,term,oldCard.getFakeAnswers(),oldCard.getParentDeck());
            	parent.activityParent.updateCard(tempCard,getArguments().getInt(ARG_SECTION_NUMBER)-1);
            	Toast.makeText(getActivity().getApplicationContext(),
        				"Card Saved", Toast.LENGTH_SHORT).show();
            }
        });
        
        
        return rootView;
    }
    
}
