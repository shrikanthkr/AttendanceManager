package com.bunkie.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewFriday extends Activity{

	String fri;
	String[] fri_array;
	TextView fri_one,fri_two,fri_three,fri_four,fri_five,fri_six,fri_seven,fri_eight,fri_nine,fri_ten;
    TimeTableDatabase ttd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewfriday);
		ttd=new TimeTableDatabase(this);
	     ttd.open();
	     fri=ttd.getFridayPeriod();
	     ttd.close();
	     
	        fri_one=(TextView)this.findViewById(R.id.fri_TextView1);
		    fri_two=(TextView)this.findViewById(R.id.fri_TextView2);
		    fri_three=(TextView)this.findViewById(R.id.fri_TextView3);
		    fri_four=(TextView)this.findViewById(R.id.fri_TextView4);
		    fri_five=(TextView)this.findViewById(R.id.fri_TextView5);
		    fri_six=(TextView)this.findViewById(R.id.fri_TextView6);
		    fri_seven=(TextView)this.findViewById(R.id.fri_TextView7);
		    fri_eight=(TextView)this.findViewById(R.id.fri_TextView8);
		    fri_nine=(TextView)this.findViewById(R.id.fri_TextView9);
		    fri_ten=(TextView)this.findViewById(R.id.fri_TextView10);
		    
		    fri_array=fri.split("\n");
		    
		    fri_one.setText(fri_array[0]);
	        fri_two.setText(fri_array[1]);
	        fri_three.setText(fri_array[2]);
	        fri_four.setText(fri_array[3]);
	        fri_five.setText(fri_array[4]);
	        fri_six.setText(fri_array[5]);
	        fri_seven.setText(fri_array[6]);
	        fri_eight.setText(fri_array[7]);
	        fri_nine.setText(fri_array[8]);
	        fri_ten.setText(fri_array[9]);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent todash=new Intent(ViewFriday.this,DashBoard.class);
		startActivity(todash);
	}
}
