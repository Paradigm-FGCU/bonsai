package org.bonsai.activities;

import java.util.ArrayList;

import org.srge.bonsai.R;
import org.srge.card.CardInfo;
import org.srge.card.RunningInfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDeckFragment extends Fragment{

	private EditDeckActivity.SectionsPagerAdapter parent;
	private EditText definitionEditText; 
	private EditText termEditText; 
	private EditText fake1EditText; 
	private EditText fake2EditText; 
	private EditText fake3EditText; 
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
    public void onResume(){
    	super.onResume();
    	setText();
    }
    
    public void setText(){
    	if(getArguments().getInt(ARG_SECTION_NUMBER)-1>parent.activityParent.getDeck().size()-1) return;
    	CardInfo card = parent.activityParent.getDeck().get(getArguments().getInt(ARG_SECTION_NUMBER)-1);
    	String term = card.getQuestion();
        String definition = card.getAnswer();
        ArrayList<String> fakes = card.getFakeAnswers();
        String fake1 = fakes.get(0);
        String fake2 = fakes.get(1);
        String fake3 = fakes.get(2);

        if(term == null) term = "";
        if(definition ==null) definition = "";
        if(fake1 == null) fake1 = "";
        if(fake2 == null) fake2 = "";
        if(fake3 == null) fake3 = "";
        
        setDefinition(definition);
        setTerm(term);
        setFakeAnswers(fake1,fake2,fake3);

    }
    
    public void setTerm(String term){
    	if(termEditText==null) return;
    	termEditText.setText(term);
    }
    
    public void setDefinition(String definition){
    	if(definitionEditText==null) return;
    	definitionEditText.setText(definition);

    }
    
    public void setFakeAnswers(String fake1, String fake2, String fake3){
    	fake1EditText.setText(fake1);
    	fake2EditText.setText(fake2);
    	fake3EditText.setText(fake3);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_edit_card, container, false);
        deck = parent.activityParent.getDeck();
        
        termEditText = (EditText)rootView.findViewById(R.id.editable_term);
        definitionEditText = (EditText)rootView.findViewById(R.id.editable_definition);
        fake1EditText = (EditText)rootView.findViewById(R.id.editable_fake1);
        fake2EditText = (EditText)rootView.findViewById(R.id.editable_fake2);
        fake3EditText = (EditText)rootView.findViewById(R.id.editable_fake3);
        
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
            	String fake1 = fake1EditText.getText().toString();
            	String fake2 = fake2EditText.getText().toString();
            	String fake3 = fake3EditText.getText().toString();
            	
            	if(definition.trim().length() == 0){
        			Toast.makeText(getActivity().getApplicationContext(),
            				"Error: term text on card", Toast.LENGTH_SHORT).show();
        			return;
        		}
        		if(term.trim().length() == 0){
        			Toast.makeText(getActivity().getApplicationContext(),
            				"Error: definition text on card", Toast.LENGTH_SHORT).show();
        			return;
        		}
        		if(fake1.trim().length()==0 || fake2.trim().length()==0 || fake3.trim().length()==0){
        			Toast.makeText(getActivity().getApplicationContext(),
            				"Error: fake answers on card", Toast.LENGTH_SHORT).show();
        			return;
        		}
        		
        		
         		CardInfo oldCard = deck.get(getArguments().getInt(ARG_SECTION_NUMBER)-1);
        		
         		CardInfo tempCard = new CardInfo(oldCard.getId(),definition,term,fake1,
        				fake2,fake3,oldCard.getParentDeck());
            	
        		parent.activityParent.updateCard(tempCard,getArguments().getInt(ARG_SECTION_NUMBER)-1);
            	
            	Toast.makeText(getActivity().getApplicationContext(),
        				"Card Saved", Toast.LENGTH_SHORT).show();
            }
        });
        
        
        return rootView;
    }
    
}
