package com.bunkie.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewTuesday extends Activity{
	String tue;
	String[] tue_array;
	TextView tue_one,tue_two,tue_three,tue_four,tue_five,tue_six,tue_seven,tue_eight,tue_nine,tue_ten;
    TimeTableDatabase ttd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewtuesday);
		 ttd=new TimeTableDatabase(this);
	     ttd.open();
	     tue=ttd.getTuesdayPeriod();
	     ttd.close();
	     
	        tue_one=(TextView)this.findViewById(R.id.tue_TextView1);
		    tue_two=(TextView)this.findViewById(R.id.tue_TextView2);
		    tue_three=(TextView)this.findViewById(R.id.tue_TextView3);
		    tue_four=(TextView)this.findViewById(R.id.tue_TextView4);
		    tue_five=(TextView)this.findViewById(R.id.tue_TextView5);
		    tue_six=(TextView)this.findViewById(R.id.tue_TextView6);
		    tue_seven=(TextView)this.findViewById(R.id.tue_TextView7);
		    tue_eight=(TextView)this.findViewById(R.id.tue_TextView8);
		    tue_nine=(TextView)this.findViewById(R.id.tue_TextView9);
		    tue_ten=(TextView)this.findViewById(R.id.tue_TextView10);
		    
		    tue_array=tue.split("\n");
		    
		    tue_one.setText(tue_array[0]);
	        tue_two.setText(tue_array[1]);
	        tue_three.setText(tue_array[2]);
	        tue_four.setText(tue_array[3]);
	        tue_five.setText(tue_array[4]);
	        tue_six.setText(tue_array[5]);
	        tue_seven.setText(tue_array[6]);
	        tue_eight.setText(tue_array[7]);
	        tue_nine.setText(tue_array[8]);
	        tue_ten.setText(tue_array[9]);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent todash=new Intent(ViewTuesday.this,DashBoard.class);
		startActivity(todash);
	}

	

}
