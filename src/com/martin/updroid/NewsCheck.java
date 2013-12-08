package com.martin.updroid;

import java.util.Random;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

public class NewsCheck extends IntentService {

	public NewsCheck() {
		super(NewsCheck.class.getSimpleName());
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		NewsSources nSources = new NewsSources(this);
		NewsCollection nColl = nSources.getAllUnread();
		
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
		mBuilder.setAutoCancel(true);
		mBuilder.setContentTitle("New articles");
		mBuilder.setContentText(nColl.getTitles().length + " unread articles");
		mBuilder.setSmallIcon(R.drawable.ic_action_view_as_list);
		mBuilder.setDefaults(Notification.DEFAULT_ALL);
		
		Intent resultIntent = new Intent(NewsCheck.this, MainActivity.class);
		
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(MainActivity.class);
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		mNotificationManager.notify(new Random().nextInt(999999999), mBuilder.build());
	}

}
