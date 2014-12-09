package br.uff.smstest.sms;

import java.util.HashMap;
import java.util.Map;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import br.uff.smstest.activity.SMSSentActivity;

public class Sms {
	
	private final static String PDUS = "pdus";
	public final static String KEY_DE = "de";
	public final static String KEY_NUMERO = "numero";
	public final static String KEY_MENSAGEM = "mensagem";
	
	public static final int MAX_SMS_CARACTERES = 160;
	
	/**
	 * Envia uma mensagem SMS.
	 * 
	 * @param context
	 * @param recipiente
	 * @param mensagem
	 */
	public void enviarSms(Context context, String recipiente, String mensagem) {
		
		SmsManager smsManager = SmsManager.getDefault();
		
		Intent intent = new Intent(context, SMSSentActivity.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
		
		smsManager.sendTextMessage(recipiente, null, mensagem, pendingIntent, null);
	}

	/**
	 * Receives PDU objects and returns the message body as well as it's sender.
	 * 
	 * @param context
	 * @param intent
	 * @return
	 */
	public Map<String, String> receberSms(Context context, Intent intent) {
	
		Bundle bundle = intent.getExtras();
		Map<String, String> parametros = new HashMap<String, String>(3);
        
		SmsMessage[] msgs = null;
        String origem = null;
        String remetente = null; 
        String corpoMensagem = null;
        
        if (bundle != null) {
        	
            Object[] pdus = (Object[]) bundle.get(PDUS);
            msgs = new SmsMessage[pdus.length];
            
            for (int i=0; i < msgs.length; i++) {
            	
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                
                origem = msgs[i].getOriginatingAddress();
                corpoMensagem = msgs[i].getMessageBody().toString();
                
                Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                        					   Uri.encode(origem));
                
                String[] projection = new String[] {ContactsContract.PhoneLookup.DISPLAY_NAME};

                Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
                
                if (cursor != null) {
                    
                	if (cursor.moveToFirst()) {
                		remetente = cursor.getString(0);
                	}

                    cursor.close();
                }
            }
            
            parametros.put(KEY_DE, remetente);
            parametros.put(KEY_NUMERO, origem);
            parametros.put(KEY_MENSAGEM, corpoMensagem);
        }
        
        return parametros;
	}
}
