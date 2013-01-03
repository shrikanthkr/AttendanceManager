package com.bunkie.project;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class TimeTableDatabase {

	private static final String KEY_ROWID = "_id";
	private static final String KEY_MONDAY = "monday";
	private static final String KEY_TUESDAY = "tuesday";
	private static final String KEY_WEDNESDAY = "wednesday";
	private static final String KEY_THURSDAY = "thursday";
	private static final String KEY_FRIDAY = "friday";
	
	private static final String DATABASE_NAME = "TimeTableDatabase_1";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "TimeTable_1";
	private Context ourContext;
	private SQLiteDatabase ourDatabase;
	private DbHelp ourHelper;

	String[] mon;
	String[] tue;
	String[] wed;
	String[] thu;
	String[] fri;
	int count=0;

	private static class DbHelp extends SQLiteOpenHelper {
		public DbHelp(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase arg0) {
			// TODO Auto-generated method stub
			arg0.execSQL("CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + " ( "
					+ KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_MONDAY + " TEXT NOT NULL, " + KEY_TUESDAY
					+ " TEXT NOT NULL, " + KEY_WEDNESDAY + " TEXT NOT NULL, "
					+ KEY_THURSDAY + " TEXT NOT NULL, " + KEY_FRIDAY
					+ " TEXT NOT NULL );");

		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			arg0.execSQL("DROP TABLE IF EXISTS TimeTable");
			onCreate(arg0);
		}
	}

	public TimeTableDatabase(Context c) {

		ourContext = c;
	}

	public TimeTableDatabase open() {
		ourHelper = new DbHelp(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		ourHelper.close();
	}

	public void insert(String monday, String tuesday, String wednesday,
			String thursday, String friday) {
		
		mon = monday.split("/");
		tue = tuesday.split("/");
		wed = wednesday.split("/");
		thu = thursday.split("/");
		fri = friday.split("/");
		ContentValues cv = new ContentValues();
		for(int i=0;i<mon.length;i++)
		{
			cv.put(KEY_MONDAY,mon[i]);
			cv.put(KEY_TUESDAY, tue[i]);
			cv.put(KEY_WEDNESDAY, wed[i]);
			cv.put(KEY_THURSDAY, thu[i]);
			cv.put(KEY_FRIDAY, fri[i]);
			ourDatabase.insert(DATABASE_TABLE, null, cv);
		}
    }

	

	 public String getMondayPeriod()
	{
	  String[] columnmonday=new String[]{KEY_MONDAY};
	  Cursor c1=ourDatabase.query(DATABASE_TABLE, columnmonday, null, null, null, null, null);
	  String result="";
	  int imonday=c1.getColumnIndex(KEY_MONDAY);
	  for(c1.moveToFirst();!c1.isAfterLast();c1.moveToNext())
	  {
		 result=result+c1.getString(imonday)+"\n"; 
	   }		
	  c1.close();
	  return result;
	}
	
	public String getTuesdayPeriod()
	{
		String[] columntuesday=new String[]{KEY_TUESDAY};
		  Cursor c1=ourDatabase.query(DATABASE_TABLE, columntuesday, null, null, null, null, null);
		  String result="";
		  int ituesday=c1.getColumnIndex(KEY_TUESDAY);
		  for(c1.moveToFirst();!c1.isAfterLast();c1.moveToNext())
		  {
			 result=result+c1.getString(ituesday)+"\n"; 
		  }		
		  c1.close();
		  return result;
		
	}
	
	public String getWednesdayPeriod()
	{
		String[] columnwednesday=new String[]{KEY_WEDNESDAY};
		  Cursor c1=ourDatabase.query(DATABASE_TABLE, columnwednesday, null, null, null, null, null);
		  String result="";
		  int iwednesday=c1.getColumnIndex(KEY_WEDNESDAY);
		  for(c1.moveToFirst();!c1.isAfterLast();c1.moveToNext())
		  {
			 result=result+c1.getString(iwednesday)+"\n"; 
		  }		
		  c1.close();
		  return result;
		
	}
	
	public String getThursdayPeriod()
	{
		String[] columnthursday=new String[]{KEY_THURSDAY};
		  Cursor c1=ourDatabase.query(DATABASE_TABLE, columnthursday, null, null, null, null, null);
		  String result="";
		  int ithursday=c1.getColumnIndex(KEY_THURSDAY);
		  for(c1.moveToFirst();!c1.isAfterLast();c1.moveToNext())
		  {
			 result=result+c1.getString(ithursday)+"\n"; 
		  }		
		  c1.close();
		  return result;
		
	}
	
	public String getFridayPeriod()
	{
		String[] columnfriday=new String[]{KEY_FRIDAY};
		  Cursor c1=ourDatabase.query(DATABASE_TABLE, columnfriday, null, null, null, null, null);
		  String result="";
		  int ifriday=c1.getColumnIndex(KEY_FRIDAY);
		  for(c1.moveToFirst();!c1.isAfterLast();c1.moveToNext())
		  {
			 result=result+c1.getString(ifriday)+"\n"; 
		  }		
		  c1.close();
		  return result;
		
	}
	public void delete_All()
    {
    	ourDatabase.delete(DATABASE_TABLE, null, null);
    	SQLiteDatabase.releaseMemory();
    }

	
}
