package com.bunkie.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class Summary extends Activity implements OnClickListener{

	
	
	Button specificSummary,fullSummary;
	ImageButton home;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summary);
		specificSummary=(Button)findViewById(R.id.button1);
		fullSummary=(Button)findViewById(R.id.button2);
		home=(ImageButton) findViewById(R.id.imageView2);
		specificSummary.setOnClickListener(this);
		fullSummary.setOnClickListener(this);
		home.setOnClickListener(this);
		
		
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		
		switch(arg0.getId()){
		
		
		
		case R.id.button1:
			
			Intent toSpecificSummary=new Intent(Summary.this,FullSummary.class);
			startActivity(toSpecificSummary);
			
			break;
		case R.id.button2: 
			Intent toAllSummary=new Intent(Summary.this,AllSummary.class);
			startActivity(toAllSummary);
			break;
			
		case R.id.imageView2:
			Intent todash=new Intent(Summary.this,DashBoard.class);
			startActivity(todash);
		}
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent toDash=new Intent(Summary.this,DashBoard.class);
		startActivity(toDash);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	
	

}
