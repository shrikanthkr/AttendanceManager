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


public class WednesdayTab extends Activity implements SimpleGestureListener,OnClickListener{
	
	String sub,cre;
	TimeTableDatabase ttd;
    String[] subarray,crearray;
    int count=0;
	Spinner wed_one,wed_two,wed_three,wed_four,wed_five,wed_six,wed_seven,wed_eight,wed_nine,wed_ten;
	SharedPreferences wed_subjects;
	SharedPreferences.Editor wed_edit;
	private SimpleGestureFilter detector;
	ImageButton done;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		wed_subjects=getSharedPreferences("wednesday",MODE_WORLD_READABLE);
		wed_edit=wed_subjects.edit();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wednesday);
		done=(ImageButton)findViewById(R.id.gonextbutton_wed);
		done.setOnClickListener(this);
		 detector = new SimpleGestureFilter(this,this);
		try
        {
        	DataBaseSubjectDredit1 dsc=new DataBaseSubjectDredit1(WednesdayTab.this);
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
	    
		wed_one=(Spinner)this.findViewById(R.id.wed_spinner1);
	    wed_two=(Spinner)this.findViewById(R.id.wed_spinner2);
	    wed_three=(Spinner)this.findViewById(R.id.wed_spinner3);
	    wed_four=(Spinner)this.findViewById(R.id.wed_spinner4);
	    wed_five=(Spinner)this.findViewById(R.id.wed_spinner5);
	    wed_six=(Spinner)this.findViewById(R.id.wed_spinner6);
	    wed_seven=(Spinner)this.findViewById(R.id.wed_spinner7);
	    wed_eight=(Spinner)this.findViewById(R.id.wed_spinner8);
	    wed_nine=(Spinner)this.findViewById(R.id.wed_spinner9);
	    wed_ten=(Spinner)this.findViewById(R.id.wed_spinner10);
	    
	    wed_one.setAdapter(adapter);
	    wed_two.setAdapter(adapter);
	    wed_three.setAdapter(adapter);
	    wed_four.setAdapter(adapter);
	    wed_five.setAdapter(adapter);
	    wed_six.setAdapter(adapter);
	    wed_seven.setAdapter(adapter);
	    wed_eight.setAdapter(adapter);
	    wed_nine.setAdapter(adapter);
	    wed_ten.setAdapter(adapter);
	    
	
	   
	   
		
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
			  Intent a=new Intent(WednesdayTab.this,ThursdayTab.class);
			  startActivity(a);
			  finish();
		
		                                           break;
		  case SimpleGestureFilter.SWIPE_RIGHT : 
			  Intent a1=new Intent(WednesdayTab.this,TuesdayTab.class);
			  startActivity(a1);
			  finish();
		 break;
		
		 
		
		  }
		
		  

		
	}
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 wed_edit.putString("wednesday", wed_one.getSelectedItem()+"/"+wed_two.getSelectedItem()+"/"+wed_three.getSelectedItem()+"/"+wed_four.getSelectedItem()+"/"+wed_five.getSelectedItem()+"/"+wed_six.getSelectedItem()+"/"+wed_seven.getSelectedItem()+"/"+wed_eight.getSelectedItem()+"/"+wed_nine.getSelectedItem()+"/"+wed_ten.getSelectedItem());
		 wed_edit.commit();
		finish();
	}
	public void onDoubleTap() {
		// TODO Auto-generated method stub
		
	}
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent a=new Intent(WednesdayTab.this,ThursdayTab.class);
		  startActivity(a);
		  finish();
		
	}

}
