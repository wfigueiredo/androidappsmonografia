package br.uff.smstest.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import br.uff.smstest.R;
import br.uff.smstest.activity.SMSDisplayActivity;
import br.uff.smstest.sms.Sms;

public class NotificationFactory {
	
	public void createNotification(Context context, String sender, String number, String messageBody) {
		
		Resources resources = context.getResources();
		
		// Notification text on Status Bar
		String tickerText = resources.getString(R.string.mensagemRecebida);		
		
		// Notification details
		CharSequence title = resources.getString(R.string.mensagemProveniente);
		
		startNotificationActivity(context, tickerText, title, sender, number, messageBody);
	}
	
	private void startNotificationActivity(Context context, CharSequence tickerText, CharSequence title, 
										   CharSequence sender, String number, String messageBody) {
		
		// Retrieves the NotificationManager, which is a System Service.
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		// Creates a new Notification.
		Notification notification = new Notification(R.drawable.incoming_sms_icon, tickerText, System.currentTimeMillis());

		// Creates a PendingIntent object, which represents the executed Activity in case of notification selection by the user.
		Intent intent = new Intent(context, SMSDisplayActivity.class);
		intent.putExtra(Sms.KEY_DE, sender);
		intent.putExtra(Sms.KEY_NUMERO, number);
		intent.putExtra(Sms.KEY_MENSAGEM, messageBody);
		
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// General Notification info
		notification.setLatestEventInfo(context, title, sender, pendingIntent);

		// Device vibration.
		// # waits for 300ms -> vibrates 400ms   
		// # waits for 500ms -> vibrates 700ms   
		notification.vibrate = new long[] {300, 400, 500, 700};
		
		// Device sound
		notification.defaults |= Notification.DEFAULT_SOUND;
		
		// Device flashing LED lights.
		notification.defaults |= Notification.DEFAULT_LIGHTS;

		// Post a notification to be shown in the status bar.
		notificationManager.notify(R.string.app_name, notification);
	}
}