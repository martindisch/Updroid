package com.martin.updroid;

import java.util.Random;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

public class NewsCheck extends IntentService {

	public NewsCheck() {
		super(NewsCheck.class.getSimpleName());
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		NewsSources nSources = new NewsSources(this);
		NewsCollection nColl = nSources.getAllUnnotified();

		if (!(nColl.getTitles().length == 0)) {
			NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
					this);
			mBuilder.setAutoCancel(true);
			
			// Check for null
			String[] filtered = new String[nColl.getTitles().length];
			int counter = 0;
			for (int i = 0; i < nColl.getTitles().length; i++) {
				if (!nColl.getTitles()[i].contentEquals("null")) {
					filtered[counter] = nColl.getTitles()[i];
					counter++;
				}
			}
			
			if (counter > 0) {
				mBuilder.setContentTitle(counter + " new articles");
				String news = "";
				for (int i = 0; i < counter; i++) {
					news += filtered[i] + "\n";
				}
				mBuilder.setContentText(news);
				mBuilder.setSmallIcon(R.drawable.ic_action_view_as_list);
				mBuilder.setDefaults(Notification.DEFAULT_ALL);
				// Make it big
				mBuilder.setStyle(new NotificationCompat.BigTextStyle()
	            .bigText(news));

				Intent resultIntent = new Intent(NewsCheck.this, MainActivity.class);

				TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
				stackBuilder.addParentStack(MainActivity.class);
				stackBuilder.addNextIntent(resultIntent);
				PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
						0, PendingIntent.FLAG_UPDATE_CURRENT);

				mBuilder.setContentIntent(resultPendingIntent);
				NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

				mNotificationManager.notify(new Random().nextInt(999999999),
						mBuilder.build());
				
				SharedPreferences spLibrary = getApplication().getSharedPreferences("Notified", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = spLibrary.edit();
				for (int i = 0; i < counter; i++) {
					editor.putBoolean(filtered[i], true);
				}
				editor.commit();
			}
			
		}

	}

}
