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

public class WednesdayTimeTable extends TabActivity implements OnClickListener{
    
	
	CheckBox wed_1;
    CheckBox wed_2;
    CheckBox wed_3;
    CheckBox wed_4;
    CheckBox wed_5;
    CheckBox wed_6;
    CheckBox wed_7;
    CheckBox wed_8;
    CheckBox wed_9;
    CheckBox wed_10;
    
    Button wednesday_done;
    Button wednesday_delete;
    
    String[] wed_array;
    
    String wed;
    
    String wed_Result="";
    
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
		setContentView(R.layout.wednesday_timetable);
		tabHost=(TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
		tabHost= getTabHost();
        TabHost.TabSpec spec;

        spec = tabHost.newTabSpec("wednesday").setIndicator("Wednesday",null).setContent(R.id.wednesday_timetable);
        tabHost.addTab(spec);
        
        wed_1=(CheckBox) findViewById(R.id.wed_CheckBox1);
        wed_2=(CheckBox) findViewById(R.id.wed_CheckBox2);
        wed_3=(CheckBox) findViewById(R.id.wed_CheckBox3);
        wed_4=(CheckBox) findViewById(R.id.wed_CheckBox4);
        wed_5=(CheckBox) findViewById(R.id.wed_CheckBox5);
        wed_6=(CheckBox) findViewById(R.id.wed_CheckBox6);
        wed_7=(CheckBox) findViewById(R.id.wed_CheckBox7);
        wed_8=(CheckBox) findViewById(R.id.wed_CheckBox8);
        wed_9=(CheckBox) findViewById(R.id.wed_CheckBox9);
        wed_10=(CheckBox) findViewById(R.id.wed_CheckBox10);
        
        wednesday_done=(Button) findViewById(R.id.wednesday_done);
        wednesday_delete=(Button) findViewById(R.id.wednesday_delete);
        
        wednesday_done.setOnClickListener(this);
        wednesday_delete.setOnClickListener(this);
		
        TimeTableDatabase ttd=new TimeTableDatabase(this);
        ttd.open();
        wed=ttd.getWednesdayPeriod();
        
        ttd.close();
        
        wed_array=wed.split("\n");
        
        
        wed_1.setText(wed_array[0]);
        wed_2.setText(wed_array[1]);
        wed_3.setText(wed_array[2]);
        wed_4.setText(wed_array[3]);
        wed_5.setText(wed_array[4]);
        wed_6.setText(wed_array[5]);
        wed_7.setText(wed_array[6]);
        wed_8.setText(wed_array[7]);
        wed_9.setText(wed_array[8]);
        wed_10.setText(wed_array[9]);
        
        if((wed_1.getText().equals("free hour")))
        {
        	wed_1.setClickable(false);
        }
        if((wed_2.getText().equals("free hour")))
        {
        	wed_2.setClickable(false);
        }
        if((wed_3.getText().equals("free hour")))
        {
        	wed_3.setClickable(false);
        }
        if((wed_4.getText().equals("free hour")))
        {
        	wed_4.setClickable(false);
        }
        if((wed_5.getText().equals("free hour")))
        {
        	wed_5.setClickable(false);
        }
        if((wed_6.getText().equals("free hour")))
        {
        	wed_6.setClickable(false);
        }
        if((wed_7.getText().equals("free hour")))
        {
        	wed_7.setClickable(false);
        }
        if((wed_8.getText().equals("free hour")))
        {
        	wed_8.setClickable(false);
        }
        if((wed_9.getText().equals("free hour")))
        {
        	wed_9.setClickable(false);
        }
        if((wed_10.getText().equals("free hour")))
        {
        	wed_10.setClickable(false);
        }
     
        
	}
	
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId())
		{
		case R.id.wednesday_done: 
			wed_Result="";
			if(wed_1.isChecked())
			{
				if(!wed_1.getText().equals("free hour"))
				wed_Result=wed_Result+wed_1.getText()+"/"+"1"+"/";
			}
			if(wed_2.isChecked())
			{
				if(!wed_2.getText().equals("free hour"))
				wed_Result=wed_Result+wed_2.getText()+"/"+"2"+"/";
			
			}
			if(wed_3.isChecked())
			{
				if(!wed_3.getText().equals("free hour"))
				wed_Result=wed_Result+wed_3.getText()+"/"+"3"+"/";
			}
			if(wed_4.isChecked())
			{
				if(!wed_4.getText().equals("free hour"))
				wed_Result=wed_Result+wed_4.getText()+"/"+"4"+"/";
			}
			if(wed_5.isChecked())
			{
				if(!wed_5.getText().equals("free hour"))
				wed_Result=wed_Result+wed_5.getText()+"/"+"5"+"/";
			}
			
			if(wed_6.isChecked())
			{
				if(!wed_6.getText().equals("free hour"))
				wed_Result=wed_Result+wed_6.getText()+"/"+"6"+"/";
			}
			if(wed_7.isChecked())
			{
				if(!wed_7.getText().equals("free hour"))
				wed_Result=wed_Result+wed_7.getText()+"/"+"7"+"/";
			}
		    if(wed_8.isChecked())
		    {
		    	if(!wed_8.getText().equals("free hour"))
		    	wed_Result=wed_Result+wed_8.getText()+"/"+"8"+"/";
		    }
		    if(wed_9.isChecked())
		    {
		    	if(!wed_9.getText().equals("free hour"))
		    	wed_Result=wed_Result+wed_9.getText()+"/"+"9"+"/'";
		    }
				if(wed_10.isChecked())
				{
					if(!wed_10.getText().equals("free hour"))
					wed_Result=wed_Result+wed_10.getText()+"/"+"10"+"/";
				}
		
		    if(!wed_Result.equals(""))
		    {
		    insertIntoDatabase(wed_Result);
		    Intent todash=new Intent(WednesdayTimeTable.this,DashBoard.class);
		    startActivity(todash);
		    }
		    else
		    	Toast.makeText(this, "Please select a subject and add bunk", 2).show();
		    break;
		    
		case R.id.wednesday_delete:
			String delete_str="";
			if(wed_1.isChecked())
			{
				if(!wed_1.getText().equals("free hour"))
				delete_str=wed_1.getText()+"/"+date_res+"/"+"1";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
				
			}
			
			if(wed_2.isChecked())
			{
				if(!wed_2.getText().equals("free hour"))
				delete_str=wed_2.getText()+"/"+date_res+"/"+"2";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(wed_3.isChecked())
			{
				if(!wed_3.getText().equals("free hour"))
				delete_str=wed_3.getText()+"/"+date_res+"/"+"3";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(wed_4.isChecked())
			{
				if(!wed_4.getText().equals("free hour"))
				delete_str=wed_4.getText()+"/"+date_res+"/"+"4";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(wed_5.isChecked())
			{
				if(!wed_5.getText().equals("free hour"))
				delete_str=wed_5.getText()+"/"+date_res+"/"+"5";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(wed_6.isChecked())
			{
				if(!wed_6.getText().equals("free hour"))
				delete_str=wed_6.getText()+"/"+date_res+"/"+"6";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(wed_7.isChecked())
			{
				if(!wed_7.getText().equals("free hour"))
				delete_str=wed_7.getText()+"/"+date_res+"/"+"7";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(wed_8.isChecked())
			{
				if(!wed_8.getText().equals("free hour"))
				delete_str=wed_8.getText()+"/"+date_res+"/"+"8";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(wed_9.isChecked())
			{
				if(!wed_9.getText().equals("free hour"))
				delete_str=wed_9.getText()+"/"+date_res+"/"+"9";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(wed_10.isChecked())
			{
				if(!wed_10.getText().equals("free hour"))
				delete_str=wed_10.getText()+"/"+date_res+"/"+"10";
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
			Intent wed_dateintent=new Intent(WednesdayTimeTable.this,DatePick.class);
			startActivity(wed_dateintent);
			break;
		   
		}
		
		return super.onOptionsItemSelected(item);
		
	}
	
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent dbac=new Intent(WednesdayTimeTable.this,DatePick.class);
		startActivity(dbac);
		
		}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	
}

