package org.bonsai.activities;

import java.util.ArrayList;

import org.bonsai.activities.FlashActivity.SectionsPagerAdapter;
import org.srge.bonsai.R;
import org.srge.card.CardInfo;
import org.srge.card.DeckInfo;
import org.srge.card.RunningInfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EditDeckFragment extends Fragment{

	private EditDeckActivity.SectionsPagerAdapter parent;
	private TextView definitionEditText; 
	private TextView termEditText; 
	private ArrayList<CardInfo> deck;

    public static final String ARG_SECTION_NUMBER = "section_number";
  

    public EditDeckFragment() {

    }

    @Override
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	parent = RunningInfo.parent2;
    	
    }
    
    public void setText(){
    	String term = parent.activityParent.getDeck().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getQuestion();
        String definition = parent.activityParent.getDeck().get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getAnswer();

        if(term == null) term = "";
        if(definition ==null) definition = "";

        setDefinition(definition);
        setTerm(term);

    }
    
    public void setTerm(String term){
    	if(termEditText==null) return;
    	termEditText.setText(term);
    }
    
    public void setDefinition(String definition){
    	if(definitionEditText==null) return;
    	definitionEditText.setText(definition);

    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_edit_card, container, false);
        deck = parent.activityParent.getDeck();
        
        RelativeLayout layout = (RelativeLayout)rootView.findViewById(R.id.layout);
        
        termEditText = (EditText)layout.getChildAt(1);
        definitionEditText = (EditText)layout.getChildAt(3);

        setText();
        
        final Button button_delete_card = (Button) rootView.findViewById(R.id.button_delete_card);
        button_delete_card.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	AlertDialog.Builder builder = new AlertDialog.Builder(parent.activityParent);
            	builder
            	.setTitle("Deleting Card")
            	.setMessage("Are You Sure?")
            	.setIcon(android.R.drawable.ic_dialog_alert)
            	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            	    public void onClick(DialogInterface dialog, int which) {			      	
            	    	parent.activityParent.deleteCard(deck.get(getArguments().getInt(ARG_SECTION_NUMBER)-1).getId());
            	    	setText();
            	    	Toast.makeText(getActivity().getApplicationContext(),
                				"Card Deleted", Toast.LENGTH_SHORT).show();
            	    }
            	})
            	.setNegativeButton("No", null)						
            	.show();
            	
            }
        });
        
        
        final Button button_save_card = (Button) rootView.findViewById(R.id.button_save_card);
        button_save_card.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	String term = definitionEditText.getText().toString();
            	String definition = termEditText.getText().toString();
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
        		CardInfo tempCard = new CardInfo(oldCard.getId(),definition,term,oldCard.getFakeAnswers().get(0),
        									oldCard.getFakeAnswers().get(1),oldCard.getFakeAnswers().get(2),oldCard.getParentDeck());
            	parent.activityParent.updateCard(tempCard,getArguments().getInt(ARG_SECTION_NUMBER)-1);
            	Toast.makeText(getActivity().getApplicationContext(),
        				"Card Saved", Toast.LENGTH_SHORT).show();
            }
        });
        
        
        return rootView;
    }
    
}
