package ru.hitage.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class Author extends Activity implements OnClickListener, OnSeekBarChangeListener{
	SeekBar sbWeight;
	Button btn1;
	Button btn2;
	  
	LinearLayout.LayoutParams lParams1;
	LinearLayout.LayoutParams lParams2;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.author);
		
		  sbWeight = (SeekBar) findViewById(R.id.sbWeight); 
		  sbWeight.setOnSeekBarChangeListener(this);
		    
		    btn1 = (Button) findViewById(R.id.btn1);
		    btn2 = (Button) findViewById(R.id.btn2);
		    
		    lParams1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
		    lParams2 = (LinearLayout.LayoutParams) btn2.getLayoutParams();
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		
		 	int leftValue = progress;
		    int rightValue = seekBar.getMax() - progress;
		    
		    lParams1.weight = leftValue;
		    lParams2.weight = rightValue;
		    
		    btn1.setText(String.valueOf(leftValue));
		    btn2.setText(String.valueOf(rightValue));
		
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

}
