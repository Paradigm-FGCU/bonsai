package org.srge.bonsai;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class PeriodicTable extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.periodic_table);
        
        ActionBar ab = getActionBar();
        ab.setTitle("Resoucres: Periodic Table");
	}
}
