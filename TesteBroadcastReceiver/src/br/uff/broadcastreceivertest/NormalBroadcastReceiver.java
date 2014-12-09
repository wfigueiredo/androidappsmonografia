package br.uff.broadcastreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NormalBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, 
					   "NormalBroadcastReceiver recebeu uma mensagem!", 
					   Toast.LENGTH_LONG).show();
	}
}

