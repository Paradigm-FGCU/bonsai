package org.srge.bonsai;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class QuizSettings extends ActionBarActivity{
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.action_bar_menu, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.resources:
	        	Intent intent = new Intent(this.findViewById(android.R.id.content).getContext(),PeriodicTable.class);
            	startActivityForResult(intent,0);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_quiz_settings);
	ActionBar ab = getActionBar();
    ab.setTitle("Bonsai: Quiz Settings");
	final TextView value = (TextView) findViewById(R.id.textView_seekbar_value);
	final SeekBar seekbar = (SeekBar) findViewById(R.id.seekBar_quiz_time);
	value.setText(findStringFromRunning(RunningInfo.getQuizTime()));
	seekbar.setProgress(findProgressFromRunning(RunningInfo.getQuizTime()));
	seekbar.setOnSeekBarChangeListener( new OnSeekBarChangeListener(){
		public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser)
		{
			String message = findString(progress);
		    if(message!=null) value.setText((String)message);
		}
	
		public void onStartTrackingTouch(SeekBar seekBar){
		       

		}
	
		public void onStopTrackingTouch(SeekBar seekBar){
			double tempd = seekbar.getProgress();
            tempd = Math.round(tempd/10)*10;
			seekbar.setProgress((int)tempd);
			int tempint = 0;
			switch(seekbar.getProgress()){
	    	case 0: tempint = 10;
	    		break;
	    	case 10: tempint = 20;;
    			break;
	    	case 20: tempint = 30;;
				break;
	    	case 30: tempint = 40;;
				break;
	    	case 40: tempint = 50;;
				break;
	    	case 50: tempint = 60;;
				break;
	    	case 60: tempint = 80;;
				break;
	    	case 70: tempint = 100;;
				break;
	    	case 80: tempint = 120;;
				break;
	    	case 90: tempint = 180;;
				break;
	    	case 100: tempint = 300;;
	    		break;
	    }
			
		RunningInfo.setQuizTime(tempint);
		}
	});
	}
	
	public String findString(int progress){
		String message = "";
	    switch(progress){
	    	case 0: message = "10 seconds per card";
	    		break;
	    	case 10: message = "20 seconds per card";
    			break;
	    	case 20: message = "30 seconds per card";
				break;
	    	case 30: message = "40 seconds per card";
				break;
	    	case 40: message = "50 seconds per card";
				break;
	    	case 50: message = "1 minute per card";
				break;
	    	case 60: message = "80 seconds per card";
				break;
	    	case 70: message = "100 seconds per card";
				break;
	    	case 80: message = "2 minutes per card";
				break;
	    	case 90: message = "3 minutes per card";
				break;
	    	case 100: message = "5 minutes per card";
	    		break;
	    	default: return null;
	    }
	    return message;
	}
	
	public String findStringFromRunning(int progress){
		String message = "";
	    switch(progress){
	    	case 10: message = "10 seconds per card";
	    		break;
	    	case 20: message = "20 seconds per card";
    			break;
	    	case 30: message = "30 seconds per card";
				break;
	    	case 40: message = "40 seconds per card";
				break;
	    	case 50: message = "50 seconds per card";
				break;
	    	case 60: message = "1 minute per card";
				break;
	    	case 80: message = "80 seconds per card";
				break;
	    	case 100: message = "100 seconds per card";
				break;
	    	case 120: message = "2 minutes per card";
				break;
	    	case 180: message = "3 minutes per card";
				break;
	    	case 300: message = "5 minutes per card";
	    		break;
	    	default: return " ";
	    }
	    return message;
	}
	public int findProgressFromRunning(int time){
		int progress = 0;
	    switch(time){
	    	case 10: progress = 0;
	    		break;
	    	case 20: progress = 10;
    			break;
	    	case 30: progress = 20;
				break;
	    	case 40: progress = 30;
				break;
	    	case 50: progress = 40;
				break;
	    	case 60: progress = 50;
				break;
	    	case 80: progress = 60;
				break;
	    	case 100: progress = 70;
				break;
	    	case 120: progress = 80;
				break;
	    	case 180: progress = 90;
				break;
	    	case 300: progress = 100;
	    		break;
	    }
	    return progress;
	}
}
