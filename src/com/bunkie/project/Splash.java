package com.bunkie.project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Splash extends Activity{
   Boolean val;
   ProgressBar pb;
   int f;
   int c;
   final float[] roundedCorners = new float[] { 5, 5, 5, 5, 5, 5, 5, 5 };
   ShapeDrawable pgDrawable;
   String MyColor = "#FF00FF";
   String details;
   String[] detarray;
   
   SharedPreferences check;
   SharedPreferences.Editor check_edit;
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		check=getSharedPreferences("check",MODE_WORLD_READABLE);
		check_edit=check.edit();
		c=check.getInt("check", 0);
		
		pb=(ProgressBar)findViewById(R.id.pb);
		pb.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar));
      
		SharedPreferences bunk=getSharedPreferences("bunkie",MODE_WORLD_READABLE);
		val=bunk.getBoolean("first",true);
		SharedPreferences flag=getSharedPreferences("flag",MODE_WORLD_READABLE);
		f=flag.getInt("flag", 0);
			
		Thread t1=new Thread(){
			int currentPosition= 0;
			
			public void run()
				{
					
					while (currentPosition<100) {
				        	
				            try {
				                Thread.sleep(8);
				                currentPosition++;
				            } catch (InterruptedException e) {
				                return;
				            } catch (Exception e) {
				                return;
				            }finally{
				            
				            pb.setProgress(currentPosition);
				            }
				        }
				        
				        if(val)
						{
							if(c==0)
							{
							Intent toinit=new Intent(Splash.this, InitialPage.class);
							startActivity(toinit);
							}
							else if(c==1)
							{
								Intent toinit=new Intent(Splash.this, Startdateandsem.class);
								startActivity(toinit);
							}
				        }
						else
						{
						Intent tobun=new Intent(Splash.this, DashBoard.class);
						startActivity(tobun);	
				
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
	
}
