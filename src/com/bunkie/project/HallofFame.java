package com.bunkie.project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HallofFame extends Activity implements OnClickListener{

	ImageView badge_gold;
	ImageView badge_silver;
	ImageView badge_bronze;
	String[] userdata;
	SharedPreferences sem_n;
	InitialDatabase ind;
	ImageButton home;
	String l_classes,per,min_per,t_bunks;
	TextView username;
	TextView sem;
	TextView attend;
	TextView thtext;
	long percent;
	ProgressBar pb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ind=new InitialDatabase(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hallof);
		
		pb=(ProgressBar)findViewById(R.id.progressbarlevel);
		pb.setProgressDrawable(getResources().getDrawable(R.drawable.bunkbar));
		Bundle recfromdash=getIntent().getExtras();
		l_classes=recfromdash.getString("l_classes");
		per=recfromdash.getString("percent");
		min_per=recfromdash.getString("min_percent");
		t_bunks=recfromdash.getString("total_bunks");
		
		username=(TextView) findViewById(R.id.username);
		sem=(TextView) findViewById(R.id.textsem);
		attend=(TextView) findViewById(R.id.attend);
		thtext=(TextView) findViewById(R.id.thtext);
		home=(ImageButton) findViewById(R.id.imageView2);
		home.setOnClickListener(this);
		
		badge_gold=(ImageView) findViewById(R.id.badge_gold);
		badge_silver=(ImageView) findViewById(R.id.badge_silver);
		badge_bronze=(ImageView) findViewById(R.id.badge_bronze);
		
	    badge_gold.setVisibility(View.INVISIBLE);
	    badge_silver.setVisibility(View.INVISIBLE);
	    badge_bronze.setVisibility(View.INVISIBLE);
	
	    sem_n=getSharedPreferences("Semester_No",MODE_WORLD_READABLE);
		String shared_sem=sem_n.getString("sem_number_key", "1");
		ind.open();
		String udata=ind.getIniData();
		ind.close();
		
		userdata=udata.split("/");
	    
		username.setText("Hello "+userdata[0]);
		sem.setText(shared_sem);
		
		if(Integer.parseInt(shared_sem)==1)
			thtext.setText("st");
		else if(Integer.parseInt(shared_sem)==2)
			thtext.setText("nd");
		else if(Integer.parseInt(shared_sem)==3)
			thtext.setText("rd");
		else
			thtext.setText("th");
	
		if(Long.parseLong(per) >= Long.parseLong(min_per) && Long.parseLong(per) <= (Long.parseLong(min_per)+10))
		{
			badge_gold.setVisibility(View.VISIBLE);
			percent=75;
		}
		else if(Long.parseLong(per) > (Long.parseLong(min_per)+10))
		{
			badge_silver.setVisibility(View.VISIBLE);
			percent=100;
		}
		else if(Long.parseLong(per) < Long.parseLong(min_per) )
		{
			badge_bronze.setVisibility(View.VISIBLE);
			if(Long.parseLong(per)>=50)
			percent=25+(Long.parseLong(per)%50);
			else if(Long.parseLong(per)<50 && Long.parseLong(per)>=25)
				percent=(Long.parseLong(per)%25);
			else
				percent=0;
		}
		
		attend.setText((Long.parseLong(per))+" %");
		Thread t1=new Thread(){
			int currentPosition= 0;
			
			public void run()
				{
					
						
				         while (currentPosition< percent) {
				        	
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
		Intent todash=new Intent(HallofFame.this,DashBoard.class);
		startActivity(todash);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent todash=new Intent(HallofFame.this,DashBoard.class);
		startActivity(todash);
		
	}
	 
	
}
