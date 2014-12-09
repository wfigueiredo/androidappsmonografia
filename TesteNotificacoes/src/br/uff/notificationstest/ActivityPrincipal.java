package br.uff.notificationstest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ActivityPrincipal extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        createNotification();
    }

	private void createNotification() 
	{
		// Notification text on Status Bar
		String tickerText = "Nova mensagem recebida";		
		
		// Notification details
		CharSequence title = "Arsenal Gear";
		CharSequence message = "A verdade por trás do Arsenal Gear";
		
		criarActivityNotificacao(ActivityPrincipal.this, tickerText, title, message);
	}
	
	private void criarActivityNotificacao(Context context, 
										   CharSequence textoTicker, 
										   CharSequence titulo, 
										   CharSequence mensagem) {
		
		NotificationManager notificationManager = 
								(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		Notification notificacao = new Notification(R.drawable.kojima_fox_icon, 
													 textoTicker, 
													 System.currentTimeMillis());

		Intent intent = new Intent(ActivityPrincipal.this, ActivityNotificacao.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(ActivityPrincipal.this, 0, intent, 0);

		notificacao.setLatestEventInfo(ActivityPrincipal.this, titulo, mensagem, pendingIntent);
		notificacao.vibrate = new long[] {300, 400, 500, 700};
		notificacao.defaults |= Notification.DEFAULT_SOUND;
		notificacao.defaults |= Notification.DEFAULT_LIGHTS;

		notificationManager.notify(R.string.app_name, notificacao);
	}
}