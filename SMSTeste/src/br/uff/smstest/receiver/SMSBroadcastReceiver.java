package br.uff.smstest.receiver;

import java.util.Map;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import br.uff.smstest.notification.NotificationFactory;
import br.uff.smstest.sms.Sms;

public class SMSBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Sms sms = new Sms();
		Map<String, String> messageParameteres = sms.receberSms(context, intent);
		
		String sender = messageParameteres.get(Sms.KEY_DE);
		String number = messageParameteres.get(Sms.KEY_NUMERO);
		String messageBody = messageParameteres.get(Sms.KEY_MENSAGEM);
		
		NotificationFactory notificationFactory = new NotificationFactory();
		notificationFactory.createNotification(context, sender, number, messageBody);
	}
}