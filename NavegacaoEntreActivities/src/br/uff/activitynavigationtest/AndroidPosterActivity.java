package br.uff.activitynavigationtest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AndroidPosterActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.androidposter);
		
		Bundle parametros = getIntent().getExtras();
		
		if (parametros != null) {
			
			String parametroTituloTela = parametros.getString(ActivityPrincipal.PARAMETRO_TITULO_TELA);
			
			TextView tituloTela = (TextView) findViewById(R.id.tituloPoster);
			tituloTela.setText(parametroTituloTela);
		}
		
		Button botaoEfetuarLigacao = (Button) findViewById(R.id.botaoEfetuarLigacao);
		botaoEfetuarLigacao.setOnClickListener(new Button.OnClickListener() {
			
			public void onClick(View v) {
				
				Uri uri = Uri.parse("tel: 02299999999");
				Intent intent = new Intent(Intent.ACTION_CALL, uri);
				startActivity(intent);
			}
		});
	}
}