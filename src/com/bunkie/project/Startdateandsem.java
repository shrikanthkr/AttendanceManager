package com.bunkie.project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


public class Startdateandsem extends Activity implements OnClickListener{
	
	
	final Calendar c=Calendar.getInstance();
    private int year=c.get(Calendar.YEAR);
    private int month=c.get(Calendar.MONTH);
    private int day=c.get(Calendar.DAY_OF_MONTH);
    EditText start_date;
    EditText sem_num;
    Button next;
    
    String date_res="";
    InitialDatabase id;
	DateDatabase dd;
	TimeTableDatabase ttd;
	static final int DATE_DIALOG_ID = 0;
    java.util.Date date;
    java.util.Date now;
    String daystr,monthstr,yearstr;
    
    String call;
    String user,univ,per;
    String sem_no;
   
    SharedPreferences check;
    SharedPreferences.Editor check_edit;
    
    SharedPreferences sem_n;
	SharedPreferences.Editor edit_sem;
	SharedPreferences userdata;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startdateandsem);
		sem_n=getSharedPreferences("Semester_No",MODE_WORLD_READABLE);
		
		userdata=getSharedPreferences("userdata",MODE_WORLD_READABLE);
		check=getSharedPreferences("check",MODE_WORLD_READABLE);
		
		check_edit=check.edit();
		
		edit_sem=sem_n.edit();
		
		
		user=userdata.getString("username", "a");
		univ=userdata.getString("univname", "saa");
		per=userdata.getString("min_percent", "75");
		
		id=new InitialDatabase(this);
		dd=new DateDatabase(this);
		ttd=new TimeTableDatabase(this);
		next=(Button) findViewById(R.id.button_next);
		start_date=(EditText) findViewById(R.id.startdate);
		sem_num=(EditText) findViewById(R.id.getsem);
		
		DataBaseSubjectDredit1 dsc=new DataBaseSubjectDredit1(Startdateandsem.this);
		try
		{
		id.open();
		id.delete_All();
		id.close();
		
		dsc.open();
		dsc.delete_All();
		dsc.close();
		
		dd.open();
		dd.delete_All();
		dd.close();
		
		ttd.open();
		ttd.delete_All();
		ttd.close();
		
		}
		catch(Exception e)
		{
			
		}
		sem_num.setInputType(InputType.TYPE_CLASS_NUMBER);
		
		if(!start_date.isInEditMode())
			 start_date.setText(date_res);
		 
		start_date.setOnClickListener(this);
		next.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId())
		{
		case R.id.startdate:
			showDialog(DATE_DIALOG_ID);
			break;
		case R.id.button_next:
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
			sem_no= sem_num.getText().toString().trim();
			if(!date_res.equals("") && !user.equals("") && !univ.equals("") && !per.equals("") && !sem_no.equals(""))
			{
			
			try {
				date=dateFormat.parse(date_res);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar today=Calendar.getInstance();
			daystr=""+today.get(Calendar.DAY_OF_MONTH);
			monthstr=""+(today.get(Calendar.MONTH)+1);
			yearstr=""+today.get(Calendar.YEAR);
			
			try {
				now=dateFormat.parse(yearstr+"-"+monthstr+"-"+daystr);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			if(date.compareTo(now)<=0)
			{
			
				id.open();
				try {
					
					id.insert(user+"/"+univ+"/"+date_res+"/"+per);
					edit_sem.putString("sem_number_key", sem_no);
					edit_sem.commit();
					check_edit.putInt("check",1);
					check_edit.commit();
				} catch (SQLiteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				id.close();
				Intent tobunkie=new Intent(Startdateandsem.this,BunkieActivity.class);
				startActivity(tobunkie);
			    finish();

			}
			else
				Toast.makeText(this, "Please Enter Today's Date or Lesser as Starting Date", 1).show();
			}
			else
				Toast.makeText(this, "Please Enter All Details!", 1).show();
			
			break;
		
		}
		
}
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case DATE_DIALOG_ID:
	        return new DatePickerDialog(this,mDateSetListener,year, month, day);
	    }
	    return null;
	}
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int Year, 
                                      int monthOfYear, int dayOfmonth) {
                    year = Year;
                    month = monthOfYear;
                    day = dayOfmonth;
                    updateDisplay();
                }  };
                
                public void updateDisplay()
                {
                	
                	date_res=year+"-"+(month+1)+"-"+day;
                	start_date.setText(date_res);
                	
                	
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
               			b.putString("from", "fromstartdate");
               			Intent toPage=new Intent(Startdateandsem.this,Page.class);
               			toPage.putExtras(b);
               			startActivity(toPage);
    				    finish();

               			break;
               		case R.id.help:
               			Bundle b1=new Bundle();
               			b1.putInt("FLAG", 0);
               			b1.putString("from", "fromstartdate");
               			Intent toPage1=new Intent(Startdateandsem.this,Page.class);
               			toPage1.putExtras(b1);
               			startActivity(toPage1);
    				    finish();

               			break;
               		   
               		}
               		
               		return super.onOptionsItemSelected(item);
               		
               	}
                            
                @Override
				protected void onPause() {
					// TODO Auto-generated method stub
					super.onPause();
					
				}
                

	
}
