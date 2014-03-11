package org.srge.bonsai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Main extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_start_screen);

        final Button button_start = (Button) findViewById(R.id.button_start);
        button_start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(v.getContext(),Activity_Main_Menu.class);
            	startActivityForResult(intent,0);
            }
        });
        
       
        
        
    }
}
