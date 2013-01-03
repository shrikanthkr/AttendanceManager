package com.bunkie.project;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;



public class NotificationReceiver extends BroadcastReceiver{

	String percent,bunks;
	Bundle b;
	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub
		  b=arg1.getExtras();
		 try
		 {
		
		 percent=b.get("overall").toString();
		 
		 bunks=b.get("totalbunks").toString();
		 
		 }
		 catch(Exception ex)
		 {
			 percent="0";
			 bunks="0";
		 }
		 finally{
			 b.clear();
		 }
		 PendingIntent contentIntent = PendingIntent.getActivity(context, Integer.parseInt(percent)*1000+Integer.parseInt(bunks), arg1, 0);
		 CharSequence tickerText ="Your attendance level is going down!";
	        long when = System.currentTimeMillis();
	        NotificationManager notofManager = (NotificationManager)context. getSystemService(Context.NOTIFICATION_SERVICE);
	        
	        Notification notification = new Notification(R.drawable.alert,tickerText,when );
	        notification.setLatestEventInfo(context, "Overall Percent : "+percent+"%" ,"\tOverall bunks : "+bunks, contentIntent);
	        
	        notification.flags = Notification.FLAG_AUTO_CANCEL;
	        notofManager.notify(Integer.parseInt(percent)*1000+Integer.parseInt(bunks),notification);
	        
	        
	       
		 
	}

}
