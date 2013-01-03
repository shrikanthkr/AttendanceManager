package com.bunkie.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewWednesday extends Activity{
	String wed;
	String[] wed_array;
	TextView wed_one,wed_two,wed_three,wed_four,wed_five,wed_six,wed_seven,wed_eight,wed_nine,wed_ten;
    TimeTableDatabase ttd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewwednesday);
		 ttd=new TimeTableDatabase(this);
	     ttd.open();
	     wed=ttd.getWednesdayPeriod();
	     ttd.close();
	     
	        wed_one=(TextView)this.findViewById(R.id.wed_TextView1);
		    wed_two=(TextView)this.findViewById(R.id.wed_TextView2);
		    wed_three=(TextView)this.findViewById(R.id.wed_TextView3);
		    wed_four=(TextView)this.findViewById(R.id.wed_TextView4);
		    wed_five=(TextView)this.findViewById(R.id.wed_TextView5);
		    wed_six=(TextView)this.findViewById(R.id.wed_TextView6);
		    wed_seven=(TextView)this.findViewById(R.id.wed_TextView7);
		    wed_eight=(TextView)this.findViewById(R.id.wed_TextView8);
		    wed_nine=(TextView)this.findViewById(R.id.wed_TextView9);
		    wed_ten=(TextView)this.findViewById(R.id.wed_TextView10);
		    
		    wed_array=wed.split("\n");
		    
		    wed_one.setText(wed_array[0]);
	        wed_two.setText(wed_array[1]);
	        wed_three.setText(wed_array[2]);
	        wed_four.setText(wed_array[3]);
	        wed_five.setText(wed_array[4]);
	        wed_six.setText(wed_array[5]);
	        wed_seven.setText(wed_array[6]);
	        wed_eight.setText(wed_array[7]);
	        wed_nine.setText(wed_array[8]);
	        wed_ten.setText(wed_array[9]);
	

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent todash=new Intent(ViewWednesday.this,DashBoard.class);
		startActivity(todash);
	}
	
}
