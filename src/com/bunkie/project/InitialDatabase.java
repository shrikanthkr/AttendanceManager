package com.bunkie.project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;




public class InitialDatabase {

	private static final String KEY_ROWID = "_id";
	private static final String KEY_NAME = "name";
	private static final String KEY_STARTDATE= "startdate";
	private static final String KEY_MINAT= "minimumattendance";
	private static final String KEY_UNIV ="univname";
	
	private static final String DATABASE_NAME = "InitDatabase1";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "FirstDatabase1";
	private Context ourContext;
	private SQLiteDatabase ourDatabase;
	private DbHelp ourHelper;
	
	class DbHelp extends SQLiteOpenHelper{

		public DbHelp(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase arg0) {
			// TODO Auto-generated method stub
			arg0.execSQL( "CREATE TABLE " + DATABASE_TABLE + " ( "+
			KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			KEY_NAME + " TEXT NOT NULL, " + 
			KEY_UNIV + " TEXT NOT NULL, " +
			KEY_STARTDATE + " DATE NOT NULL, " + 
			KEY_MINAT + " INTEGER NOT NULL );" 					
					);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			arg0.execSQL("DROP TABLE IF EXISTS TimeTable");
			onCreate(arg0);
			
		}
			
		
	}
	public  InitialDatabase(Context c){
		ourContext=c;
	}
	
	public InitialDatabase open(){
		ourHelper = new DbHelp(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		
		return this;
	}
	
	public void close() {
		ourHelper.close();
	}
	
	public void insert(String ins) throws SQLiteException, ParseException{
		String[] x=ins.split("/");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		java.util.Date date =dateFormat.parse(x[2]);
		ContentValues cv=new ContentValues();
		
			cv.put(KEY_NAME, x[0]);
			cv.put(KEY_UNIV, x[1]);
			cv.put(KEY_STARTDATE, dateFormat.format(date));
			cv.put(KEY_MINAT, x[3]);
			
		ourDatabase.insert(DATABASE_TABLE, null, cv);
		
	}
	
	public String getIniData(){
		String result;
		result="";
		Log.i("In initial database", "msg");
		String columns[]={KEY_NAME,KEY_UNIV,KEY_STARTDATE,KEY_MINAT};
		
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		c.moveToFirst();
		int iName=c.getColumnIndex(KEY_NAME);
		Log.i("In initial database", "msg" + iName);
		int iUniv=c.getColumnIndex(KEY_UNIV);
		int isDate=c.getColumnIndex(KEY_STARTDATE);
		int iminAt=c.getColumnIndex(KEY_MINAT);
		Log.i("In initial database", "brfore getting data");
		result=result+c.getString(iName)+"/";
		Log.i("In initial database", "after name");
		result=result+c.getString(iUniv)+"/";
		Log.i("In initial database", "after univ");
		result=result+c.getString(isDate)+"/";
		Log.i("In initial database", "after date");
		result=result+c.getString(iminAt);
		c.close();
		return result;
		
	}
	
	
	 public void delete_All()
	    {
	    	ourDatabase.delete(DATABASE_TABLE, null, null);
	    	SQLiteDatabase.releaseMemory();
	     }
	
}
