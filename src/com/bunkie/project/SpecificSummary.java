package com.bunkie.project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SpecificSummary extends Activity implements OnClickListener{
    
	String get_sub;
	String get_count;
	String min_percent;
	String[] seperate_item;
	String[] seperate_sub_count;
	String[] leavedates;
	String temp;
	Calendar endDate;
	Calendar startDate;
	Calendar l_Date;
	ImageButton home;
	String l_result;
	
	int leaveflag=0;
	InitialDatabase id;
	ProgressBar pb;
	   final float[] roundedCorners = new float[] { 5, 5, 5, 5, 5, 5, 5, 5 };
	   ShapeDrawable pgDrawable;
	   String MyColor = "#FF00FF";
	   
	
	float percent;
	
	String get_mon_hours,get_tue_hours,get_wed_hours,get_thu_hours,get_fri_hours;
	String[] get_mon,get_tue,get_wed,get_thu,get_fri;
	
	long mon_sub=0,tue_sub=0,wed_sub=0,thu_sub=0,fri_sub=0;
	long mon=0,tue=0,wed=0,thu=0,fri=0;
	
	long l_mon=0,l_tue=0,l_wed=0,l_thu=0,l_fri=0;
	
	long attended;
	long tot,bunkl,bunktot;
	long min_per;
	
	
	TextView t_bunks;
	TextView t_present;
	TextView t_total;
	TextView t_percent;
	TextView bunkper;
	TextView bunksleft;
	java.util.Date date;
	java.util.Date leaved;
	
	LeaveDatabase ld;
	DataBaseSubjectDredit1 dsc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		id=new InitialDatabase(this);
		dsc=new DataBaseSubjectDredit1(this);
		ld=new LeaveDatabase(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.specificsummary);
		
		pb=(ProgressBar)findViewById(R.id.progressBar1);
		
		pb.setProgressDrawable(getResources().getDrawable(R.drawable.bunkbar));
		
		t_bunks=(TextView) findViewById(R.id.totalbunks);
		t_present=(TextView) findViewById(R.id.totalpresent);
		t_total=(TextView) findViewById(R.id.totalhours);
		t_percent=(TextView) findViewById(R.id.percentage);
		bunkper=(TextView) findViewById(R.id.bunkper);
		bunksleft=(TextView) findViewById(R.id.bunksleft);
		
		home=(ImageButton) findViewById(R.id.imageView2);
		home.setOnClickListener(this);
		
		Bundle get=getIntent().getExtras();
		try
		{
		get_sub=get.getString("SUBJECT");
		get_count=get.getString("COUNT");
		}
		catch(Exception e)
		{
			
		}
		
		t_bunks.setText(get_count);
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
		
		id.open();
		String result=id.getIniData();
		id.close();
		String[] startdatestring=result.split("/");
		
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
		
		attended=((mon-l_mon)*mon_sub) + ((tue-l_tue)*tue_sub) + ((wed-l_wed)*wed_sub) + ((thu-l_thu)*thu_sub) + ((fri-l_fri)*fri_sub);
		try
		{
		percent=100 -((Long.parseLong(get_count)*100)/attended);
		}
		catch(Exception e)
		{
			
		}
		
		
		t_percent.setText(""+percent+"%");
		t_total.setText(""+ attended);
		t_present.setText(""+ (attended - Long.parseLong(get_count)));
		try
		{
		bunkper.setText("Bunk :"+ ((Long.parseLong(get_count)*100)/attended) + " %");
		}
		catch(Exception ex)
		{
			
		}
		
		dsc.open();
		tot=dsc.SubjectTotalClass(get_sub);
		dsc.close();
		
		id.open();
		String get_min_percent=id.getIniData();
		String[] min_percent_arr=get_min_percent.split("/");
		
		min_percent=min_percent_arr[3];
		
		min_per=Long.parseLong(min_percent);
		id.close();
		if(((int)((100-min_per)*tot)/100)-Integer.parseInt(get_count)> 0)
		bunksleft.setText("You can still Bunk "+(((int)((100-min_per)*tot)/100)-Integer.parseInt(get_count)) +" classes");
		else
			bunksleft.setText("You can still Bunk "+"0" +" classes");
		
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

    public long calculate_subject_count(String[] s)
    {
    	long sub_count_day=0;
    	for(int i=0;i<s.length;i++)
		{
    		if(!(s[i].equals("free hour")))
    		{
    			if(get_sub.equals(s[i]))
    				sub_count_day++;
    		}
    			
		}
    	
    	return  sub_count_day;
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
		Intent dbac=new Intent(SpecificSummary.this,FullSummary.class);
		    startActivity(dbac);
		}

public void onClick(View v) {
	// TODO Auto-generated method stub
	Intent todash=new Intent(SpecificSummary.this,DashBoard.class);
	startActivity(todash);
}
}