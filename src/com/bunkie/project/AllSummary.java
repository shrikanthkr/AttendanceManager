package com.bunkie.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class AllSummary extends Activity{
	
	
	TableLayout tl;
	DateDatabase dd; 
	String sub,date,hour;
	String[] subarray,datearray,hourarray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainsummarypage);
		tl=(TableLayout) findViewById(R.id.fulltable);
	dd=new DateDatabase(this);
	dd.open();
	date=dd.getDate();
	hour=dd.getHour();
	sub=dd.getSubject();
	dd.close();
	try
	{
	settingTheColumns();
	}
	catch(Exception ex)
	{
		
	}
		
		
	}

	private void settingTheColumns() {
		// TODO Auto-generated method stub
		subarray=sub.split("/");
		datearray=date.split("/");
		hourarray=hour.split("/");
		
		android.view.Display display=((android.view.WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		
		for(int i=0;i<subarray.length;i++){
		TableRow tr=new TableRow(this);
		tr.setLayoutParams(new LayoutParams (LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		TextView tv1=new TextView(this);
		TextView tv2=new TextView(this);
		TextView tv3=new TextView(this);
		tv1.setWidth(display.getWidth()/3);
		tv2.setWidth(display.getWidth()/3);
		tv3.setWidth(display.getWidth()/3);
		tv1.setTextSize(20);
		tv1.setTextColor(Color.BLACK);
		tv1.setText("  "+subarray[i]);
		tr.addView(tv1);
		tv2.setTextSize(20);
		tv2.setTextColor(Color.BLACK);
		tv2.setText(reverseme(datearray[i]));
		tr.addView(tv2);
		tv3.setTextSize(20);
		tv3.setTextColor(Color.BLACK);
		tv3.setText("\t"+hourarray[i]);
		tr.addView(tv3);
		
		tl.addView(tr, new TableLayout.LayoutParams(new LayoutParams (LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)));
		}
		
		
		
		
	}
	
       private String reverseme(String sub){
		
		
    	   String result="";
    	   
    	   String[] resarray=sub.split("-");
    	  
    	   result=resarray[2]+"-"+resarray[1]+"-"+resarray[0];
    	   return result;
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent todash=new Intent(AllSummary.this,Summary.class);
		startActivity(todash);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
       
       
	
}
