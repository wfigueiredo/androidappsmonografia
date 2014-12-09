package br.uff.alarmtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmeBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		
		Intent intent = new Intent("EXIBIR_ALARME");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		context.startActivity(intent);
	}
}