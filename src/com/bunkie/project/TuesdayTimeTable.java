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

public class TuesdayTimeTable extends TabActivity implements OnClickListener{
    
	
	CheckBox tue_1;
    CheckBox tue_2;
    CheckBox tue_3;
    CheckBox tue_4;
    CheckBox tue_5;
    CheckBox tue_6;
    CheckBox tue_7;
    CheckBox tue_8;
    CheckBox tue_9;
    CheckBox tue_10;
    
    Button tuesday_done;
    Button tuesday_delete;
    
    String[] tue_array;
    
    String tue;
    
    String tue_Result="";
    
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
		setContentView(R.layout.tuesday_timetable);
		tabHost=(TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
		tabHost= getTabHost();
        TabHost.TabSpec spec;

        spec = tabHost.newTabSpec("tueday").setIndicator("Tuesday",null).setContent(R.id.tuesday_timetable);
        tabHost.addTab(spec);
        
        tue_1=(CheckBox) findViewById(R.id.tue_CheckBox1);
        tue_2=(CheckBox) findViewById(R.id.tue_CheckBox2);
        tue_3=(CheckBox) findViewById(R.id.tue_CheckBox3);
        tue_4=(CheckBox) findViewById(R.id.tue_CheckBox4);
        tue_5=(CheckBox) findViewById(R.id.tue_CheckBox5);
        tue_6=(CheckBox) findViewById(R.id.tue_CheckBox6);
        tue_7=(CheckBox) findViewById(R.id.tue_CheckBox7);
        tue_8=(CheckBox) findViewById(R.id.tue_CheckBox8);
        tue_9=(CheckBox) findViewById(R.id.tue_CheckBox9);
        tue_10=(CheckBox) findViewById(R.id.tue_CheckBox10);
        
        tuesday_done=(Button) findViewById(R.id.tuesday_done);
        tuesday_delete=(Button) findViewById(R.id.tuesday_delete);
        
        tuesday_done.setOnClickListener(this);
        tuesday_delete.setOnClickListener(this);
       
		
        TimeTableDatabase ttd=new TimeTableDatabase(this);
        ttd.open();
        tue=ttd.getTuesdayPeriod();
        
        ttd.close();
        
        tue_array=tue.split("\n");
        
        
        tue_1.setText(tue_array[0]);
        tue_2.setText(tue_array[1]);
        tue_3.setText(tue_array[2]);
        tue_4.setText(tue_array[3]);
        tue_5.setText(tue_array[4]);
        tue_6.setText(tue_array[5]);
        tue_7.setText(tue_array[6]);
        tue_8.setText(tue_array[7]);
        tue_9.setText(tue_array[8]);
        tue_10.setText(tue_array[9]);
        
        if((tue_1.getText().equals("free hour")))
        {
        	tue_1.setClickable(false);
        }
        if((tue_2.getText().equals("free hour")))
        {
        	tue_2.setClickable(false);
        }
        if((tue_3.getText().equals("free hour")))
        {
        	tue_3.setClickable(false);
        }
        if((tue_4.getText().equals("free hour")))
        {
        	tue_4.setClickable(false);
        }
        if((tue_5.getText().equals("free hour")))
        {
        	tue_5.setClickable(false);
        }
        if((tue_6.getText().equals("free hour")))
        {
        	tue_6.setClickable(false);
        }
        if((tue_7.getText().equals("free hour")))
        {
        	tue_7.setClickable(false);
        }
        if((tue_8.getText().equals("free hour")))
        {
        	tue_8.setClickable(false);
        }
        if((tue_9.getText().equals("free hour")))
        {
        	tue_9.setClickable(false);
        }
        if((tue_10.getText().equals("free hour")))
        {
        	tue_10.setClickable(false);
        }
     
     
        
	}
	
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId())
		{
		case R.id.tuesday_done: 
			tue_Result="";
			if(tue_1.isChecked())
			{
				if(!tue_1.getText().equals("free hour"))
				tue_Result=tue_Result+tue_1.getText()+"/"+"1"+"/";
			}
			if(tue_2.isChecked())
			{
				if(!tue_2.getText().equals("free hour"))
				tue_Result=tue_Result+tue_2.getText()+"/"+"2"+"/";
			
			}
			if(tue_3.isChecked())
			{
				if(!tue_3.getText().equals("free hour"))
				tue_Result=tue_Result+tue_3.getText()+"/"+"3"+"/";
			}
			if(tue_4.isChecked())
			{
				if(!tue_4.getText().equals("free hour"))
				tue_Result=tue_Result+tue_4.getText()+"/"+"4"+"/";
			}
			if(tue_5.isChecked())
			{
				if(!tue_5.getText().equals("free hour"))
				tue_Result=tue_Result+tue_5.getText()+"/"+"5"+"/";
			}
			
			if(tue_6.isChecked())
			{
				if(!tue_6.getText().equals("free hour"))
				tue_Result=tue_Result+tue_6.getText()+"/"+"6"+"/";
			}
			if(tue_7.isChecked())
			{
				if(!tue_7.getText().equals("free hour"))
				tue_Result=tue_Result+tue_7.getText()+"/"+"7"+"/";
			}
		    if(tue_8.isChecked())
		    {
		    	if(!tue_8.getText().equals("free hour"))
		    	tue_Result=tue_Result+tue_8.getText()+"/"+"8"+"/";
		    }
		    if(tue_9.isChecked())
		    {
		    	if(!tue_9.getText().equals("free hour"))
		    	tue_Result=tue_Result+tue_9.getText()+"/"+"9"+"/'";
		    }
				if(tue_10.isChecked())
				{
					if(!tue_10.getText().equals("free hour"))
					tue_Result=tue_Result+tue_10.getText()+"/"+"10"+"/";
				}
		    if(!tue_Result.equals(""))
		    {
		    insertIntoDatabase(tue_Result);
		    Intent todash=new Intent(TuesdayTimeTable.this,DashBoard.class);
		    startActivity(todash);
		    }
		    else
		    	Toast.makeText(this, "Please select a subject and add bunk", 2).show();
		    break;
		    
		case R.id.tuesday_delete:
			String delete_str="";
			if(tue_1.isChecked())
			{
				if(!tue_1.getText().equals("free hour"))
				delete_str=tue_1.getText()+"/"+date_res+"/"+"1";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
				
			}
			
			if(tue_2.isChecked())
			{
				if(!tue_2.getText().equals("free hour"))
				delete_str=tue_2.getText()+"/"+date_res+"/"+"2";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(tue_3.isChecked())
			{
				if(!tue_3.getText().equals("free hour"))
				delete_str=tue_3.getText()+"/"+date_res+"/"+"3";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(tue_4.isChecked())
			{
				if(!tue_4.getText().equals("free hour"))
				delete_str=tue_4.getText()+"/"+date_res+"/"+"4";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(tue_5.isChecked())
			{
				if(!tue_5.getText().equals("free hour"))
				delete_str=tue_5.getText()+"/"+date_res+"/"+"5";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(tue_6.isChecked())
			{
				if(!tue_6.getText().equals("free hour"))
				delete_str=tue_6.getText()+"/"+date_res+"/"+"6";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(tue_7.isChecked())
			{
				if(!tue_7.getText().equals("free hour"))
				delete_str=tue_7.getText()+"/"+date_res+"/"+"7";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(tue_8.isChecked())
			{
				if(!tue_8.getText().equals("free hour"))
				delete_str=tue_8.getText()+"/"+date_res+"/"+"8";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(tue_9.isChecked())
			{
				if(!tue_9.getText().equals("free hour"))
				delete_str=tue_9.getText()+"/"+date_res+"/"+"9";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(tue_10.isChecked())
			{
				if(!tue_10.getText().equals("free hour"))
				delete_str=tue_10.getText()+"/"+date_res+"/"+"10";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
	
		
		}
		
	}
	
	private void deleteFromDatabase(String s) {
		// TODO Auto-generated method stub
		dd.open();
		try {
			dd.deleteSpecific(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dd.close();
		
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
			Intent tue_dateintent=new Intent(TuesdayTimeTable.this,DatePick.class);
			startActivity(tue_dateintent);
			break;
		   
		}
		
		return super.onOptionsItemSelected(item);
		
	}
	
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent dbac=new Intent(TuesdayTimeTable.this,DatePick.class);
		startActivity(dbac);
		
		}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	
}

