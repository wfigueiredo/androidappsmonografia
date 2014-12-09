package br.uff.alarmtest;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

public class ExibicaoAlarmeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm_display);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		Intent intent = new Intent("AGENDAR_ALARME"); 
		PendingIntent pendingIntent = 
					PendingIntent.getBroadcast(ExibicaoAlarmeActivity.this, 0, intent, 0);
		
		AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
    	alarme.cancel(pendingIntent);
	}
}