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

public class ThursdayTimeTable extends TabActivity implements OnClickListener{
    
	
	CheckBox thu_1;
    CheckBox thu_2;
    CheckBox thu_3;
    CheckBox thu_4;
    CheckBox thu_5;
    CheckBox thu_6;
    CheckBox thu_7;
    CheckBox thu_8;
    CheckBox thu_9;
    CheckBox thu_10;
    
    Button thursday_done;
    Button thursday_delete;
    String[] thu_array;
    
    String thu;
    
    String thu_Result="";
    
    
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
		setContentView(R.layout.thursday_timetable);
		tabHost=(TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
		tabHost= getTabHost();
        TabHost.TabSpec spec;

        spec = tabHost.newTabSpec("thursday").setIndicator("Thursday",null).setContent(R.id.thursday_timetable);
        tabHost.addTab(spec);
        
        thu_1=(CheckBox) findViewById(R.id.thu_CheckBox1);
        thu_2=(CheckBox) findViewById(R.id.thu_CheckBox2);
        thu_3=(CheckBox) findViewById(R.id.thu_CheckBox3);
        thu_4=(CheckBox) findViewById(R.id.thu_CheckBox4);
        thu_5=(CheckBox) findViewById(R.id.thu_CheckBox5);
        thu_6=(CheckBox) findViewById(R.id.thu_CheckBox6);
        thu_7=(CheckBox) findViewById(R.id.thu_CheckBox7);
        thu_8=(CheckBox) findViewById(R.id.thu_CheckBox8);
        thu_9=(CheckBox) findViewById(R.id.thu_CheckBox9);
        thu_10=(CheckBox) findViewById(R.id.thu_CheckBox10);
        
        thursday_done=(Button) findViewById(R.id.thursday_done);
        thursday_delete=(Button) findViewById(R.id.thursday_delete);
        thursday_done.setOnClickListener(this);
        thursday_delete.setOnClickListener(this);
       
		
        TimeTableDatabase ttd=new TimeTableDatabase(this);
        ttd.open();
        thu=ttd.getThursdayPeriod();
        
        ttd.close();
        
        thu_array=thu.split("\n");
        
        
        thu_1.setText(thu_array[0]);
        thu_2.setText(thu_array[1]);
        thu_3.setText(thu_array[2]);
        thu_4.setText(thu_array[3]);
        thu_5.setText(thu_array[4]);
        thu_6.setText(thu_array[5]);
        thu_7.setText(thu_array[6]);
        thu_8.setText(thu_array[7]);
        thu_9.setText(thu_array[8]);
        thu_10.setText(thu_array[9]);
     
        if((thu_1.getText().equals("free hour")))
        {
        	thu_1.setClickable(false);
        }
        if((thu_2.getText().equals("free hour")))
        {
        	thu_2.setClickable(false);
        }
        if((thu_3.getText().equals("free hour")))
        {
        	thu_3.setClickable(false);
        }
        if((thu_4.getText().equals("free hour")))
        {
        	thu_4.setClickable(false);
        }
        if((thu_5.getText().equals("free hour")))
        {
        	thu_5.setClickable(false);
        }
        if((thu_6.getText().equals("free hour")))
        {
        	thu_6.setClickable(false);
        }
        if((thu_7.getText().equals("free hour")))
        {
        	thu_7.setClickable(false);
        }
        if((thu_8.getText().equals("free hour")))
        {
        	thu_8.setClickable(false);
        }
        if((thu_9.getText().equals("free hour")))
        {
        	thu_9.setClickable(false);
        }
        if((thu_10.getText().equals("free hour")))
        {
        	thu_10.setClickable(false);
        }
    
        
	}
	
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId())
		{
		case R.id.thursday_done: 
			thu_Result="";
			if(thu_1.isChecked())
			{
				if(!thu_1.getText().equals("free hour"))
				thu_Result=thu_Result+thu_1.getText()+"/"+"1"+"/";
			}
			if(thu_2.isChecked())
			{
				if(!thu_2.getText().equals("free hour"))
				thu_Result=thu_Result+thu_2.getText()+"/"+"2"+"/";
			
			}
			if(thu_3.isChecked())
			{
				if(!thu_3.getText().equals("free hour"))
				thu_Result=thu_Result+thu_3.getText()+"/"+"3"+"/";
			}
			if(thu_4.isChecked())
			{
				if(!thu_4.getText().equals("free hour"))
				thu_Result=thu_Result+thu_4.getText()+"/"+"4"+"/";
			}
			if(thu_5.isChecked())
			{
				if(!thu_5.getText().equals("free hour"))
				thu_Result=thu_Result+thu_5.getText()+"/"+"5"+"/";
			}
			
			if(thu_6.isChecked())
			{
				if(!thu_6.getText().equals("free hour"))
				thu_Result=thu_Result+thu_6.getText()+"/"+"6"+"/";
			}
			if(thu_7.isChecked())
			{
				if(!thu_7.getText().equals("free hour"))
				thu_Result=thu_Result+thu_7.getText()+"/"+"7"+"/";
			}
		    if(thu_8.isChecked())
		    {
		    	if(!thu_8.getText().equals("free hour"))
		    	thu_Result=thu_Result+thu_8.getText()+"/"+"8"+"/";
		    }
		    if(thu_9.isChecked())
		    {
		    	if(!thu_9.getText().equals("free hour"))
		    	thu_Result=thu_Result+thu_9.getText()+"/"+"9"+"/'";
		    }
				if(thu_10.isChecked())
				{
					if(!thu_10.getText().equals("free hour"))
					thu_Result=thu_Result+thu_10.getText()+"/"+"10"+"/";
				}
	
				 if(!thu_Result.equals(""))
				 {
					    insertIntoDatabase(thu_Result);
					    Intent todash=new Intent(ThursdayTimeTable.this,DashBoard.class);
					    startActivity(todash);
				 }
			    else
 			   Toast.makeText(this, "Please select a subject and add bunk", 2).show();
		    break;
		    
		case R.id.thursday_delete:
			String delete_str="";
			if(thu_1.isChecked())
			{
				if(!thu_1.getText().equals("free hour"))
				delete_str=thu_1.getText()+"/"+date_res+"/"+"1";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
				
			}
			
			if(thu_2.isChecked())
			{
				if(!thu_2.getText().equals("free hour"))
				delete_str=thu_2.getText()+"/"+date_res+"/"+"2";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(thu_3.isChecked())
			{
				if(!thu_3.getText().equals("free hour"))
				delete_str=thu_3.getText()+"/"+date_res+"/"+"3";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(thu_4.isChecked())
			{
				if(!thu_4.getText().equals("free hour"))
				delete_str=thu_4.getText()+"/"+date_res+"/"+"4";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(thu_5.isChecked())
			{
				if(!thu_5.getText().equals("free hour"))
				delete_str=thu_5.getText()+"/"+date_res+"/"+"5";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(thu_6.isChecked())
			{
				if(!thu_6.getText().equals("free hour"))
				delete_str=thu_6.getText()+"/"+date_res+"/"+"6";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(thu_7.isChecked())
			{
				if(!thu_7.getText().equals("free hour"))
				delete_str=thu_7.getText()+"/"+date_res+"/"+"7";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(thu_8.isChecked())
			{
				if(!thu_8.getText().equals("free hour"))
				delete_str=thu_8.getText()+"/"+date_res+"/"+"8";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(thu_9.isChecked())
			{
				if(!thu_9.getText().equals("free hour"))
				delete_str=thu_9.getText()+"/"+date_res+"/"+"9";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(thu_10.isChecked())
			{
				if(!thu_10.getText().equals("free hour"))
				delete_str=thu_10.getText()+"/"+date_res+"/"+"10";
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
			Intent thu_dateintent=new Intent(ThursdayTimeTable.this,DatePick.class);
			startActivity(thu_dateintent);
			break;
		   
		}
		
		return super.onOptionsItemSelected(item);
		
	}
	
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent dbac=new Intent(ThursdayTimeTable.this,DatePick.class);
		startActivity(dbac);
		
		}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}

	