package br.uff.broadcastreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class SistemaCarregadoBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) 
	{
		Toast.makeText(context, "Sistema Operacional carregado com sucesso!", 
				Toast.LENGTH_LONG).show();
	}
}



