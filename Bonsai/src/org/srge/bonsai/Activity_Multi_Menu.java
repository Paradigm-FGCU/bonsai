package org.srge.bonsai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Multi_Menu extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_multi_menu);

		final Button button_start_multi = (Button) findViewById(R.id.button_start_multi);
		button_start_multi.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),
						Activity_Multi.class);
				startActivityForResult(intent, 0);
			}
		});
		
	}
}
