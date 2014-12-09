package br.uff.broadcastreceivertest;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BroadcastActivity extends Activity {

	private NormalBroadcastReceiver normalBroadcastReceiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		normalBroadcastReceiver = new NormalBroadcastReceiver(); 
		registerReceiver(normalBroadcastReceiver, new IntentFilter("NORMAL_BROADCAST_RECEIVER"));
		
		Button botaoBroadcast = (Button) findViewById(R.id.botaoBroadcast);
		botaoBroadcast.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("NORMAL_BROADCAST_RECEIVER");
				sendBroadcast(intent);   
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(normalBroadcastReceiver);
	}
}

