package com.bunkie.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewThursday extends Activity{
	String thu;
	String[] thu_array;
	TextView thu_one,thu_two,thu_three,thu_four,thu_five,thu_six,thu_seven,thu_eight,thu_nine,thu_ten;
    TimeTableDatabase ttd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewthursday);
		ttd=new TimeTableDatabase(this);
	     ttd.open();
	     thu=ttd.getThursdayPeriod();
	     ttd.close();
	     
	        thu_one=(TextView)this.findViewById(R.id.thu_TextView1);
		    thu_two=(TextView)this.findViewById(R.id.thu_TextView2);
		    thu_three=(TextView)this.findViewById(R.id.thu_TextView3);
		    thu_four=(TextView)this.findViewById(R.id.thu_TextView4);
		    thu_five=(TextView)this.findViewById(R.id.thu_TextView5);
		    thu_six=(TextView)this.findViewById(R.id.thu_TextView6);
		    thu_seven=(TextView)this.findViewById(R.id.thu_TextView7);
		    thu_eight=(TextView)this.findViewById(R.id.thu_TextView8);
		    thu_nine=(TextView)this.findViewById(R.id.thu_TextView9);
		    thu_ten=(TextView)this.findViewById(R.id.thu_TextView10);
		    
		    thu_array=thu.split("\n");
		    
		    thu_one.setText(thu_array[0]);
	        thu_two.setText(thu_array[1]);
	        thu_three.setText(thu_array[2]);
	        thu_four.setText(thu_array[3]);
	        thu_five.setText(thu_array[4]);
	        thu_six.setText(thu_array[5]);
	        thu_seven.setText(thu_array[6]);
	        thu_eight.setText(thu_array[7]);
	        thu_nine.setText(thu_array[8]);
	        thu_ten.setText(thu_array[9]);
	
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent todash=new Intent(ViewThursday.this,DashBoard.class);
		startActivity(todash);
	}

}
