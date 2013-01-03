package com.bunkie.project;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;


public class ViewTimeTable extends TabActivity{
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewtimetable);
		TabHost tabHost = getTabHost();
        TabHost.TabSpec spec1;
        TabHost.TabSpec spec2;
        TabHost.TabSpec spec3;
        TabHost.TabSpec spec4;
        TabHost.TabSpec spec5;
        
        Intent intent1=new Intent().setClass(this,ViewMonday.class);
        spec1 = tabHost.newTabSpec("monday").setIndicator("Mon",null).setContent(intent1);
        tabHost.addTab(spec1);
        Intent intent2=new Intent().setClass(this,ViewTuesday.class);
        spec2 = tabHost.newTabSpec("tuesday").setIndicator("Tue",null).setContent(intent2);
        tabHost.addTab(spec2);
        Intent intent3=new Intent().setClass(this,ViewWednesday.class);
        spec3 = tabHost.newTabSpec("wednesday").setIndicator("Wed",null).setContent(intent3);
        tabHost.addTab(spec3);
        Intent intent4=new Intent().setClass(this,ViewThursday.class);
        spec4 = tabHost.newTabSpec("thursday").setIndicator("Thu",null).setContent(intent4);
        tabHost.addTab(spec4);
        Intent intent5=new Intent().setClass(this,ViewFriday.class);
        spec5 = tabHost.newTabSpec("friday").setIndicator("Fri",null).setContent(intent5);
        tabHost.addTab(spec5);
                
        
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
		Intent todash=new Intent(ViewTimeTable.this,DashBoard.class);
		startActivity(todash);
	}
	 

	

}
