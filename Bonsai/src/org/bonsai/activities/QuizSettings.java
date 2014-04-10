package org.bonsai.activities;

import org.bonsai.util.CActionBarActivity;
import org.srge.bonsai.R;
import org.srge.bonsai.R.id;
import org.srge.bonsai.R.layout;
import org.srge.card.RunningInfo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;
//import android.widget.RadioGroup.OnCheckedChangeListener;

public class QuizSettings extends CActionBarActivity{
	private RadioGroup radioOrder;
	private View radioTemp;
	
	
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_settings);
		ActionBar ab = getActionBar();
	    ab.setTitle("Bonsai: Quiz Settings");
		final TextView value = (TextView) findViewById(R.id.textView_seekbar_value);
		final SeekBar seekbar = (SeekBar) findViewById(R.id.seekBar_quiz_time);
		value.setText(findStringFromRunning(RunningInfo.getQuizTime()));
		
		final Switch mySwitch = (Switch)findViewById(R.id.timed_quiz_switch);
		mySwitch.setChecked(RunningInfo.getTimedQuiz());
		mySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			    RunningInfo.setTimedQuiz(isChecked);
			    seekbar.setEnabled(mySwitch.isChecked());
			}     
		});
		
		radioOrder = (RadioGroup) findViewById(R.id.radioOrder);
		RadioButton storedButton = (RadioButton)radioOrder.getChildAt(RunningInfo.getCardOrder());
		storedButton.setChecked(true);
		RadioGroup.OnCheckedChangeListener listener =new RadioGroup.OnCheckedChangeListener()
	    {
			@Override
	        public void onCheckedChanged(RadioGroup group, int checkedId)
	        {
			    // get selected radio button from radioGroup
				int selectedId = radioOrder.getCheckedRadioButtonId();
				// find the radiobutton by returned id
			    //radioTemp = (RadioButton) findViewById(selectedId);
			    View radioTemp = radioOrder.findViewById(selectedId);
			    int idx = radioOrder.indexOfChild(radioTemp);
	
				RunningInfo.updateCardOrder(idx);
				
			}

		};
		
		radioOrder.setOnCheckedChangeListener(listener);
		
		seekbar.setEnabled(mySwitch.isChecked());
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
