package com.bunkie.project;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InitialPage extends Activity implements OnClickListener{
	
	
	Boolean val;
	Button next;
	EditText username;
	EditText univname;
	EditText min_percent;
	
	String user;
	String univ;
	String per;
	Float percent_attendance;
	
	SharedPreferences userdata;
    SharedPreferences.Editor edit_userdata;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.initial);
		
		userdata=getSharedPreferences("userdata",MODE_WORLD_READABLE);
		edit_userdata=userdata.edit();
		
		next=(Button) findViewById(R.id.button_next);
		username=(EditText) findViewById(R.id.username);
		univname=(EditText) findViewById(R.id.univname);
		min_percent=(EditText) findViewById(R.id.attendance);
		
		
		min_percent.setInputType(InputType.TYPE_CLASS_NUMBER);
		
		next.setOnClickListener(this);
	}


       public void onClick(View arg0) {

		switch(arg0.getId())
		{
		case R.id.button_next: 
			
			user= username.getText().toString().trim();
			univ=univname.getText().toString().trim();
			per=min_percent.getText().toString().trim();
			try
			{
				percent_attendance= Float.parseFloat(per);
			}
			catch(Exception e)
			{
			 
			}
			
			
			if(!user.equals("") && !univ.equals("") && !per.equals(""))
			{
				Thread t1=new Thread(){
					public void run()
					{
			    edit_userdata.putString("username", user);
			    edit_userdata.putString("univname", univ);
		    	edit_userdata.putString("min_percent", per);
			    edit_userdata.commit();
				    }
				};
				Thread t2=new Thread(){
				public void run()
				{
				Intent tostartdateandsem=new Intent(InitialPage.this,Startdateandsem.class);
				startActivity(tostartdateandsem);
				}};
				t2.start();
				t1.start();
				
				finish();

			}
			
			else
			Toast.makeText(getApplicationContext(), "Please enter valid details!", 2).show();
			
			break;
			
	}
}
	
       public boolean onCreateOptionsMenu(Menu dashboardmenu)
   	{
   		MenuInflater inflate=getMenuInflater();
   		inflate.inflate(R.menu.dashboardmenu, dashboardmenu);
   	    return true;
   	}
   	
   	public boolean onOptionsItemSelected(MenuItem item)
   	{
   		
   		switch(item.getItemId())
   		{
   		case R.id.about:
   			
   			Bundle b=new Bundle();
   			b.putInt("FLAG", 1);
   			b.putString("from", "frominitial");
   			Intent toPage=new Intent(InitialPage.this,Page.class);
   			toPage.putExtras(b);
   			startActivity(toPage);
		    finish();
		    break;
		    
   		case R.id.help:
   			Bundle b1=new Bundle();
   			b1.putInt("FLAG", 0);
   			b1.putString("from", "frominitial");	
   			Intent toPage1=new Intent(InitialPage.this,Page.class);
   			toPage1.putExtras(b1);
   			startActivity(toPage1);
		    finish();
		    break;
   		   
   		}
   		
   		return super.onOptionsItemSelected(item);
   		
   	}
                
                @Override
    	protected void onPause() {
					// TODO Auto-generated method stub
					super.onPause();

				}

	

}
