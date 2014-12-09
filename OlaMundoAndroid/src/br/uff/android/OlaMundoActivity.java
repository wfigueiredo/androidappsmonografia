package br.uff.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OlaMundoActivity extends Activity {
	
	private static final String MENSAGEM_INICIO = "Olá Mundo! Estou usando Android!";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TextView textView = new TextView(OlaMundoActivity.this);
		textView.setText(MENSAGEM_INICIO);
		
		setContentView(textView);
	}
}