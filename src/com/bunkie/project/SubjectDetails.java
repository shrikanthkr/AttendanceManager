package com.bunkie.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SubjectDetails extends Activity implements OnClickListener{
	
	Button add;
	EditText subject,credits,totalhours;
	String sub,cre,tot;
	Bundle b;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		sub="";
		cre="";
		tot="";
		b=new Bundle();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subjectdetails);
		add=(Button)findViewById(R.id.addDetails);
		subject=(EditText)findViewById(R.id.subject);
		credits=(EditText)findViewById(R.id.credits);
		totalhours=(EditText)findViewById(R.id.totalclasses);
		credits.setInputType(InputType.TYPE_CLASS_NUMBER);
		totalhours.setInputType(InputType.TYPE_CLASS_NUMBER);
		add.setOnClickListener(this);		
		}
	
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		try{
		sub=subject.getText().toString().trim();
		cre=credits.getText().toString().trim();
		tot=totalhours.getText().toString().trim();
		}
		catch(Exception ex)
		{
			
		}
		if(!sub.equals("") && !cre.equals("") && !tot.equals(""))
		{
		DataBaseSubjectDredit1 dsc=new DataBaseSubjectDredit1(SubjectDetails.this);
		dsc.open();
		dsc.insert(sub,cre,tot);
		dsc.close();
	    finish();
	    Bundle nb=new Bundle();
	    nb.putString("condition", "set");
		Intent toBunkieActivity=new Intent(SubjectDetails.this,BunkieActivity.class);
		toBunkieActivity.putExtras(nb);
	    startActivity(toBunkieActivity);
	    finish();
		}
		else
			Toast.makeText(this, "please enter all details", 2).show();
		}
	 @Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			b.putString("SUBJECT", subject.getText().toString());
			b.putString("CREDIT", credits.getText().toString());
			b.putString("TOTALCLASSES", totalhours.getText().toString());
			
		}


	
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	    finish();
	    Bundle nb=new Bundle();
	    nb.putString("condition", "set");
	    Intent toBunkieActivity=new Intent(SubjectDetails.this,BunkieActivity.class);
	    toBunkieActivity.putExtras(nb);
	    startActivity(toBunkieActivity);
	}
	public void onResume(){
		super.onResume();
		try{
			subject.setText(b.getString("SUBJECT"));
			credits.setText(b.getString("CREDIT"));
			totalhours.setText(b.getString("TOTALCLASSES"));
		}catch(Exception e){}
	}



	 
	 	
}
