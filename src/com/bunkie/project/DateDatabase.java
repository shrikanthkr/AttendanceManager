package com.bunkie.project;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DateDatabase {

	private static final String KEY_ROWID = "_id";
	private static final String KEY_SUBJECT = "subject";
	private static final String KEY_DATE= "date";
	private static final String KEY_HOUR= "hour";
	String result;
	
	private static final String DATABASE_NAME = "Date";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "DateTableWithHour";
	private Context ourContext;
	private SQLiteDatabase ourDatabase;
	private DbHelp ourHelper;
	
	String[] subarray;
	int count;
	Date d;
	
	private static class DbHelp extends SQLiteOpenHelper {
		public DbHelp(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase arg0) {
			// TODO Auto-generated method stub
			
			arg0.execSQL("CREATE TABLE " + DATABASE_TABLE + " ( "+
			KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			KEY_SUBJECT + " TEXT NOT NULL, " + 
			KEY_HOUR + " INTEGER NOT NULL, " +
			KEY_DATE + " DATE NOT NULL );"
			
			);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			
			arg0.execSQL("DROP TABLE IF EXISTS TimeTable");
			onCreate(arg0);
			
		}
		
	}
	
	public DateDatabase(Context c){
		ourContext = c;
		
	}
	
	public DateDatabase open(){
		ourHelper = new DbHelp(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		
		return this;
	}
	
	public void close() {
		ourHelper.close();
	}
	
	
	public void insert(String set) throws ParseException{
		String[] subjectarray = null;
		String[] datearray=null;
		String[] hourarray=null;
		int flag=0;
		set=set.trim();
		subarray=set.split("/");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		java.util.Date date =dateFormat.parse(subarray[subarray.length-1]);
		ContentValues cv = new ContentValues();
		
			try
			{
			subjectarray=getSubject().split("/");
			datearray=getDate().split("/");
			hourarray=getHour().split("/");
			}
			catch(Exception e)
			{
				
			}
			
		
		
		
		for(int i=0;i<subarray.length-1;i=i+2){
			
			for(int j=0;j<subjectarray.length;j++)
			{
				
				if(subarray[i].equals(subjectarray[j]) && subarray[i+1].equals(hourarray[j]) && dateFormat.format(date).equals(datearray[j]))
				{
				  flag=1;
				  break;
				}
				else
					flag=0;
			}
				if(flag==0)
				{
			     cv.put(KEY_SUBJECT, subarray[i]);
			     cv.put(KEY_HOUR, subarray[i+1]);
		         cv.put(KEY_DATE, dateFormat.format(date));
			     ourDatabase.insert(DATABASE_TABLE,null,cv);
			     }
				else
					Toast.makeText(ourContext, "This Bunk has already been entered!", 0).show();
			}
		}
		
	
		
	 
	public String getSub(){
		
		
		final String gettotalcount="SELECT "+ KEY_SUBJECT + ",COUNT (*)"    + " FROM "+ DATABASE_TABLE+" GROUP BY "+ KEY_SUBJECT + " ;" ;
		
		Cursor c=ourDatabase.rawQuery(gettotalcount, null);
		c.moveToFirst();
		int count=c.getCount();
	     result="";
		 c.moveToFirst();
		 int k=0;
		for(k=0;k<count;k++){
			
			result=result+c.getString(0)+"/";
			c.moveToNext();
		}
		 
		c.close();
		return result;
	}
	
	public String getSubCount()
	{
		
		final String gettotalcount="SELECT "+ KEY_SUBJECT + ",COUNT (*)"    + " FROM "+ DATABASE_TABLE+" GROUP BY "+ KEY_SUBJECT + " ;" ;
        Cursor c=ourDatabase.rawQuery(gettotalcount, null);
	    c.moveToFirst();
	    int count=c.getCount();
	    result="";
	    for(int k=0;k<count;k++){
	    	result=result+c.getString(0)+"/"+c.getString(1)+"\n";
			c.moveToNext();
	    }
	    c.close();
		return result;
		
	}
	public String getHour()
	{
		final String gettotalcount="SELECT "+ KEY_HOUR +  " FROM "+ DATABASE_TABLE +" ORDER BY " + KEY_DATE + " ;" ;
        Cursor c=ourDatabase.rawQuery(gettotalcount, null);
	    c.moveToFirst();
	    int count=c.getCount();
	    result="";
	    for(int k=0;k<count;k++){
	    	result=result+c.getString(c.getColumnIndex(KEY_HOUR))+"/";
			c.moveToNext();
	    }
	    c.close();
		return result;
		
	}
	public String getSubject()
	{
		final String gettotalcount="SELECT "+ KEY_SUBJECT +  " FROM "+ DATABASE_TABLE +" ORDER BY " + KEY_DATE + " ;" ;
        Cursor c=ourDatabase.rawQuery(gettotalcount, null);
	    c.moveToFirst();
	    int count=c.getCount();
	    result="";
	    for(int k=0;k<count;k++){
	    	result=result+c.getString(c.getColumnIndex(KEY_SUBJECT))+"/";
			c.moveToNext();
	    }
	    c.close();
		return result;
		
	}
	
	public String getDate()
	{
		final String gettotalcount="SELECT "+ KEY_DATE +  " FROM "+ DATABASE_TABLE +" ORDER BY " + KEY_DATE + " ;" ;
        Cursor c=ourDatabase.rawQuery(gettotalcount, null);
	    c.moveToFirst();
	    int count=c.getCount();
	    result="";
	    for(int k=0;k<count;k++){
	    	result=result+c.getString(c.getColumnIndex(KEY_DATE))+"/";
			c.moveToNext();
	    }
	    c.close();
		return result;
		
	}
	
	public String getCount()
	{
		
		final String gettotalcount="SELECT "+ KEY_SUBJECT + ",COUNT (*)"    + " FROM "+ DATABASE_TABLE+" GROUP BY "+ KEY_SUBJECT + " ;" ;
        Cursor c=ourDatabase.rawQuery(gettotalcount, null);
	    c.moveToFirst();
	    int count=c.getCount();
	    result="";
	    for(int k=0;k<count;k++){
	    	result=result+c.getString(1)+"/";
			c.moveToNext();
	    }
	    c.close();
		return result;
		
	}
	public void deleteSpecific(String set) throws ParseException{
		String[] setarray=set.split("/");
		String sub;
		String date;
		String hour;
		sub=setarray[0];
		date=setarray[1];
		hour=setarray[2];
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		java.util.Date dateform =dateFormat.parse(date);
		
		ourDatabase.delete(DATABASE_TABLE, KEY_SUBJECT +" LIKE '"+sub+"%' "+ " AND " +  KEY_DATE + " LIKE '" + dateFormat.format(dateform)+"%' " + " AND " + KEY_HOUR + " = " + Integer.parseInt(hour) , null);
	    
	    
	}
	 public void delete_All()
	    {
	    	ourDatabase.delete(DATABASE_TABLE, null, null);
	    	SQLiteDatabase.releaseMemory();
	    }
	
	



	
	
	
	
	
	
	
	
}
