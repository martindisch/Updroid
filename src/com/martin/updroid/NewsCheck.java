package com.martin.updroid;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

public class NewsCheck extends IntentService {

	public NewsCheck() {
		super(NewsCheck.class.getSimpleName());
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.e("FFF", "Download started");
		NewsSources nSources = new NewsSources(getApplicationContext());
		NewsCollection nA3N = nSources.getA3_News();
		NewsCollection nA3D = nSources.getA3_Devhub();
		NewsCollection nSEN = nSources.getSpaceEngineers_News();
		NewsCollection nLN = nSources.getLayer_News();
		Log.e("FFF", "Download finished");
	}

}
