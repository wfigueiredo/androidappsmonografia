package br.uff.sharedpreferencestest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class ActivityPrincipal extends Activity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        Button logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Log.i("SHAREDPREFS_LOG", "[1] Pressed LOGOUT. Will destroy MainActivity.");
				
				PreferencesHelper preferencesHelper = new PreferencesHelper(ActivityPrincipal.this);
				preferencesHelper.atualizarSharedPreference(PreferencesHelper.PREFERENCE_LOGADO, false);
				finish();
				startActivity(new Intent(ActivityPrincipal.this, LoginActivity.class));
			}
		});
    }
}