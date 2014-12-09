package br.uff.logandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class LogAndroidActivity extends Activity {
    
	private static final String LOGTAG_ANDROID = "LOGTAG_ANDROID";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Log VERBOSE
        Log.v(LOGTAG_ANDROID, "LOG VERBOSE");
        
        // Log DEBUG
        Log.v(LOGTAG_ANDROID, "LOG DEBUG");
        
        // Log INFO
        Log.v(LOGTAG_ANDROID, "LOG INFO");
        
        // Log WARN
        Log.v(LOGTAG_ANDROID, "LOG WARN");
        
        // Log ERROR
        Log.v(LOGTAG_ANDROID, "LOG ERROR");
    }
}