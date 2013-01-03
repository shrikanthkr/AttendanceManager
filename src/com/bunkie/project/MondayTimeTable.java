package com.bunkie.project;



import java.text.ParseException;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TabHost;
import android.widget.Toast;

public class MondayTimeTable extends TabActivity implements OnClickListener{
    
	
	CheckBox mon_1;
    CheckBox mon_2;
    CheckBox mon_3;
    CheckBox mon_4;
    CheckBox mon_5;
    CheckBox mon_6;
    CheckBox mon_7;
    CheckBox mon_8;
    CheckBox mon_9;
    CheckBox mon_10;
    
    Button monday_done;
    Button monday_delete;
    
    String[] mon_array;
    
    String mon;
    
    String mon_Result="";
    
    String date_res;
    static final int DATE_DIALOG_ID = 0;
    int count=0;
    DateDatabase dd;
    TabHost tabHost;
    
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
    	Bundle receive=getIntent().getExtras();
        date_res=receive.getString("key");
		dd=new DateDatabase(this);
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.monday_timetable);
		tabHost=(TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
		tabHost= getTabHost();
        TabHost.TabSpec spec;

        spec = tabHost.newTabSpec("monday").setIndicator("Monday",null).setContent(R.id.monday_timetable);
        tabHost.addTab(spec);
        
        mon_1=(CheckBox) findViewById(R.id.mon_CheckBox1);
        mon_2=(CheckBox) findViewById(R.id.mon_CheckBox2);
        mon_3=(CheckBox) findViewById(R.id.mon_CheckBox3);
        mon_4=(CheckBox) findViewById(R.id.mon_CheckBox4);
        mon_5=(CheckBox) findViewById(R.id.mon_CheckBox5);
        mon_6=(CheckBox) findViewById(R.id.mon_CheckBox6);
        mon_7=(CheckBox) findViewById(R.id.mon_CheckBox7);
        mon_8=(CheckBox) findViewById(R.id.mon_CheckBox8);
        mon_9=(CheckBox) findViewById(R.id.mon_CheckBox9);
        mon_10=(CheckBox) findViewById(R.id.mon_CheckBox10);
        
        monday_done=(Button) findViewById(R.id.monday_done);
        monday_delete=(Button) findViewById(R.id.monday_delete);
        
        monday_done.setOnClickListener(this);
        monday_delete.setOnClickListener(this);
       
		
        TimeTableDatabase ttd=new TimeTableDatabase(this);
        ttd.open();
        mon=ttd.getMondayPeriod();
        
        ttd.close();
        
        mon_array=mon.split("\n");
        
        
        
        mon_1.setText(mon_array[0]);
        mon_2.setText(mon_array[1]);
        mon_3.setText(mon_array[2]);
        mon_4.setText(mon_array[3]);
        mon_5.setText(mon_array[4]);
        mon_6.setText(mon_array[5]);
        mon_7.setText(mon_array[6]);
        mon_8.setText(mon_array[7]);
        mon_9.setText(mon_array[8]);
        mon_10.setText(mon_array[9]);
        
        if((mon_1.getText().equals("free hour")))
        {
        	mon_1.setClickable(false);
        }
        if((mon_2.getText().equals("free hour")))
        {
        	mon_2.setClickable(false);
        }
        if((mon_3.getText().equals("free hour")))
        {
        	mon_3.setClickable(false);
        }
        if((mon_4.getText().equals("free hour")))
        {
        	mon_4.setClickable(false);
        }
        if((mon_5.getText().equals("free hour")))
        {
        	mon_5.setClickable(false);
        }
        if((mon_6.getText().equals("free hour")))
        {
        	mon_6.setClickable(false);
        }
        if((mon_7.getText().equals("free hour")))
        {
        	mon_7.setClickable(false);
        }
        if((mon_8.getText().equals("free hour")))
        {
        	mon_8.setClickable(false);
        }
        if((mon_9.getText().equals("free hour")))
        {
        	mon_9.setClickable(false);
        }
        if((mon_10.getText().equals("free hour")))
        {
        	mon_10.setClickable(false);
        }
            
	}
	
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId())
		{
		case R.id.monday_done: 
			mon_Result="";
			if(mon_1.isChecked())
			{
				if(!mon_1.getText().equals("free hour"))
				mon_Result=mon_Result+mon_1.getText()+"/"+"1"+"/";
			}
			if(mon_2.isChecked())
			{
				if(!mon_2.getText().equals("free hour"))
				mon_Result=mon_Result+mon_2.getText()+"/"+"2"+"/";
				
			}
				if(mon_3.isChecked())
				{
				    if(!mon_3.getText().equals("free hour"))
					mon_Result=mon_Result+mon_3.getText()+"/"+"3"+"/";
			
				}
				
				if(mon_4.isChecked())
				{
					if(!mon_4.getText().equals("free hour"))
					mon_Result=mon_Result+mon_4.getText()+"/"+"4"+"/";
			
				}
				if(mon_5.isChecked())
				{
				    if(!mon_5.getText().equals("free hour"))
					mon_Result=mon_Result+mon_5.getText()+"/"+"5"+"/";
				}
				if(mon_6.isChecked())
				{
				    if(!mon_6.getText().equals("free hour"))
					mon_Result=mon_Result+mon_6.getText()+"/"+"6"+"/";
				}
			if(mon_7.isChecked())
			{
				if(!mon_7.getText().equals("free hour"))
				mon_Result=mon_Result+mon_7.getText()+"/"+"7"+"/";
			}
		    if(mon_8.isChecked())
		    {
				if(!mon_8.getText().equals("free hour"))
		    	mon_Result=mon_Result+mon_8.getText()+"/"+"8"+"/";
		    }
		    if(mon_9.isChecked())
		    {
				if(!mon_9.getText().equals("free hour"))
		    	mon_Result=mon_Result+mon_9.getText()+"/"+"9"+"/";
		    }
		    if(mon_10.isChecked())
		    {
				if(!mon_10.getText().equals("free hour"))
		    	mon_Result=mon_Result+mon_10.getText()+"/"+"10"+"/";
		    }
		    
		    if(!mon_Result.equals(""))
		    {
		    insertIntoDatabase(mon_Result);
		    Intent todash=new Intent(MondayTimeTable.this,DashBoard.class);
		    startActivity(todash);
		    }
		    else
		    Toast.makeText(this, "Please select a subject and add bunk", 1).show();
		   break;
	
		case R.id.monday_delete:
			String delete_str="";
			if(mon_1.isChecked())
			{
				if(!mon_1.getText().equals("free hour"))
				delete_str=mon_1.getText()+"/"+date_res+"/"+"1";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
				
			}
			
			if(mon_2.isChecked())
			{
				if(!mon_2.getText().equals("free hour"))
				delete_str=mon_2.getText()+"/"+date_res+"/"+"2";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(mon_3.isChecked())
			{
				if(!mon_3.getText().equals("free hour"))
				delete_str=mon_3.getText()+"/"+date_res+"/"+"3";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(mon_4.isChecked())
			{
				if(!mon_4.getText().equals("free hour"))
				delete_str=mon_4.getText()+"/"+date_res+"/"+"4";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(mon_5.isChecked())
			{
				if(!mon_5.getText().equals("free hour"))
				delete_str=mon_5.getText()+"/"+date_res+"/"+"5";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(mon_6.isChecked())
			{
				if(!mon_6.getText().equals("free hour"))
				delete_str=mon_6.getText()+"/"+date_res+"/"+"6";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(mon_7.isChecked())
			{
				if(!mon_7.getText().equals("free hour"))
				delete_str=mon_7.getText()+"/"+date_res+"/"+"7";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(mon_8.isChecked())
			{
				if(!mon_8.getText().equals("free hour"))
				delete_str=mon_8.getText()+"/"+date_res+"/"+"8";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(mon_9.isChecked())
			{
				if(!mon_9.getText().equals("free hour"))
				delete_str=mon_9.getText()+"/"+date_res+"/"+"9";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(mon_10.isChecked())
			{
				if(!mon_10.getText().equals("free hour"))
				delete_str=mon_10.getText()+"/"+date_res+"/"+"10";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
		
		
		}
		
	}
	
	private void insertIntoDatabase(String res) {
		// TODO Auto-generated method stub
		dd.open();
	    try {
			dd.insert(res+date_res);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    dd.close();
	}

	public void deleteFromDatabase(String s)
	{
		dd.open();
		try {
			dd.deleteSpecific(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dd.close();
		
	}
	public boolean onCreateOptionsMenu(Menu datemenu)
	{
		MenuInflater inflate=getMenuInflater();
		inflate.inflate(R.menu.datemenu, datemenu);
	    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		
		switch(item.getItemId())
		{
		    case R.id.changedate:
			Intent mon_dateintent=new Intent(MondayTimeTable.this,DatePick.class);
			startActivity(mon_dateintent);
			break;
		   
		}
		
		return super.onOptionsItemSelected(item);
		
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent dbac=new Intent(MondayTimeTable.this,DatePick.class);
		startActivity(dbac);
		
		}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	

	
}
