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


public class MondatTab extends Activity implements SimpleGestureListener,OnClickListener{
	String sub,cre;
	TimeTableDatabase ttd;
    String[] subarray,crearray;
    float x1=0;
	float x2=0;
    int count=0;
	 Spinner mon_one,mon_two,mon_three,mon_four;
	 Spinner mon_five;
	 Spinner mon_six;
	 Spinner mon_seven;
	 Spinner mon_eight;
	 Spinner mon_nine;
	 Spinner mon_ten;
	 private SimpleGestureFilter detector;
	 ImageButton done;
	 ArrayAdapter<String> adapter;
	
	SharedPreferences mon_subjects;
	SharedPreferences.Editor mon_edit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		mon_subjects=getSharedPreferences("monday",MODE_WORLD_READABLE);
		mon_edit=mon_subjects.edit();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.monday);
		
		 done=(ImageButton)findViewById(R.id.gonextbutton_mon);
		 done.setOnClickListener(this);
		 detector = new SimpleGestureFilter(this,this);

		 try
	        {
	        	DataBaseSubjectDredit1 dsc=new DataBaseSubjectDredit1(MondatTab.this);
	        dsc.open();
	        sub=dsc.getSubject();
	        dsc.close();
	        
	        }
	        catch(Exception e)
	        {
	        	
	        }
	        countTokens(sub);
	        tokenValues(sub);
	        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,subarray);
		    adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
		    mon_one=(Spinner)this.findViewById(R.id.mon_spinner1);
		    mon_two=(Spinner)this.findViewById(R.id.mon_spinner2);
		    mon_three=(Spinner)this.findViewById(R.id.mon_spinner3);
		    mon_four=(Spinner)this.findViewById(R.id.mon_spinner4);
		    mon_five=(Spinner)this.findViewById(R.id.mon_spinner5);
		    mon_six=(Spinner)this.findViewById(R.id.mon_spinner6);
		    mon_seven=(Spinner)this.findViewById(R.id.mon_spinner7);
		    mon_eight=(Spinner)this.findViewById(R.id.mon_spinner8);
		    mon_nine=(Spinner)this.findViewById(R.id.mon_spinner9);
		    mon_ten=(Spinner)this.findViewById(R.id.mon_spinner10);
	    
		    
	    
		    mon_one.setAdapter(adapter);
		    mon_two.setAdapter(adapter);
		    mon_three.setAdapter(adapter);
		    mon_four.setAdapter(adapter);
		    mon_five.setAdapter(adapter);
		    mon_six.setAdapter(adapter);
		    mon_seven.setAdapter(adapter);
		    mon_eight.setAdapter(adapter);
		    mon_nine.setAdapter(adapter);
		    mon_ten.setAdapter(adapter);
		    
		   
		
	   
	   
	}
	public boolean dispatchTouchEvent(MotionEvent me){
		this.detector.onTouchEvent(me);
		return super.dispatchTouchEvent(me);
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
		 mon_edit.putString("monday", mon_one.getSelectedItem()+"/"+mon_two.getSelectedItem()+"/"+mon_three.getSelectedItem()+"/"+mon_four.getSelectedItem()+"/"+mon_five.getSelectedItem()+"/"+mon_six.getSelectedItem()+"/"+mon_seven.getSelectedItem()+"/"+mon_eight.getSelectedItem()+"/"+mon_nine.getSelectedItem()+"/"+mon_ten.getSelectedItem());
		 mon_edit.commit();
		finish();
	}
	
	

	public void onSwipe(int direction) {
		// TODO Auto-generated method stub
		
		
		 
		
		  switch (direction) {
		
		 
		
		  case SimpleGestureFilter.SWIPE_LEFT : 
			  Intent a=new Intent(MondatTab.this,TuesdayTab.class);
			  startActivity(a);
			  finish();
			  break;
		
		
		 
		
		  }
		
		 
		
	}
	public void onDoubleTap() {
		// TODO Auto-generated method stub
		
	}
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		 Intent a=new Intent(MondatTab.this,TuesdayTab.class);
		  startActivity(a);
		  finish();
	}
	
	
}
