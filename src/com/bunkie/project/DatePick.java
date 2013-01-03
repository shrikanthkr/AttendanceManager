package com.bunkie.project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DatePick extends Activity implements OnClickListener{
	
	final Calendar c=Calendar.getInstance();
    private int year=c.get(Calendar.YEAR);
    private int month=c.get(Calendar.MONTH);
    private int day=c.get(Calendar.DAY_OF_MONTH);
    TextView t1,t2;
    Button set,change,markas;
    ImageButton home;
    String date_res;
    DateFormat dateFormat;
    static final int DATE_DIALOG_ID = 0;
    java.util.Date date;
	java.util.Date startdate;
	java.util.Date enddate;
	
	LeaveDatabase ld;
	int dateflag=0;
	Calendar endDate;
	String[] resarray;
	String enddatestring;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datepick);
		endDate=Calendar.getInstance();
		enddatestring=endDate.get(Calendar.YEAR)+"-"+((endDate.get(Calendar.MONTH))+1)+"-"+endDate.get(Calendar.DAY_OF_MONTH);
		ld=new LeaveDatabase(this);
		InitialDatabase id=new InitialDatabase(this);
		id.open();
		String res=id.getIniData();
		resarray=res.split("/");
		id.close();
		
		home=(ImageButton) findViewById(R.id.imageView2);
		set=(Button) findViewById(R.id.set_button);
		change=(Button) findViewById(R.id.change_button);
		markas=(Button) findViewById(R.id.markashol);
		t1=(TextView) findViewById(R.id.settextView);
		t2=(TextView) findViewById(R.id.daytextView);
		
		set.setOnClickListener(this);
		change.setOnClickListener(this);
		markas.setOnClickListener(this);
		home.setOnClickListener(this);
		date_res= year+"-"+(month+1)+"-"+day;
		t1.setText("Date: "+day+"-"+(month+1)+"-"+year);
		dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			date=dateFormat.parse(date_res);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  switch(date.getDay())
		  {
		  case 1:
			  t2.setText("Showing Timetable for Monday");
			  break;
		  case 2: 
			  t2.setText("Showing Timetable for Tuesday");
			  break;
		  case 3:
			  t2.setText("Showing Timetable for Wednesday");
			  break;
		  case 4: 
			  t2.setText("Showing Timetable for Thursday");
			  break;
		  case 5:
			  t2.setText("Showing Timetable for Friday");
			  break;
		     default:t2.setText("It's a Weekend!");break;
		  }
	}
	
	
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.set_button:
			
			Bundle b=new Bundle();
			b.putString("key",date_res);
			dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
			try {
				date=dateFormat.parse(date_res);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				enddate=dateFormat.parse(enddatestring);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				startdate=dateFormat.parse(resarray[2]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			LeaveDatabase ld=new LeaveDatabase(this);
			try{
				ld.open();
				String[] dates=ld.getDate();
				for(int i=0;i<dates.length;i++){
					if(date.equals(dateFormat.parse(dates[i])))
					{
					dateflag=1;
					break;
					}
					else
						dateflag=0;
				}
				
				ld.close();
			}catch(Exception e){
				
			}
			if(dateflag==0){
				
			  switch(date.getDay())
			  {
			
			  case 1:
				  if(date.after(startdate) || date.equals(startdate)) 
				  {
					  if(date.before(enddate) || date.equals(enddate))
					  {
						  Intent montimetable=new Intent(DatePick.this,MondayTimeTable.class);
						  montimetable.putExtras(b);
						  startActivity(montimetable);
					  }
					  else
						  Toast.makeText(this, "Sorry! You can't add future bunks", 1).show();
				  }
				  else
					  Toast.makeText(this, "Date selected is before Semester starting date!", 2).show();
				  break;
			  case 2:
				  if(date.after(startdate) || date.equals(startdate)) 
				  {
					  if(date.before(enddate) || date.equals(enddate))
					  {
						  Intent tuetimetable=new Intent(DatePick.this,TuesdayTimeTable.class);
						  tuetimetable.putExtras(b);
						  startActivity(tuetimetable);
					  }
					  else
						  Toast.makeText(this, "Sorry! You can't add future bunks", 1).show();
				  }
				  else
					  Toast.makeText(this, "Date selected is before Semester starting date!", 1).show();
				  break;
				  
			  case 3:
				  if(date.after(startdate) || date.equals(startdate)) 
				  {
					  if(date.before(enddate) || date.equals(enddate))
					  {
						  Intent wedtimetable=new Intent(DatePick.this,WednesdayTimeTable.class);
						  wedtimetable.putExtras(b);
						  startActivity(wedtimetable);
					  }
					  else
						  Toast.makeText(this, "Sorry! You can't add future bunks", 1).show();
				  }
				  else
					  Toast.makeText(this, "Date selected is before Semester starting date!", 1).show();
				  break;
			  case 4:
				  if(date.after(startdate) || date.equals(startdate))
				  {
					  if(date.before(enddate) || date.equals(enddate))
					  {
						  Intent thutimetable=new Intent(DatePick.this,ThursdayTimeTable.class);
						  thutimetable.putExtras(b);
						  startActivity(thutimetable);
					  }
					  else
						  Toast.makeText(this, "Sorry! You can't add future bunks", 1).show();
				  }
				  else
					  Toast.makeText(this, "Date selected is before Semester starting date!", 1).show();
				  break;
			  case 5:
				  if(date.after(startdate) || date.equals(startdate))
				  {
					  if(date.before(enddate) || date.equals(enddate))
					  {
						  Intent fritimetable=new Intent(DatePick.this,FridayTimeTable.class);
						  fritimetable.putExtras(b);
						  startActivity(fritimetable);
					  }
					  else
						  Toast.makeText(this, "Sorry! You can't add future bunks", 1).show();
				  }
				  else
					  Toast.makeText(this, "Date selected is before Semester starting date!", 1).show();
			  break;
			  default:
				  Toast.makeText(this, "It's a Weekend!", 2).show();
				  break;
			  }
			}
				else
					Toast.makeText(getApplicationContext(), "You have marked it as a Holiday", 1).show();
			  break;
		case R.id.change_button:
			showDialog(DATE_DIALOG_ID);
			break;
		case R.id.imageView2:
			Intent todash=new Intent(DatePick.this,DashBoard.class);
			startActivity(todash);
			break;
		case R.id.markashol:
			ld=new LeaveDatabase(this);
    		ld.open();
    		dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
			try {
				date=dateFormat.parse(date_res);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				enddate=dateFormat.parse(enddatestring);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				startdate=dateFormat.parse(resarray[2]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 if(date.after(startdate) || date.equals(startdate)) 
			  {
				  if(date.before(enddate) || date.equals(enddate))
				  {
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
			   
			   builder.setMessage("Mark it as Holiday?");
			   builder.setCancelable(true);
			          builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			              public void onClick(DialogInterface dialog, int id) {
			            	InsertintoLeaveDatabase();
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
				  Toast.makeText(this, "Sorry! You can't add future dates", 1).show();
			  } else
			  Toast.makeText(this, "Date selected is before Semester starting date!", 1).show();
    		 
    		
    		ld.close();

    		break;
		
		}
	}
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case DATE_DIALOG_ID:
	        return new DatePickerDialog(this,
	                    mDateSetListener,
	                    year, month, day);
	    }
	    return null;
	}
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int Year, 
                                      int monthOfYear, int dayOfmonth) {
                    year = Year;
                    month = monthOfYear;
                    day = dayOfmonth;
                    updateDisplay();
                }  };
                
                public void updateDisplay()
                {
                	
                	date_res=year+"-"+(month+1)+"-"+day;
                	t1.setText("Date: "+day+"-"+(month+1)+"-"+year);
                	dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
            		try {
            			date=dateFormat.parse(date_res);
            		} catch (ParseException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
            		  switch(date.getDay())
            		  {
            		  case 1:
            			  t2.setText("Showing Timetable for Monday");
            			  break;
            		  case 2: 
            			  t2.setText("Showing Timetable for Tuesday");
            			  break;
            		  case 3:
            			  t2.setText("Showing Timetable for Wednesday");
            			  break;
            		  case 4: 
            			  t2.setText("Showing Timetable for Thursday");
            			  break;
            		  case 5:
            			  t2.setText("Showing Timetable for Friday");
            			  break;
            		  default:t2.setText("It's a Weekend!");break;
            		  }

            	}
                
                public boolean onCreateOptionsMenu(Menu dashboardmenu)
            	{
            		MenuInflater inflate=getMenuInflater();
            		inflate.inflate(R.menu.specifyleave, dashboardmenu);
            	    return true;
            	}
            	
            	public boolean onOptionsItemSelected(MenuItem item)
            	{
            		
            		ld=new LeaveDatabase(this);
            		ld.open();
            		dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        			try {
        				date=dateFormat.parse(date_res);
        			} catch (ParseException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			
        			try {
        				enddate=dateFormat.parse(enddatestring);
        			} catch (ParseException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			}
        			
        			try {
        				startdate=dateFormat.parse(resarray[2]);
        			} catch (ParseException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
            		 if(date.after(startdate) || date.equals(startdate)) 
        			  {
        				  if(date.before(enddate) || date.equals(enddate))
        				  {
            		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        			   
        			   builder.setMessage("Delete Holiday?");
        			   builder.setCancelable(true);
        			          builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        			              public void onClick(DialogInterface dialog, int id) {
        			            	DeleteFromLeaveDatabase();
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
        				  Toast.makeText(this, "Sorry! You can't add future dates", 1).show();
        			  } else
        			  Toast.makeText(this, "Date selected is before Semester starting date!", 1).show();
            		 
            		
            		ld.close();

            		return super.onOptionsItemSelected(item);
            		
            	}
               
            	private void DeleteFromLeaveDatabase() {
					ld.open();
					try {
						ld.delete(date_res);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ld.close();
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
					Intent dbac=new Intent(DatePick.this,DashBoard.class);
	        		startActivity(dbac);
	        		}
				
				private void InsertintoLeaveDatabase() {
					// TODO Auto-generated method stub
					try {
						ld.open();
						ld.insert(date_res);
						ld.close();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

}
