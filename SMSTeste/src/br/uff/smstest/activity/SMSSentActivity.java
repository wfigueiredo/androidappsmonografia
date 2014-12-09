package br.uff.smstest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import br.uff.smstest.R;

public class SMSSentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.message_sent);
		
		Button sendButton = (Button) findViewById(R.id.sendAnotherMessage);
		sendButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(SMSSentActivity.this, SMSMainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
