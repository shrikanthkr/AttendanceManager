package com.bunkie.project;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;




public class DashBoard extends Activity implements OnClickListener{
	ImageButton home;
	ImageButton add;
	ImageButton list;
	ImageButton startnew;
	ImageButton hall;
	ImageButton alert;
	ImageButton help;
	
	String[] rearray;
	DateDatabase dd;
	DataBaseSubjectDredit1 ds;
	InitialDatabase ind;
	TimeTableDatabase ttd;
	SharedPreferences notificationset;
	SharedPreferences.Editor notedit;
	SharedPreferences mon_subjects;
	SharedPreferences.Editor mon_edit;
	SharedPreferences tue_subjects;
	SharedPreferences.Editor tue_edit;
	SharedPreferences wed_subjects;
	SharedPreferences.Editor wed_edit;
	SharedPreferences thu_subjects;
    SharedPreferences.Editor thu_edit;
    SharedPreferences fri_subjects;
    SharedPreferences.Editor fri_edit; 
    SharedPreferences bunk;
	SharedPreferences.Editor editor;
	
	Thread t1,t2,t3;
	 
	String min_percent;
	
	long f_min_percent;
	long l_total_classes;
	long l_classes;
	long total_bunks;
	long percent;
	
	Calendar endDate;
	Calendar startDate;
	Calendar cal;
	java.util.Date date;
	String get_mon_hours,get_tue_hours,get_wed_hours,get_thu_hours,get_fri_hours;
	String[] get_mon,get_tue,get_wed,get_thu,get_fri;
	
	long mon_sub=0,tue_sub=0,wed_sub=0,thu_sub=0,fri_sub=0;
	long mon=0,tue=0,wed=0,thu=0,fri=0;
	LeaveDatabase ld;
	int not_int;
	Calendar l_Date;
	String l_result;
	long l_mon=0,l_tue=0,l_wed=0,l_thu=0,l_fri=0;
	String[] leavedates;
	java.util.Date leaved;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		notificationset=getSharedPreferences("notificationset",MODE_WORLD_READABLE);
		notedit=notificationset.edit();
		mon_subjects=getSharedPreferences("monday",MODE_WORLD_READABLE);
		mon_edit=mon_subjects.edit();
		tue_subjects=getSharedPreferences("tuesday",MODE_WORLD_READABLE);
		tue_edit=tue_subjects.edit();
		wed_subjects=getSharedPreferences("wednesday",MODE_WORLD_READABLE);
		wed_edit=wed_subjects.edit();
		thu_subjects=getSharedPreferences("thursday",MODE_WORLD_READABLE);
		thu_edit=thu_subjects.edit();
		fri_subjects=getSharedPreferences("friday",MODE_WORLD_READABLE);
		fri_edit=fri_subjects.edit();
		
		not_int=notificationset.getInt("notify", 0);
		
		dd=new DateDatabase(this);
		ds=new DataBaseSubjectDredit1(this);
		ind=new InitialDatabase(this);
		ttd=new TimeTableDatabase(this);
		ld=new LeaveDatabase(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		home=(ImageButton) findViewById(R.id.imageView2);
		add=(ImageButton) findViewById(R.id.addbutton);
		list=(ImageButton) findViewById(R.id.listbutton);
		startnew=(ImageButton) findViewById(R.id.startnewbutton);
		hall=(ImageButton) findViewById(R.id.hallbutton);
		alert=(ImageButton) findViewById(R.id.alertbutton);
		help=(ImageButton) findViewById(R.id.viewtimetablebutton);
		home.setOnClickListener(this);
		add.setOnClickListener(this);
		list.setOnClickListener(this);
		startnew.setOnClickListener(this);
		hall.setOnClickListener(this);
		alert.setOnClickListener(this);
		help.setOnClickListener(this);
		
		CalculateAll();
		
		}

	private void CalculateAll() {
		// TODO Auto-generated method stub
		ind.open();
		String getfrominit=ind.getIniData();
		String[] getfrominitarray=getfrominit.split("/");
		
		min_percent=getfrominitarray[3];
		f_min_percent=Long.parseLong(min_percent);
		
		ind.close();
		
		ds.open();
		l_total_classes= ds.AllSubjectsTotalClass();
		ds.close();
		
		dd.open();
		String getc=dd.getCount();
		String[] getc_array=getc.split("/");
		
		for(int i=0;i<getc_array.length;i++)
		{
			try
			{
			total_bunks=total_bunks+Long.parseLong(getc_array[i]);
			}
			catch(Exception e)
			{ }
		}
		
		dd.close();
        
		TimeTableDatabase ttd=new TimeTableDatabase(this);
		ttd.open();
		get_mon_hours=ttd.getMondayPeriod();
		get_mon=get_mon_hours.split("\n");
		
		mon_sub=calculate_subject_count(get_mon);
		
		get_tue_hours=ttd.getTuesdayPeriod();
		get_tue=get_tue_hours.split("\n");
		
		tue_sub=calculate_subject_count(get_tue);
		
		get_wed_hours=ttd.getWednesdayPeriod();
		get_wed=get_wed_hours.split("\n");
		
		wed_sub=calculate_subject_count(get_wed);
		
		get_thu_hours=ttd.getThursdayPeriod();
		get_thu=get_thu_hours.split("\n");
		
		thu_sub=calculate_subject_count(get_thu);
		
		get_fri_hours=ttd.getFridayPeriod();
		get_fri=get_fri_hours.split("\n");
		
		fri_sub=calculate_subject_count(get_fri);
		
		ttd.close();
		
		ttd.close();
		ld.open();
		leavedates=ld.getDate();
		ld.close();
		
		l_Date=Calendar.getInstance();
		for(int i=0;i<leavedates.length;i++)
		{
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			leaved=dateFormat1.parse(leavedates[i]);
			l_result=dateFormat1.format(leaved);
			String[] l_array=l_result.split("-");
			l_Date.set(Integer.parseInt(l_array[0]), Integer.parseInt(l_array[1])-1, Integer.parseInt(l_array[2]));
			Calculateleavedate(l_Date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
		}

		
		
		
		ind.open();
		String result=ind.getIniData();
	    String[] startdatestring=result.split("/");
		ind.close();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			date=dateFormat.parse(startdatestring[2]);
			result=dateFormat.format(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String[] splitresult=result.split("-");
		
		Calendar startDate=Calendar.getInstance();
		startDate.set(Integer.parseInt(splitresult[0]),(Integer.parseInt(splitresult[1])-1),Integer.parseInt(splitresult[2]));
		Calendar endDate=Calendar.getInstance();
	    daysBetween(startDate,endDate);
	    l_classes=((mon-l_mon)*mon_sub) + ((tue-l_tue)*tue_sub) + ((wed-l_wed)*wed_sub) + ((thu-l_thu)*thu_sub) + ((fri-l_fri)*fri_sub);
	    
	    try
	    {
	    percent=((l_classes-total_bunks)*100)/l_classes;
	    }
	    catch(Exception ex)
	    { }
	    
	    if(percent<75){
	    	
	    	    
    		    if(not_int!=Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
    		    {
    		    Bundle b=new Bundle();	
    		    b.putLong("overall", percent);
    		    b.putLong("totalbunks", total_bunks);
    		    Intent alarm=new Intent(this,NotificationReceiver.class);
	    	    alarm.putExtras(b);
			    cal=Calendar.getInstance();
			    cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH), 19, 31);
			    PendingIntent pendingIntent = PendingIntent.getBroadcast(this,(int) ((int)percent*1000+total_bunks), alarm,0);
			    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
			    alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),pendingIntent);
    		    notedit.putInt("notify", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    		    notedit.commit();
    		    }
			    
			  }

	}

	public  long daysBetween(Calendar startDate, Calendar endDate) {
		  Calendar date = (Calendar) startDate.clone();
		  long daysBetween = 0;
		  while (date.before(endDate) || date.equals(endDate)) {
		       
			  if(!(date.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY))
		      {
		    	  if(!(date.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY))
		    	  {
		    		  if(date.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY)
		    		   mon++;
		    		  else if(date.get(Calendar.DAY_OF_WEEK)==Calendar.TUESDAY)
		    		   tue++;
		    	      else if(date.get(Calendar.DAY_OF_WEEK)==Calendar.WEDNESDAY)
		    		    wed++;
		    		  else if(date.get(Calendar.DAY_OF_WEEK)==Calendar.THURSDAY)
		    		    thu++;
		    		  else if(date.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
		    			fri++;
		    		  
		    		  daysBetween++;
		    	  }
		    		  
		 
		      }
		      date.add(Calendar.DAY_OF_MONTH, 1);

		  
		}
		 
		  return daysBetween;

	}


	public void Calculateleavedate(Calendar leavedate)
	{
		  
		  if(leavedate.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY)
		   l_mon++;
		  else if(leavedate.get(Calendar.DAY_OF_WEEK)==Calendar.TUESDAY)
		   l_tue++;
	      else if(leavedate.get(Calendar.DAY_OF_WEEK)==Calendar.WEDNESDAY)
		    l_wed++;
		  else if(leavedate.get(Calendar.DAY_OF_WEEK)==Calendar.THURSDAY)
		    l_thu++;
		  else if(leavedate.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
			l_fri++;
	}

	private long calculate_subject_count(String[] s) {
		// TODO Auto-generated method stub
		long sub_count_day=0;
    	for(int i=0;i<s.length;i++)
		{
    		if(!(s[i].equals("free hour")))
    		{
    			sub_count_day++;
    		}
    			
		}
    	
    	return  sub_count_day;
	}

	public void onClick(View arg0) {

		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.imageView2: 
			
			break;
		
		case R.id.addbutton: 
			Intent todate=new Intent(DashBoard.this,DatePick.class);
			startActivity(todate);
			break;
		case R.id.listbutton: 
			Intent tolist=new Intent(DashBoard.this,Summary.class);
			startActivity(tolist);
			break;
		case R.id.startnewbutton: 
				  
				 bunk=getSharedPreferences("bunkie",MODE_WORLD_READABLE);
				 editor=bunk.edit();
			   ind.open();
			   String res=ind.getIniData();
			   rearray=res.split("/");
			   ind.close();
			   AlertDialog.Builder builder = new AlertDialog.Builder(this);
	  		   
	      	   builder.setMessage("Are you sure ?");
	      	   builder.setCancelable(true);
	      	          builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	      	             public void onClick(DialogInterface dialog, int id) {
	      	            	
	      	            t1=new Thread(){
	      	            public void run()
	      	            {
	      	             dd.open();
	      	  			 dd.delete_All();
	      	  			 dd.close();
	      	  			 ds.open();
	      	  			 ds.delete_All();
	      	  			 ds.close();
	      	  			 ind.open();
	      	  			 ind.delete_All();
	      	  			 ind.close();
	      	  			 ttd.open();
	      	  			 ttd.delete_All();
	      	  			 ttd.close(); 
	      	  			 ld.open();
	      	  			 ld.delete_All();
	      	  			 ld.close();
	      	           }};
	      	          t2=new Thread()
	      	         {
	      	        	 
	      	        	 public void run()
	      	        	 {
	      				 mon_edit.putString("monday", "free hour"+"/"+"free hour"+"/"+"free hour"+"	/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour");
	      				 mon_edit.commit();
	      				 tue_edit.putString("tuesday", "free hour"+"/"+"free hour"+"/"+"free hour"+ "/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour");
	      				 tue_edit.commit();
	      				 wed_edit.putString("wednesday", "free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour");
	      				 wed_edit.commit();
	      				 thu_edit.putString("thursday", "free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour");
	      				 thu_edit.commit();
	      				 fri_edit.putString("friday", "free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour");
	      				 fri_edit.commit();
	      	        	}};
	      	        	t3=new Thread()
	      	        	{
	      	        		public void run()
	      	        		{
	      	        	
	      	        	boolean val=bunk.getBoolean("first", false);
	      	          
	      				 if(!val)
	      				 {
	      					 editor.putBoolean("first", true);
	      			         editor.commit();
	      			         t1.start();
    			             t2.start();
	      			         Intent toinit=new Intent(DashBoard.this,Startdateandsem.class);
	      			         startActivity(toinit);
	      				 }
	      				 
	      	        		}};
	      				    
	      	        		dialog.cancel();
	      	        	 	t3.start();

	      	        		
	      	        			      	        		
	      	              }
	      	             });
	      	        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
	    	              public void onClick(DialogInterface dialog, int id) {
	    	            	dialog.cancel();
	    	              }
	    	            });
	      	          builder.show();
			
			 
			 
			 
			break;
		case R.id.hallbutton: 
			Bundle b_tohall=new Bundle();
			b_tohall.putString("l_classes", ""+l_classes);
			b_tohall.putString("total_bunks", ""+total_bunks);
			b_tohall.putString("percent", ""+percent);
			b_tohall.putString("min_percent", min_percent);
			Intent tohall=new Intent(DashBoard.this,HallofFame.class);
			tohall.putExtras(b_tohall);
			startActivity(tohall);
			break;
		case R.id.alertbutton: 
			
			Bundle b_toalert=new Bundle();
			b_toalert.putString("l_classes", ""+l_classes);
			b_toalert.putString("total_bunks", ""+total_bunks);
			b_toalert.putString("percent", ""+percent);
			b_toalert.putString("min_percent", min_percent);
			Intent toalert=new Intent(DashBoard.this,Alerts.class);
			toalert.putExtras(b_toalert);
			startActivity(toalert);
			break;
		case R.id.viewtimetablebutton:
			Intent totimetable=new Intent(DashBoard.this,ViewTimeTable.class);
			startActivity(totimetable);
			break;
		
		
		}
		
	}
	
	
	public boolean onCreateOptionsMenu(Menu dashboardmenu)
	{
		MenuInflater inflate=getMenuInflater();
		inflate.inflate(R.menu.dashboardmenu, dashboardmenu);
	    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		
		switch(item.getItemId())
		{
		case R.id.about:
			Bundle b=new Bundle();
			b.putInt("FLAG", 1);
			b.putString("from", "fromdashboard");
			Intent toPage=new Intent(DashBoard.this,Page.class);
			toPage.putExtras(b);
			startActivity(toPage);
			break;
		case R.id.help:
			Bundle b1=new Bundle();
			b1.putInt("FLAG", 0);
			b1.putString("from", "fromdashboard");
		Intent toPage1=new Intent(DashBoard.this,Page.class);
		toPage1.putExtras(b1);
		startActivity(toPage1);
		break;
		   
		}
		
		return super.onOptionsItemSelected(item);
		
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
		onDestroy();
	}
	 

	

}
