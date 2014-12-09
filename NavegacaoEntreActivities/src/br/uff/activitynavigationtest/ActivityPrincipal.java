package br.uff.activitynavigationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ActivityPrincipal extends Activity {
	
	public final static String PARAMETRO_TITULO_TELA = "TITULO_TELA";
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ImageView androidImageView = (ImageView) findViewById(R.id.androidLogo);
		androidImageView.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				Bundle bundle = new Bundle();
				bundle.putString(PARAMETRO_TITULO_TELA, "Apresentando: Google Android!");
				
				Intent intent = new Intent(ActivityPrincipal.this, AndroidPosterActivity.class);
				intent.putExtras(bundle);
				
				startActivity(intent);
			}
		});
	};
}