package br.uff.activityinteraction;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivityDeInteracao extends Activity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ImageView androidLogo = (ImageView) findViewById(R.id.androidLogo);
    	androidLogo.setImageResource(R.drawable.android_small_logo);
    	androidLogo.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(ActivityDeInteracao.this, 
							   "Ola! Sou o Android!", Toast.LENGTH_SHORT).show();
			}
		});
    }
}