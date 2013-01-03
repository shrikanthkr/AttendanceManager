package com.bunkie.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DataBaseSubjectDredit1 {
	   
	private static final String KEY_ROWID="id";
	private static final String KEY_SUBJECT="subject1";
	private static final String KEY_CREDIT="credit1";
	private static final String KEY_TOTALCLASSES="totalclasses1";
	private static final String DATABASE_NAME="DatabaseSubjectCredit90";
	private static final int DATABASE_VERSION=12;
    private static final String DATABASE_TABLE="DatabaseSubjectCreditTable5";
	private Context ourContext;
	private SQLiteDatabase ourDatabase;
	private DbHelp ourHelper;
	
    private static class DbHelp extends SQLiteOpenHelper {
		public DbHelp(Context context){
			super(context, DATABASE_NAME,null,DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		    }
		@Override
		public void onCreate(SQLiteDatabase arg0) {
			// TODO Auto-generated method stub
			arg0.execSQL("CREATE TABLE  " + DATABASE_TABLE + " ( " +
			    KEY_ROWID  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
		        KEY_SUBJECT + " TEXT NOT NULL, " +
				KEY_CREDIT + " INTEGER NOT NULL, " + 
		        KEY_TOTALCLASSES + " INTEGER NOT NULL " +
		        " );"
		      );
			}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			arg0.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(arg0);
		}

	}
	 
    public DataBaseSubjectDredit1(Context c)
    {
    	ourContext=c;
    }
    
    public DataBaseSubjectDredit1 open()
    {
    	ourHelper=new DbHelp(ourContext);
    	ourDatabase=ourHelper.getWritableDatabase();
    	//Toast.makeText(ourContext, "opened", 3).show();
    	return this;
    }
	
    public void close()
    {
    	ourHelper.close();
    }

    public void insert(String sub,String cre, String tot)
    {
    	ContentValues cv=new ContentValues();
    	cv.put(KEY_SUBJECT,sub);
    	cv.put(KEY_CREDIT, cre);
    	cv.put(KEY_TOTALCLASSES, tot);
    	ourDatabase.insert(DATABASE_TABLE, null,cv);
      }
    
    public String getSubject()
    {
    	String[] columnsubject=new String[]{KEY_SUBJECT};
    	Cursor c=ourDatabase.query(DATABASE_TABLE, columnsubject, null, null, null, null, null);
    	String subject="";
    	int isubject=c.getColumnIndex(KEY_SUBJECT);
    	for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
		{
    		
		subject=subject+c.getString(isubject)+"\n";
		}
    	c.close();
    	return subject;
		
    }
    
    public String getCredit()
    {
    	String[] columncredit=new String[]{KEY_CREDIT};
    	Cursor c=ourDatabase.query(DATABASE_TABLE, columncredit, null, null, null, null, null);
    	String credit="";
    	int icredit=c.getColumnIndex(KEY_CREDIT);
    	for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
    	{
    		credit=credit+c.getString(icredit)+"\n";
    	}
    	c.close();
        return credit;
    }
    public String getTotalClasses()
    {
    	String[] columncredit=new String[]{KEY_SUBJECT,KEY_TOTALCLASSES};
    	Cursor c=ourDatabase.query(DATABASE_TABLE, columncredit, null, null, null, null, null);
    	String totoalClasses="";
    	int icredit=c.getColumnIndex(KEY_TOTALCLASSES);
    	int isub=c.getColumnIndex(KEY_SUBJECT);
    	for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
    	{
    		totoalClasses=totoalClasses+c.getString(isub)+"/"+c.getString(icredit)+"\n";
    	}
    	c.close();
        return totoalClasses;
        
    }
    
    public long SubjectTotalClass(String sub){
    	long total = 0;
    	final String query="SELECT "+ KEY_TOTALCLASSES +" FROM " + DATABASE_TABLE +" WHERE " + KEY_SUBJECT + " LIKE '"+sub+"%' "+ " ;" ;
    	Cursor c=ourDatabase.rawQuery(query, null);
    	c.moveToFirst();
    	int count=c.getCount();
    	int k=0;
		for(k=0;k<count;k++){
			
    	total=Integer.parseInt(c.getString(c.getColumnIndex(KEY_TOTALCLASSES)));
    	c.moveToNext();
		}
    	
    	c.close();
		return total;
    	
    }
    
    public long AllSubjectsTotalClass(){
    	long total = 0;
    	final String query="SELECT "+ KEY_TOTALCLASSES +" FROM " + DATABASE_TABLE + " ;" ;
    	Cursor c=ourDatabase.rawQuery(query, null);
    	c.moveToFirst();
    	int count=c.getCount();
    	int k=0;
		for(k=0;k<count;k++){
			
    	total=total+Integer.parseInt(c.getString(c.getColumnIndex(KEY_TOTALCLASSES)));
    	c.moveToNext();
		}
    	
    	c.close();
		return total;
    	
    }
    
    
    public void delete_All()
    {
    	ourDatabase.delete(DATABASE_TABLE, null, null);
    	SQLiteDatabase.releaseMemory();
     }
    
    
}



