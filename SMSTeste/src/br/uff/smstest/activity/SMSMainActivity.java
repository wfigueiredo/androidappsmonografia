package br.uff.smstest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.uff.smstest.R;
import br.uff.smstest.sms.Sms;

public class SMSMainActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		
		final EditText recipienteInput = (EditText) findViewById(R.id.recipientInput);
		final EditText mensagemInput = (EditText) findViewById(R.id.messageInput);
		
		Button sendButton = (Button) findViewById(R.id.send);
		sendButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String recipiente = recipienteInput.getText().toString();
				String mensagem = mensagemInput.getText().toString();
				
				if (mensagem.length() <= Sms.MAX_SMS_CARACTERES) {
					
					Sms sms = new Sms();
					sms.enviarSms(SMSMainActivity.this, recipiente, mensagem);
					
					Intent intent = new Intent(SMSMainActivity.this, SMSSentActivity.class);
					startActivity(intent);
					finish();
				}
				else {
					Toast.makeText(SMSMainActivity.this, getResources().getString(R.string.excedidoMaxCaracteres), Toast.LENGTH_SHORT).show();
				}
			}
		});
    }
}