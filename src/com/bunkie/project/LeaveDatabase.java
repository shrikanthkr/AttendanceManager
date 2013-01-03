package com.bunkie.project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;



public class LeaveDatabase {
	
	private static final String KEY_ROWID = "_id";
	private static final String KEY_DATE= "date";
	
	private static final String DATABASE_NAME = "Leave";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "DateTableWithLeave";
	private Context ourContext;
	private SQLiteDatabase ourDatabase;
	private DbHelp ourHelper;
	
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
	
	public LeaveDatabase(Context c){
		ourContext = c;
		}
	public LeaveDatabase open(){
		ourHelper = new DbHelp(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		ourHelper.close();
	}
	

	public void insert(String set) throws ParseException
	{
		int flag=0;
		String[] datearray=null;
		try
		{
		  datearray=getDate();	
		}
		catch(Exception e)
		{
			
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		java.util.Date date =dateFormat.parse(set);
		
		for(int i=0;i<datearray.length;i++)
		{
		  if(dateFormat.format(date).equals(datearray[i]))
		   {
			flag=1;
			 break;
			}
			else
				flag=0;
		}
		
		if(flag==0)
		{
		ContentValues cv = new ContentValues();
		cv.put(KEY_DATE, dateFormat.format(date));
	    ourDatabase.insert(DATABASE_TABLE,null,cv);
	    Toast.makeText(ourContext, "Successfully marked", 1).show();
		}
		else
			Toast.makeText(ourContext, "Already marked as holiday", 1).show();
}
		
	
	
	public String[] getDate()
	{
		final String gettotalcount="SELECT "+ KEY_DATE +  " FROM "+ DATABASE_TABLE +" ORDER BY " + KEY_DATE + " ;" ;
        Cursor c=ourDatabase.rawQuery(gettotalcount, null);
	    c.moveToFirst();
	    int count=c.getCount();
	    String[] result=new String[count];
	    for(int k=0;k<count;k++){
	    	result[k]=c.getString(c.getColumnIndex(KEY_DATE));
			c.moveToNext();
		}
	    c.close();
		return result;
		
	}
	public void delete_All()
    {
    	ourDatabase.delete(DATABASE_TABLE, null, null);
    	SQLiteDatabase.releaseMemory();
    }
	public void delete(String date_res) throws ParseException {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		java.util.Date dateform =dateFormat.parse(date_res);
		
		ourDatabase.delete(DATABASE_TABLE,KEY_DATE + " LIKE '" + dateFormat.format(dateform)+"%' ",null);
		
	}

	
}
