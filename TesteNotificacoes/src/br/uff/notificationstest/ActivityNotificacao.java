package br.uff.notificationstest;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

public class ActivityNotificacao extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_layout);
		
		NotificationManager notificationManager = 
						(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		notificationManager.cancel(R.string.app_name);
	}
}