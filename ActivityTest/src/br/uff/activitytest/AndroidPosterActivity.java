package br.uff.activitytest;

import br.uff.android.R;
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
			
			Bundle parameters = getIntent().getExtras();
			
			if (parameters != null)
			{
				String paramScreenTitle = parameters.getString(ActivityPrincipal.PARAM_SCREEN_TITLE);
				
				TextView screenTitle = (TextView) findViewById(R.id.posterScreenTitle);
				screenTitle.setText(paramScreenTitle);
			}
			
			Button makePhoneCallButton = (Button) findViewById(R.id.makeCallButton);
			makePhoneCallButton.setOnClickListener(new Button.OnClickListener() 
			{
				public void onClick(View v) {
					
					Uri uri = Uri.parse("tel: 02185385707");
					Intent intent = new Intent(Intent.ACTION_CALL, uri);
					startActivity(intent);
				}
			});
		}
	}

