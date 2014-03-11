package org.srge.bonsai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Main_Menu extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

	setContentView(R.layout.activity_main_menu);
	
	final Button button_flashcard = (Button) findViewById(R.id.button_flashcard);
    button_flashcard.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	Intent intent = new Intent(v.getContext(),CardActivity.class);
        	startActivityForResult(intent,0);
        }
    });
    
	
	final Button button_multi = (Button) findViewById(R.id.button_multi);
    button_multi.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	Intent intent = new Intent(v.getContext(),Activity_Multi_Menu.class);
        	startActivityForResult(intent,0);
        }
    });
    
	final Button button_options = (Button) findViewById(R.id.button_options);
    button_options.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
        	Intent intent = new Intent(v.getContext(),Activity_Main.class);
        	startActivityForResult(intent,0);
        }
    });
	}
}
