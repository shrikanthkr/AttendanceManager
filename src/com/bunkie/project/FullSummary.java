package com.bunkie.project;

import java.util.ArrayList;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;



public class FullSummary extends ListActivity{

	private ArrayList<String> list_sub = null;
	private ArrayList<String> list_count = null;
   // private OrderAdapter m_adapter;
    DateDatabase dd;
    DataBaseSubjectDredit1 dsc;
    String b_sub;
	String b_count;
	String d_sub;
	String d_date;
    String[] b_subarray;
    String[] b_countarray;
    String[] seperate;
    String[] b_maincount;
	String[] d_subarray;
	String[] d_datearray;
	int flag=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		dd=new DateDatabase(this);
		dsc=new DataBaseSubjectDredit1(this);
		list_sub=new ArrayList<String>();
		list_count=new ArrayList<String>();
		dd.open();
	    b_count=dd.getSubCount();
		b_count=b_count.trim();
		b_countarray=b_count.split("\n");
		String temp=dd.getCount();
		b_maincount=temp.split("/");
	    dd.close();
	    dsc.open();
	    b_sub=dsc.getSubject();
		b_sub=b_sub.trim();
		b_subarray=b_sub.split("\n");
	    dsc.close();
	    
	    for(int i=0;i<b_subarray.length;i++){
	    		
	    	
	    	for(int j=0;j<b_countarray.length;j++)
	    	{
	    		seperate=b_countarray[j].trim().split("/");
	    		if(b_subarray[i].trim().equalsIgnoreCase(seperate[0].trim()))
	    		{
	    			list_sub.add(i,seperate[0]);
			    	list_count.add(i,seperate[1]);
			    	flag=1;
	    			break;
	    			
	    		}
	    	}		
	    	    if(flag==1)
	    	    {
	    		
		    	flag=0;
	    	    }	
	    		
	    		else
		    	{
		    	  list_sub.add(i,b_subarray[i]);
		    	  list_count.add(i,"0");
		    	  
		    	}
	    	
	    }
	    
	    setListAdapter(new ArrayAdapter<String>(this, R.layout.row, list_sub) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View row;
				parent.setBackgroundColor(Color.WHITE);
				
				LayoutInflater mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
				if (null == convertView) {
					row = mInflater.inflate(R.layout.row, null);
					row.setBackgroundColor(Color.WHITE);
				} else {
					row = convertView;
					row.setBackgroundColor(Color.WHITE);
					
				}
				TextView tv = (TextView) row.findViewById(R.id.toptext);
				TextView tv2 = (TextView) row.findViewById(R.id.bottomtext);
				tv.setText( "  "+getItem(position));
				tv.setTextColor(Color.BLACK);
				tv.setTextSize(28);
				tv2.setText("  No. of Bunks: "+list_count.get(position));
				tv2.setTextSize(15);
				tv2.setTextColor(Color.BLACK);
				return row;
			}
		});
	   
        
		
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		String s=list_sub.get(position);
		String s1=list_count.get(position);
		v.setBackgroundColor(Color.parseColor("#5b92f6"));
		
		Intent call=new Intent(this.getApplicationContext(),SpecificSummary.class);
		Bundle b=new Bundle();
		b.putString("SUBJECT",s);
		if(!s1.equals(""))
		b.putString("COUNT",s1);
		else
			b.putString("COUNT", "0");
		call.putExtras(b);
		startActivity(call);
		
		
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
			Intent dbac=new Intent(FullSummary.this,Summary.class);
 		    startActivity(dbac);
 		}
	
	
	
}
