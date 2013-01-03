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



public class TuesdayTab extends Activity implements SimpleGestureListener,OnClickListener{
	String sub,cre;
	TimeTableDatabase ttd;
    String[] subarray,crearray;
    int count=0;
	Spinner tue_one,tue_two,tue_three,tue_four,tue_five,tue_six,tue_seven,tue_eight,tue_nine,tue_ten;
	SharedPreferences tue_subjects;
	SharedPreferences.Editor tue_edit;
	private SimpleGestureFilter detector;
	ImageButton done;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		tue_subjects=getSharedPreferences("tuesday",MODE_WORLD_READABLE);
		tue_edit=tue_subjects.edit();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tuesday);
		done=(ImageButton)findViewById(R.id.gonextbutton_tue);
		done.setOnClickListener(this);
		 detector = new SimpleGestureFilter(this,this);
		try
        {
        	DataBaseSubjectDredit1 dsc=new DataBaseSubjectDredit1(TuesdayTab.this);
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
	tue_one=(Spinner)this.findViewById(R.id.tue_spinner1);
    tue_two=(Spinner)this.findViewById(R.id.tue_spinner2);
    tue_three=(Spinner)this.findViewById(R.id.tue_spinner3);
    tue_four=(Spinner)this.findViewById(R.id.tue_spinner4);
    tue_five=(Spinner)this.findViewById(R.id.tue_spinner5);
    tue_six=(Spinner)this.findViewById(R.id.tue_spinner6);
    tue_seven=(Spinner)this.findViewById(R.id.tue_spinner7);
    tue_eight=(Spinner)this.findViewById(R.id.tue_spinner8);
    tue_nine=(Spinner)this.findViewById(R.id.tue_spinner9);
    tue_ten=(Spinner)this.findViewById(R.id.tue_spinner10);
    
    tue_one.setAdapter(adapter);
    tue_two.setAdapter(adapter);
    tue_three.setAdapter(adapter);
    tue_four.setAdapter(adapter);
    tue_five.setAdapter(adapter);
    tue_six.setAdapter(adapter);
    tue_seven.setAdapter(adapter);
    tue_eight.setAdapter(adapter);
    tue_nine.setAdapter(adapter);
    tue_ten.setAdapter(adapter);
    

   
    
    
    
	
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

protected void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
	tue_edit.putString("tuesday", tue_one.getSelectedItem()+"/"+tue_two.getSelectedItem()+"/"+tue_three.getSelectedItem()+"/"+tue_four.getSelectedItem()+"/"+tue_five.getSelectedItem()+"/"+tue_six.getSelectedItem()+"/"+tue_seven.getSelectedItem()+"/"+tue_eight.getSelectedItem()+"/"+tue_nine.getSelectedItem()+"/"+tue_ten.getSelectedItem() );
	tue_edit.commit();
	finish();
}
public void onSwipe(int direction) {
	// TODO Auto-generated method stub

	
	 
	
	  switch (direction) {
	
	 
	
	  case SimpleGestureFilter.SWIPE_LEFT : 
		  Intent a=new Intent(TuesdayTab.this,WednesdayTab.class);
		  startActivity(a);
		  finish();
	                                           break;
	  case SimpleGestureFilter.SWIPE_RIGHT : 
		  Intent a1=new Intent(TuesdayTab.this,MondatTab.class);
		  startActivity(a1);
		  finish();
		  break;
	 
	
	 
	
	  }
	
	  

	
}
public void onDoubleTap() {
	// TODO Auto-generated method stub
	
}
public boolean dispatchTouchEvent(MotionEvent me){
	this.detector.onTouchEvent(me);
	return super.dispatchTouchEvent(me);
}
public void onClick(View arg0) {
	// TODO Auto-generated method stub
	  Intent a=new Intent(TuesdayTab.this,WednesdayTab.class);
	  startActivity(a);
	  finish();
}

}
