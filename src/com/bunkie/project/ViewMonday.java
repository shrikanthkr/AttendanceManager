package com.bunkie.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewMonday extends Activity{
	String mon;
	String[] mon_array;
	TextView mon_one,mon_two,mon_three,mon_four,mon_five,mon_six,mon_seven,mon_eight,mon_nine,mon_ten;
    TimeTableDatabase ttd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewmonday);
		 ttd=new TimeTableDatabase(this);
	     ttd.open();
	     mon=ttd.getMondayPeriod();
	     ttd.close();
	     
	        mon_one=(TextView)this.findViewById(R.id.mon_TextView1);
		    mon_two=(TextView)this.findViewById(R.id.mon_TextView2);
		    mon_three=(TextView)this.findViewById(R.id.mon_TextView3);
		    mon_four=(TextView)this.findViewById(R.id.mon_TextView4);
		    mon_five=(TextView)this.findViewById(R.id.mon_TextView5);
		    mon_six=(TextView)this.findViewById(R.id.mon_TextView6);
		    mon_seven=(TextView)this.findViewById(R.id.mon_TextView7);
		    mon_eight=(TextView)this.findViewById(R.id.mon_TextView8);
		    mon_nine=(TextView)this.findViewById(R.id.mon_TextView9);
		    mon_ten=(TextView)this.findViewById(R.id.mon_TextView10);
		    
		    mon_array=mon.split("\n");
		    
		    mon_one.setText(mon_array[0]);
	        mon_two.setText(mon_array[1]);
	        mon_three.setText(mon_array[2]);
	        mon_four.setText(mon_array[3]);
	        mon_five.setText(mon_array[4]);
	        mon_six.setText(mon_array[5]);
	        mon_seven.setText(mon_array[6]);
	        mon_eight.setText(mon_array[7]);
	        mon_nine.setText(mon_array[8]);
	        mon_ten.setText(mon_array[9]);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent todash=new Intent(ViewMonday.this,DashBoard.class);
		startActivity(todash);
	}
	
	

}
