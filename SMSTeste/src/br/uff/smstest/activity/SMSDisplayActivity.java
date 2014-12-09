package br.uff.smstest.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import br.uff.smstest.R;
import br.uff.smstest.sms.Sms;

public class SMSDisplayActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.display_sms_layout);
		
		// Cancels notification on the Status Bar, since it's Activity is on foreground.
		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.cancel(R.string.app_name);
		
		String sender = getIntent().getStringExtra(Sms.KEY_DE);
		String number = getIntent().getStringExtra(Sms.KEY_NUMERO);
		String messageBody = getIntent().getStringExtra(Sms.KEY_MENSAGEM);
		
		TextView fromName = (TextView) findViewById(R.id.fromName);
		fromName.setText(sender);
		
		TextView fromNumber = (TextView) findViewById(R.id.fromNumber);
		fromNumber.setText(number);
		
		TextView message = (TextView) findViewById(R.id.messageBody);
		message.setText(messageBody);
	}
}