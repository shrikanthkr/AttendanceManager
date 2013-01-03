package com.bunkie.project;

import java.util.StringTokenizer;

import com.bunkie.project.SimpleGestureFilter.SimpleGestureListener;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;


public class ThursdayTab extends Activity implements SimpleGestureListener,OnClickListener{

	String sub,cre;
	TimeTableDatabase ttd;
    String[] subarray,crearray;
    int count=0;
    
     Spinner thu_one,
     thu_two,
     thu_three,
     thu_four,
     thu_five,
     thu_six,
     thu_seven,
     thu_eight,
     thu_nine,
     thu_ten;
     SharedPreferences thu_subjects;
     
     SharedPreferences.Editor thu_edit;
     ImageButton done;
     private SimpleGestureFilter detector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		

		thu_subjects=getSharedPreferences("thursday",MODE_WORLD_READABLE);
		thu_edit=thu_subjects.edit();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thursday);
		done=(ImageButton)findViewById(R.id.gonextbutton_thu);
		done.setOnClickListener(this);
		 detector = new SimpleGestureFilter(this,this);
		try
        {
        	DataBaseSubjectDredit1 dsc=new DataBaseSubjectDredit1(ThursdayTab.this);
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
	   
	    thu_one=(Spinner)this.findViewById(R.id.thu_spinner1);
	    thu_two=(Spinner)this.findViewById(R.id.thu_spinner2);
	    thu_three=(Spinner)this.findViewById(R.id.thu_spinner3);
	    thu_four=(Spinner)this.findViewById(R.id.thu_spinner4);
	    thu_five=(Spinner)this.findViewById(R.id.thu_spinner5);
	    thu_six=(Spinner)this.findViewById(R.id.thu_spinner6);
	    thu_seven=(Spinner)this.findViewById(R.id.thu_spinner7);
	    thu_eight=(Spinner)this.findViewById(R.id.thu_spinner8);
	    thu_nine=(Spinner)this.findViewById(R.id.thu_spinner9);
	    thu_ten=(Spinner)this.findViewById(R.id.thu_spinner10);
	    
	    thu_one.setAdapter(adapter);
	    thu_two.setAdapter(adapter);
	    thu_three.setAdapter(adapter);
	    thu_four.setAdapter(adapter);
	    thu_five.setAdapter(adapter);
	    thu_six.setAdapter(adapter);
	    thu_seven.setAdapter(adapter);
	    thu_eight.setAdapter(adapter);
	    thu_nine.setAdapter(adapter);
	    thu_ten.setAdapter(adapter);
	    
	   
	   
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

	public boolean dispatchTouchEvent(MotionEvent me){
		this.detector.onTouchEvent(me);
		return super.dispatchTouchEvent(me);
	}
	public void onSwipe(int direction) {
		// TODO Auto-generated method stub
		
		
		 
		
		  switch (direction) {
		
		 
		
		  case SimpleGestureFilter.SWIPE_LEFT :  
			  Intent a=new Intent(ThursdayTab.this,FridayTab.class);
		  startActivity(a);finish();
		                                           break;
		
		  case SimpleGestureFilter.SWIPE_RIGHT : 
			  Intent a1=new Intent(ThursdayTab.this,WednesdayTab.class);
			  startActivity(a1);
			  finish();
		  
		
		 
		
		  }
		
		   
		
	}
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 thu_edit.putString("thursday", thu_one.getSelectedItem()+"/"+thu_two.getSelectedItem()+"/"+thu_three.getSelectedItem()+"/"+thu_four.getSelectedItem()+"/"+thu_five.getSelectedItem()+"/"+thu_six.getSelectedItem()+"/"+thu_seven.getSelectedItem()+"/"+thu_eight.getSelectedItem()+"/"+thu_nine.getSelectedItem()+"/"+thu_ten.getSelectedItem());
		 thu_edit.commit();
		finish();
	}
	public void onDoubleTap() {
		// TODO Auto-generated method stub
		
	}
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent a=new Intent(ThursdayTab.this,FridayTab.class);
		  startActivity(a);finish();
		
	}
	

}
