package br.uff.activityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ActivityB extends Activity {
    
    public final static int OPCAO_ANDROID = 1;
    public final static int OPCAO_IOS = 2;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityb_layout);
        
        final Intent intent = new Intent();
        
        ImageView logotipoAndroid = (ImageView) findViewById(R.id.androidLogo);
    	logotipoAndroid.setOnClickListener(new View.OnClickListener() {
    		
    		public void onClick(View v) {
    			setResult(OPCAO_ANDROID, intent);
    			finish();
    		}
    	});
    	
    	ImageView logotipoApple = (ImageView) findViewById(R.id.appleLogo);
     	logotipoApple.setOnClickListener(new View.OnClickListener() {
     		
     		public void onClick(View v) {
     			setResult(OPCAO_IOS, intent);
     			finish();
     		}
     	});
    }
}