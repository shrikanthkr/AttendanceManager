package com.bunkie.project;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;



public class Alerts extends Activity implements OnClickListener{

	ImageView good;
	ImageView awesome;
	ImageView danger;
	
	TextView overall_classes;
	TextView overall_attended;
	TextView overall_bunks;
	TextView overall_attendance;
	
	TextView danger_text;
	TextView head;
	TextView bunkper;
	InitialDatabase ind;
	DataBaseSubjectDredit1 dsc;
	DateDatabase dd;
	
	ImageButton home;
	String min_percent;
	
	long f_min_percent;
	long l_total_classes;
	long l_classes;
	long total_bunks;
	long percent;
	
	ProgressBar pb;
	
	java.util.Date date;
	String get_mon_hours,get_tue_hours,get_wed_hours,get_thu_hours,get_fri_hours;
	String[] get_mon,get_tue,get_wed,get_thu,get_fri;
	
	long mon_sub=0,tue_sub=0,wed_sub=0,thu_sub=0,fri_sub=0;
	long mon=0,tue=0,wed=0,thu=0,fri=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alerts);
		
		ind=new InitialDatabase(this);
		dsc=new DataBaseSubjectDredit1(this);
		dd=new DateDatabase(this);
		
		pb=(ProgressBar)findViewById(R.id.overallprogress);
		pb.setProgressDrawable(getResources().getDrawable(R.drawable.bunkbar));
		
		
		danger=(ImageView) findViewById(R.id.danger);
		home=(ImageButton) findViewById(R.id.imageView2);
		home.setOnClickListener(this);
		
		overall_classes=(TextView) findViewById(R.id.overallclasses);
		overall_attended=(TextView) findViewById(R.id.overallattended);
		overall_bunks=(TextView) findViewById(R.id.overallbunks);
		overall_attendance=(TextView) findViewById(R.id.overallattendance);
		bunkper=(TextView) findViewById(R.id.bunkper);
		
		
		danger.setVisibility(View.INVISIBLE);
		
		Bundle getfromdash=getIntent().getExtras();
	    l_classes=Long.parseLong(getfromdash.getString("l_classes").trim());
	    total_bunks=Long.parseLong(getfromdash.getString("total_bunks").trim());
	    percent=Long.parseLong(getfromdash.getString("percent").trim());
	    min_percent=getfromdash.getString("min_percent").trim();
	    
		overall_classes.setText(""+l_classes);
        overall_attended.setText(""+ (l_classes-total_bunks));
        overall_bunks.setText(""+total_bunks);
        overall_attendance.setText(""+percent+"%");
        
        Thread t1=new Thread(){
			int currentPosition= 0;
			
			public void run()
				{
					
						
				         while (currentPosition<(int) percent) {
				        	
				            try {
				                Thread.sleep(5);
				                currentPosition++;
				            } catch (InterruptedException e) {
				                return;
				            } catch (Exception e) {
				                return;
				            }finally{
				            
				            pb.setProgress(currentPosition);
				            }
				        }
				        
				        	
			}
			};
			t1.start();
			if(percent<Long.parseLong(min_percent))
       {
    	   danger.setVisibility(View.VISIBLE);
    	  
    	   Bundle b=new Bundle();	
		   b.putString("overall", ""+percent);
		   b.putString("totalbunks", ""+total_bunks);
    	   Calendar cal=Calendar.getInstance();
    	   Intent alarm=new Intent(this,NotificationReceiver.class);	
    	   alarm.putExtras(b);
    	   PendingIntent pendingIntent = PendingIntent.getBroadcast(this,(int) ((int)percent*1000+total_bunks), alarm,0);
		   AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		   alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),pendingIntent);
       }
    	 
      
       try
		{
		bunkper.setText((percent) + " %");
		}
		catch(Exception ex)
		{
			
		}
	}
	
	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
		
	}
	@Override
	public void onBackPressed() {
	// TODO Auto-generated method stub
	super.onBackPressed();
	Intent todash=new Intent(Alerts.this,DashBoard.class);
	startActivity(todash);
	}



	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent todash=new Intent(Alerts.this,DashBoard.class);
		startActivity(todash);
		
	}

}
