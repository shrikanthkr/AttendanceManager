package com.bunkie.project;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;




public class BunkieActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    Button addsubject,done;
    int i=0,cred,count=0;
    int flag=0;
    String sub,cre,tot;
    String[] subarray,crearray;
    String condition_string;
    TableLayout tl;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addsubject);
        tl=(TableLayout) findViewById(R.id.maintable);
        Bundle got=getIntent().getExtras();
        try
        {
        condition_string=got.getString("condition");
        }
        catch(Exception ex)
        {
        	condition_string="";
        }
        sub="";
        cre="";
        tot="";
        DataBaseSubjectDredit1 dsc=new DataBaseSubjectDredit1(BunkieActivity.this);
        dsc.open();
        try
        {
        
        sub=dsc.getSubject();
        cre=dsc.getCredit();
        tot=dsc.getTotalClasses();
        }
        catch(Exception e)
        {
         
        }
        dsc.close();
        subarray=sub.split("\n");
        crearray=cre.split("\n");
        for(int i=0;i<subarray.length;i++)
          {
          if(!subarray[i].equals("") & !crearray[i].equals(""))
		{
        	
        	TableRow tr = new TableRow(this);
            tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.MATCH_PARENT));   
            TextView subjectname=new TextView(this);
            subjectname.setText(""+ (i+1) +".\t"+subarray[i]);
            subjectname.setTextSize(17);
            subjectname.setTextColor(Color.BLACK);
            tr.addView(subjectname);
            tl.addView(tr, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT)); 
            flag=1;
         }
          

        }
          addsubject=(Button) findViewById(R.id.addsubjectbutton);
          addsubject.setOnClickListener(this);
          done=(Button) findViewById(R.id.donebutton);
          done.setOnClickListener(this);
	}
	
    
    
    public void onClick(View arg0) {
		// TODO Auto-generated method stub
    	switch(arg0.getId())
    	{
    	case R.id.addsubjectbutton:
    	Intent subdet=new Intent(BunkieActivity.this,SubjectDetails.class);
		startActivity(subdet);
		finish();
		break;
    	case R.id.donebutton:
    	   if(condition_string.equals("set") && flag==1)
    	   {
    	   AlertDialog.Builder builder = new AlertDialog.Builder(this);
  		   
      	   builder.setMessage("Have you entered all the subjects?");
      	   builder.setCancelable(true);
      	          builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
      	              public void onClick(DialogInterface dialog, int id) {
      	            	finish();
      	            	Intent doneIntent=new Intent(BunkieActivity.this,MondatTab.class);
      	          	    startActivity(doneIntent);
      	          	    
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
    	   else
    		   Toast.makeText(this, "Please Enter atleast one subject", 2).show();
    	break;
	   }
    	

}
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
	}
}