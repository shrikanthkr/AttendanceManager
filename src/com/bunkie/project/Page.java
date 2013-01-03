package com.bunkie.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Page extends Activity implements OnClickListener{
	int flag;
	ImageButton home;
	String frompage;
	TextView one,two,three,four,five,six,seven,eight,nine,ten,eleven,twelve;
	Button b_one,b_two,b_three,b_four,b_five,b_six,b_seven,b_eight,b_nine,b_ten,b_eleven;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		
		Bundle b=getIntent().getExtras();
		flag=b.getInt("FLAG");
		frompage=b.getString("from");
		if(flag==1)
			setContentView(R.layout.aboutpage);
		else
		{
			setContentView(R.layout.helppage);
			one=(TextView) findViewById(R.id.firsttext);
			two=(TextView) findViewById(R.id.secondtext);
			three=(TextView) findViewById(R.id.thirdtext);
			four=(TextView) findViewById(R.id.fourthtext);
			five=(TextView) findViewById(R.id.fifthtext);
			six=(TextView) findViewById(R.id.sixthtext);
			seven=(TextView) findViewById(R.id.seventhtext);
			eight=(TextView) findViewById(R.id.eighthtext);
			nine=(TextView) findViewById(R.id.ninthtext);
			ten=(TextView) findViewById(R.id.tenthtext);
			eleven=(TextView) findViewById(R.id.eleventhtext);
			twelve=(TextView) findViewById(R.id.twelvethtext);
			
			b_one=(Button) findViewById(R.id.done1);
			b_two=(Button) findViewById(R.id.done2);
			b_three=(Button) findViewById(R.id.done3);
			b_four=(Button) findViewById(R.id.done4);
			b_five=(Button) findViewById(R.id.done5);
			b_six=(Button) findViewById(R.id.done6);
			b_seven=(Button) findViewById(R.id.done7);
			b_eight=(Button) findViewById(R.id.done8);
			b_nine=(Button) findViewById(R.id.done9);
			b_ten=(Button) findViewById(R.id.done10);
			b_eleven=(Button) findViewById(R.id.done11);
			
			
			one.setVisibility(View.INVISIBLE);
			two.setVisibility(View.INVISIBLE);
			three.setVisibility(View.INVISIBLE);
			four.setVisibility(View.INVISIBLE);
			five.setVisibility(View.INVISIBLE);
			six.setVisibility(View.INVISIBLE);
			seven.setVisibility(View.INVISIBLE);
			eight.setVisibility(View.INVISIBLE);
			nine.setVisibility(View.INVISIBLE);
			ten.setVisibility(View.INVISIBLE);
			eleven.setVisibility(View.INVISIBLE);
			twelve.setVisibility(View.INVISIBLE);
			
			b_one.setVisibility(View.INVISIBLE);
			b_two.setVisibility(View.INVISIBLE);
			b_three.setVisibility(View.INVISIBLE);
			b_four.setVisibility(View.INVISIBLE);
			b_five.setVisibility(View.INVISIBLE);
			b_six.setVisibility(View.INVISIBLE);
			b_seven.setVisibility(View.INVISIBLE);
			b_eight.setVisibility(View.INVISIBLE);
			b_nine.setVisibility(View.INVISIBLE);
			b_ten.setVisibility(View.INVISIBLE);
			b_eleven.setVisibility(View.INVISIBLE);
			
			
			b_one.setOnClickListener(this); 
			b_two.setOnClickListener(this);b_three.setOnClickListener(this);
			b_four.setOnClickListener(this);b_five.setOnClickListener(this);b_six.setOnClickListener(this);
			b_seven.setOnClickListener(this);b_eight.setOnClickListener(this);b_nine.setOnClickListener(this);
			b_ten.setOnClickListener(this);b_eleven.setOnClickListener(this);
			
			one.setVisibility(View.VISIBLE);
			two.setVisibility(View.VISIBLE);
			b_one.setVisibility(View.VISIBLE);
			
		}	
		home=(ImageButton) findViewById(R.id.imageView2);
		home.setOnClickListener(this);
		
	}
	 protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			finish();
			
		}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if(frompage.equals("fromdashboard"))
		{
		Intent todash=new Intent(Page.this,DashBoard.class);
		startActivity(todash);
		}
		else if(frompage.equals("frominitial"))
		{
			Intent toinit=new Intent(Page.this,InitialPage.class);
			startActivity(toinit);
		}
		else if(frompage.equals("fromstartdate"))
		{
			Intent tosecond=new Intent(Page.this,Startdateandsem.class);
			startActivity(tosecond);
		}
	}
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId())
		{
		case R.id.imageView2:
		if(frompage.equals("fromdashboard"))
		{
		Intent todash=new Intent(Page.this,DashBoard.class);
		startActivity(todash);
		}
		break;
		
		case R.id.done1:
			one.setVisibility(View.INVISIBLE);
			b_one.setVisibility(View.INVISIBLE);
			two.setVisibility(View.INVISIBLE);
			three.setVisibility(View.VISIBLE);
			b_two.setVisibility(View.VISIBLE);
			break;
		case R.id.done2:
			three.setVisibility(View.INVISIBLE);
			b_two.setVisibility(View.INVISIBLE);
			four.setVisibility(View.VISIBLE);
			b_three.setVisibility(View.VISIBLE);
		    break;
		case R.id.done3:
			four.setVisibility(View.INVISIBLE);
			b_three.setVisibility(View.INVISIBLE);
			five.setVisibility(View.VISIBLE);
			b_four.setVisibility(View.VISIBLE);
			break;
		case R.id.done4:
			five.setVisibility(View.INVISIBLE);
			b_four.setVisibility(View.INVISIBLE);
			six.setVisibility(View.VISIBLE);
			b_five.setVisibility(View.VISIBLE);
			break;
		case R.id.done5:
			six.setVisibility(View.INVISIBLE);
			b_five.setVisibility(View.INVISIBLE);
			seven.setVisibility(View.VISIBLE);
			b_six.setVisibility(View.VISIBLE);
			break;
		case R.id.done6:
			seven.setVisibility(View.INVISIBLE);
			b_six.setVisibility(View.INVISIBLE);
			eight.setVisibility(View.VISIBLE);
			b_seven.setVisibility(View.VISIBLE);
			break;
		case R.id.done7:
			eight.setVisibility(View.INVISIBLE);
			b_seven.setVisibility(View.INVISIBLE);
			nine.setVisibility(View.VISIBLE);
			b_eight.setVisibility(View.VISIBLE);
			break;
		case R.id.done8:
			nine.setVisibility(View.INVISIBLE);
			b_eight.setVisibility(View.INVISIBLE);
			ten.setVisibility(View.VISIBLE);
			b_nine.setVisibility(View.VISIBLE);
			break;
		case R.id.done9:
			ten.setVisibility(View.INVISIBLE);
			b_nine.setVisibility(View.INVISIBLE);
			eleven.setVisibility(View.VISIBLE);
			b_ten.setVisibility(View.VISIBLE);
			break;
		case R.id.done10:
			eleven.setVisibility(View.INVISIBLE);
			b_ten.setVisibility(View.INVISIBLE);
			twelve.setVisibility(View.VISIBLE);
			b_eleven.setVisibility(View.VISIBLE);
			break;
		case R.id.done11:
			twelve.setVisibility(View.INVISIBLE);
			b_eleven.setVisibility(View.INVISIBLE);
			one.setVisibility(View.VISIBLE);
			b_one.setVisibility(View.VISIBLE);
			two.setVisibility(View.VISIBLE);
			break;
		}
	}
	 

}
