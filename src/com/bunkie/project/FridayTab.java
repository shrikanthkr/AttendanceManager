package com.bunkie.project;

import java.util.StringTokenizer;

import com.bunkie.project.SimpleGestureFilter.SimpleGestureListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;


public class FridayTab extends Activity implements SimpleGestureListener,OnClickListener{
	String sub,cre;
	TimeTableDatabase ttd;
    String[] subarray,crearray;
	SharedPreferences bunk;
    SharedPreferences.Editor editor;
    int count=0;
    private SimpleGestureFilter detector;
    
    
    SharedPreferences mon_subjects;
    SharedPreferences tue_subjects;
    SharedPreferences wed_subjects;
    SharedPreferences thu_subjects;
    SharedPreferences fri_subjects;
    
    String monday;
	String tuesday;
	String wednesday;
	String thursday;
	String friday;

     Spinner fri_one;
     Spinner fri_two;
     Spinner fri_three;
     Spinner fri_four;
     Spinner fri_five;
     Spinner fri_six;
     Spinner fri_seven;
     Spinner fri_eight;
     Spinner fri_nine;
     Spinner fri_ten;
     
     
     SharedPreferences.Editor fri_edit;
     
     ImageButton done;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		fri_subjects=getSharedPreferences("friday",MODE_WORLD_READABLE);
		fri_edit=fri_subjects.edit();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friday);
		done=(ImageButton)findViewById(R.id.gonextbutton_fri);
		done.setOnClickListener(this);
		 detector = new SimpleGestureFilter(this,this);
		 mon_subjects=getSharedPreferences("monday",MODE_WORLD_READABLE);
	    	tue_subjects=getSharedPreferences("tuesday",MODE_WORLD_READABLE);
	    	wed_subjects=getSharedPreferences("wednesday",MODE_WORLD_READABLE);
	    	thu_subjects=getSharedPreferences("thursday",MODE_WORLD_READABLE);
	    	fri_subjects=getSharedPreferences("friday",MODE_WORLD_READABLE);
	    	bunk=getSharedPreferences("bunkie",MODE_WORLD_READABLE);
	        editor=bunk.edit();
		try
        {
        	DataBaseSubjectDredit1 dsc=new DataBaseSubjectDredit1(FridayTab.this);
        dsc.open();
        sub=dsc.getSubject();
        dsc.close();
        
        }
        catch(Exception e)
        {
        	
        }
        countTokens(sub);
        tokenValues(sub);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,subarray);
	    adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
	    
		fri_one=(Spinner)this.findViewById(R.id.fri_spinner1);
        fri_two=(Spinner)this.findViewById(R.id.fri_spinner2);
        fri_three=(Spinner)this.findViewById(R.id.fri_spinner3);
        fri_four=(Spinner)this.findViewById(R.id.fri_spinner4);
        fri_five=(Spinner)this.findViewById(R.id.fri_spinner5);
        fri_six=(Spinner)this.findViewById(R.id.fri_spinner6);
        fri_seven=(Spinner)this.findViewById(R.id.fri_spinner7);
        fri_eight=(Spinner)this.findViewById(R.id.fri_spinner8);
        fri_nine=(Spinner)this.findViewById(R.id.fri_spinner9);
        fri_ten=(Spinner)this.findViewById(R.id.fri_spinner10);
        
	    fri_one.setAdapter(adapter);
	    fri_two.setAdapter(adapter);
	    fri_three.setAdapter(adapter);
	    fri_four.setAdapter(adapter);
	    fri_five.setAdapter(adapter);
	    fri_six.setAdapter(adapter);
	    fri_seven.setAdapter(adapter);
	    fri_eight.setAdapter(adapter);
	    fri_nine.setAdapter(adapter);
	    fri_ten.setAdapter(adapter);
	    
	   
	    
	    
	    
	}
    
	private void tokenValues(String subject) {
		// TODO Auto-generated method stub
		StringTokenizer subtoken = new StringTokenizer(subject,"\n");
		subarray=new String[count+1];
		subarray[0]="free hour";
		while(subtoken.hasMoreTokens())
	  	{
	  	    for(int i=0;i<count;i++)	
	  	    {
	  	    	subarray[i+1]=subtoken.nextToken();
	  	    }
	  	 }
		
		
	}
	private void countTokens(String sub2) {
		// TODO Auto-generated method stub
		StringTokenizer dummy = new StringTokenizer(sub,"\n");
        count=dummy.countTokens();
	}

	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 fri_edit.putString("friday", fri_one.getSelectedItem()+"/"+fri_two.getSelectedItem()+"/"+fri_three.getSelectedItem()+"/"+fri_four.getSelectedItem()+"/"+fri_five.getSelectedItem()+"/"+fri_six.getSelectedItem()+"/"+fri_seven.getSelectedItem()+"/"+fri_eight.getSelectedItem()+"/"+fri_nine.getSelectedItem()+"/"+fri_ten.getSelectedItem());
		 fri_edit.commit();
		finish();
	}


	public boolean dispatchTouchEvent(MotionEvent me){
		this.detector.onTouchEvent(me);
		return super.dispatchTouchEvent(me);
	}

	public void onSwipe(int direction) {
		// TODO Auto-generated method stub
		
		
		 
		
		  switch (direction) {
		
		 
		
		  case SimpleGestureFilter.SWIPE_LEFT : 
			  
			  	 fri_edit.putString("friday", fri_one.getSelectedItem()+"/"+fri_two.getSelectedItem()+"/"+fri_three.getSelectedItem()+"/"+fri_four.getSelectedItem()+"/"+fri_five.getSelectedItem()+"/"+fri_six.getSelectedItem()+"/"+fri_seven.getSelectedItem()+"/"+fri_eight.getSelectedItem()+"/"+fri_nine.getSelectedItem()+"/"+fri_ten.getSelectedItem());
				 fri_edit.commit();
					
					monday=mon_subjects.getString("monday", "free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour");
					tuesday=tue_subjects.getString("tuesday", "free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour");
					wednesday=wed_subjects.getString("wednesday", "free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour");
					thursday=thu_subjects.getString("thursday", "free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour");
					friday=fri_subjects.getString("friday", "free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour");
				
			
				
				ttd=new TimeTableDatabase(this);
				
				
				 AlertDialog.Builder builder = new AlertDialog.Builder(this);
				   builder.setMessage("Proceed to Creation of the Timetable?");
		    	   builder.setCancelable(true);
		    	          builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		    	              public void onClick(DialogInterface dialog, int id) {
		    	            	  try
		    	  				{
		    	  				ttd.open();
		    	  				
		    	  				ttd.insert(monday, tuesday, wednesday, thursday, friday);
		    	  				
		    	  				ttd.close();
		    	  				}
		    	  				catch(Exception ex)
		    	  				{
		    	  					
		    	  				}
		    	            	  Intent a=new Intent(FridayTab.this,DashBoard.class);
		    	    			  startActivity(a);
		    	    			  Boolean first=bunk.getBoolean("first",true);
		    	  				  if(first)
		    	  		         {
		    	  		           editor.putBoolean("first", false);
		    	  		           editor.commit();
		    	  		           
		    	  		         }
		    	  				
		    	          		  finish();
		    	                dialog.cancel();
		    	              }
		    	             });
		    	        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
		  	              public void onClick(DialogInterface dialog, int id) {
		  	            	dialog.cancel();
		  	              }
		  	            });
		    	          builder.show();
			 
		
		                    break;
		  case SimpleGestureFilter.SWIPE_RIGHT : 
			  Intent a1=new Intent(FridayTab.this,ThursdayTab.class);
			  startActivity(a1);
			  finish();
		  
		
		 
		
		  }
		
		   

		
	}

	public void onDoubleTap() {
		// TODO Auto-generated method stub
		
	}
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		  
			 fri_edit.putString("friday", fri_one.getSelectedItem()+"/"+fri_two.getSelectedItem()+"/"+fri_three.getSelectedItem()+"/"+fri_four.getSelectedItem()+"/"+fri_five.getSelectedItem()+"/"+fri_six.getSelectedItem()+"/"+fri_seven.getSelectedItem()+"/"+fri_eight.getSelectedItem()+"/"+fri_nine.getSelectedItem()+"/"+fri_ten.getSelectedItem());
			 fri_edit.commit();
				
				monday=mon_subjects.getString("monday", "free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour");
				tuesday=tue_subjects.getString("tuesday", "free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour");
				wednesday=wed_subjects.getString("wednesday", "free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour");
				thursday=thu_subjects.getString("thursday", "free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour");
				friday=fri_subjects.getString("friday", "free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour"+"/"+"free hour");
			
		
			
			 ttd=new TimeTableDatabase(this);
			
			
			   AlertDialog.Builder builder = new AlertDialog.Builder(this);
			   builder.setMessage("Proceed to Creation of the Timetable?");
	    	   builder.setCancelable(true);
	    	          builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	    	              public void onClick(DialogInterface dialog, int id) {
	    	            	  try
	    	      			{
	    	      			ttd.open();
	    	      			
	    	      			ttd.insert(monday, tuesday, wednesday, thursday, friday);
	    	      			
	    	      			ttd.close();
	    	      			}
	    	      			catch(Exception ex)
	    	      			{
	    	      				
	    	      			}
	    	      			Boolean first=bunk.getBoolean("first",true);
	    	      			if(first)
	    	      	        {
	    	      	           editor.putBoolean("first", false);
	    	      	           editor.commit();
	    	      	           
	    	      	        }
	    	            	  Intent a=new Intent(FridayTab.this,DashBoard.class);
	    	    			  startActivity(a);
	    	          		  finish();
	    	                  dialog.cancel();
	    	              }
	    	             });
	    	        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
	  	              public void onClick(DialogInterface dialog, int id) {
	  	            	dialog.cancel();
	  	              }
	  	            });
	    	          builder.show();
	    	         
		
	}
    
}
