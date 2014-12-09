package br.uff.unboundservicetest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.uff.unboundservicetest.R;

public class ServicoDownloadActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_servico);
		
		Button botaoDownload = (Button) findViewById(R.id.botaoDownload);
		botaoDownload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent("BAIXAR_ARQUIVO");
				startService(intent);
				finish();
			}
		});
	}
}
