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

public class FridayTimeTable extends TabActivity implements OnClickListener{
    
	
	CheckBox fri_1;
    CheckBox fri_2;
    CheckBox fri_3;
    CheckBox fri_4;
    CheckBox fri_5;
    CheckBox fri_6;
    CheckBox fri_7;
    CheckBox fri_8;
    CheckBox fri_9;
    CheckBox fri_10;
    
    Button friday_done;
    Button friday_delete;
    String[] fri_array;
    
    String fri;
    
    String fri_Result="";
    
    int count=0;
    DateDatabase dd;
    TabHost tabHost;
    String date_res="";
    String b_sub;
	String b_count;
	String[] b_subarray;
	String[] b_countarray;
    
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
    	
		Bundle receive=getIntent().getExtras();
        date_res=receive.getString("key");
		dd=new DateDatabase(this);
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.friday_timetable);
		tabHost=(TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
		tabHost= getTabHost();
        TabHost.TabSpec spec;

        spec = tabHost.newTabSpec("friday").setIndicator("Friday",null).setContent(R.id.friday_timetable);
        tabHost.addTab(spec);
        
        fri_1=(CheckBox) findViewById(R.id.fri_CheckBox1);
        fri_2=(CheckBox) findViewById(R.id.fri_CheckBox2);
        fri_3=(CheckBox) findViewById(R.id.fri_CheckBox3);
        fri_4=(CheckBox) findViewById(R.id.fri_CheckBox4);
        fri_5=(CheckBox) findViewById(R.id.fri_CheckBox5);
        fri_6=(CheckBox) findViewById(R.id.fri_CheckBox6);
        fri_7=(CheckBox) findViewById(R.id.fri_CheckBox7);
        fri_8=(CheckBox) findViewById(R.id.fri_CheckBox8);
        fri_9=(CheckBox) findViewById(R.id.fri_CheckBox9);
        fri_10=(CheckBox) findViewById(R.id.fri_CheckBox10);
        
        friday_done=(Button) findViewById(R.id.friday_done);
        friday_delete=(Button) findViewById(R.id.friday_delete);
        friday_done.setOnClickListener(this);
        friday_delete.setOnClickListener(this);
       
		
        TimeTableDatabase ttd=new TimeTableDatabase(this);
        ttd.open();
        fri=ttd.getFridayPeriod();
        
        ttd.close();
        
        fri_array=fri.split("\n");
        
        
        fri_1.setText(fri_array[0]);
        fri_2.setText(fri_array[1]);
        fri_3.setText(fri_array[2]);
        fri_4.setText(fri_array[3]);
        fri_5.setText(fri_array[4]);
        fri_6.setText(fri_array[5]);
        fri_7.setText(fri_array[6]);
        fri_8.setText(fri_array[7]);
        fri_9.setText(fri_array[8]);
        fri_10.setText(fri_array[9]);
        
        if((fri_1.getText().equals("free hour")))
        {
        	fri_1.setClickable(false);
        }
        if((fri_2.getText().equals("free hour")))
        {
        	fri_2.setClickable(false);
        }
        if((fri_3.getText().equals("free hour")))
        {
        	fri_3.setClickable(false);
        }
        if((fri_4.getText().equals("free hour")))
        {
        	fri_4.setClickable(false);
        }
        if((fri_5.getText().equals("free hour")))
        {
        	fri_5.setClickable(false);
        }
        if((fri_6.getText().equals("free hour")))
        {
        	fri_6.setClickable(false);
        }
        if((fri_7.getText().equals("free hour")))
        {
        	fri_7.setClickable(false);
        }
        if((fri_8.getText().equals("free hour")))
        {
        	fri_8.setClickable(false);
        }
        if((fri_9.getText().equals("free hour")))
        {
        	fri_9.setClickable(false);
        }
        if((fri_10.getText().equals("free hour")))
        {
        	fri_10.setClickable(false);
        }
    
        
        
	}
	
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId())
		{
		case R.id.friday_done: 
			fri_Result="";
			if(fri_1.isChecked())
			{
				if(!fri_1.getText().equals("free hour"))
				fri_Result=fri_Result+fri_1.getText()+"/"+"1"+"/";
			}
			if(fri_2.isChecked())
			{
				if(!fri_2.getText().equals("free hour"))
				fri_Result=fri_Result+fri_2.getText()+"/"+"2"+"/";
			
			}
			if(fri_3.isChecked())
			{
				if(!fri_3.getText().equals("free hour"))
				fri_Result=fri_Result+fri_3.getText()+"/"+"3"+"/";
			}
			if(fri_4.isChecked())
			{
				if(!fri_4.getText().equals("free hour"))
				fri_Result=fri_Result+fri_4.getText()+"/"+"4"+"/";
			}
			if(fri_5.isChecked())
			{
				if(!fri_5.getText().equals("free hour"))
				fri_Result=fri_Result+fri_5.getText()+"/"+"5"+"/";
			}
			
			if(fri_6.isChecked())
			{
				if(!fri_6.getText().equals("free hour"))
				fri_Result=fri_Result+fri_6.getText()+"/"+"6"+"/";
			}
			if(fri_7.isChecked())
			{
				if(!fri_7.getText().equals("free hour"))
				fri_Result=fri_Result+fri_7.getText()+"/"+"7"+"/";
			}
		    if(fri_8.isChecked())
		    {
		    	if(!fri_8.getText().equals("free hour"))
		    	fri_Result=fri_Result+fri_8.getText()+"/"+"8"+"/";
		    }
		    if(fri_9.isChecked())
		    {
		    	if(!fri_9.getText().equals("free hour"))
		    	fri_Result=fri_Result+fri_9.getText()+"/"+"9"+"/'";
		    }
				if(fri_10.isChecked())
				{
					if(!fri_10.getText().equals("free hour"))
					fri_Result=fri_Result+fri_10.getText()+"/"+"10"+"/";
				}
	
				 if(!fri_Result.equals(""))
				 {
					    insertIntoDatabase(fri_Result);
					    Intent todash=new Intent(FridayTimeTable.this,DashBoard.class);
					    startActivity(todash);
				 }	    
			    else
	         	   Toast.makeText(this, "Please select a subject and add bunk", 2).show();
		     break;
		     
		case R.id.friday_delete:
			String delete_str="";
			if(fri_1.isChecked())
			{
				if(!fri_1.getText().equals("free hour"))
				delete_str=fri_1.getText()+"/"+date_res+"/"+"1";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
				
			}
			
			if(fri_2.isChecked())
			{
				if(!fri_2.getText().equals("free hour"))
				delete_str=fri_2.getText()+"/"+date_res+"/"+"2";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(fri_3.isChecked())
			{
				if(!fri_3.getText().equals("free hour"))
				delete_str=fri_3.getText()+"/"+date_res+"/"+"3";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(fri_4.isChecked())
			{
				if(!fri_4.getText().equals("free hour"))
				delete_str=fri_4.getText()+"/"+date_res+"/"+"4";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(fri_5.isChecked())
			{
				if(!fri_5.getText().equals("free hour"))
				delete_str=fri_5.getText()+"/"+date_res+"/"+"5";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(fri_6.isChecked())
			{
				if(!fri_6.getText().equals("free hour"))
				delete_str=fri_6.getText()+"/"+date_res+"/"+"6";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(fri_7.isChecked())
			{
				if(!fri_7.getText().equals("free hour"))
				delete_str=fri_7.getText()+"/"+date_res+"/"+"7";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(fri_8.isChecked())
			{
				if(!fri_8.getText().equals("free hour"))
				delete_str=fri_8.getText()+"/"+date_res+"/"+"8";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(fri_9.isChecked())
			{
				if(!fri_9.getText().equals("free hour"))
				delete_str=fri_9.getText()+"/"+date_res+"/"+"9";
				if(!delete_str.equals(""))
				deleteFromDatabase(delete_str);
			}
			
			if(fri_10.isChecked())
			{
				if(!fri_10.getText().equals("free hour"))
				delete_str=fri_10.getText()+"/"+date_res+"/"+"10";
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
			Intent fri_dateintent=new Intent(FridayTimeTable.this,DatePick.class);
			startActivity(fri_dateintent);
			break;
		   
		}
		
		return super.onOptionsItemSelected(item);
		
	}
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent dbac=new Intent(FridayTimeTable.this,DatePick.class);
		startActivity(dbac);
		
		}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	     

}

