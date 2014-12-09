package br.uff.activitylifecycletest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class CicloDeVidaTesteActivity extends Activity {
    
	private final static String LOGTAG_CICLO_DE_VIDA_ACTIVITY = "LOGTAG_CICLO_DE_VIDA_ACTIVITY";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		Log.i(LOGTAG_CICLO_DE_VIDA_ACTIVITY, "onCreate() invocado!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    protected void onStart() {
    	Log.i(LOGTAG_CICLO_DE_VIDA_ACTIVITY, "onStart() invocado!");
    	super.onStart();
    }
    
    @Override
    protected void onRestart() {
    	Log.i(LOGTAG_CICLO_DE_VIDA_ACTIVITY, "onRestart() invocado!");
    	super.onRestart();
    }
    
    @Override
    protected void onResume() {
    	Log.i(LOGTAG_CICLO_DE_VIDA_ACTIVITY, "onResume() invocado!");
    	super.onResume();
    }
    
    @Override
    protected void onPause() {
    	Log.i(LOGTAG_CICLO_DE_VIDA_ACTIVITY, "onPause() invocado!");
    	super.onPause();
    }
    
    @Override
    protected void onStop() {
    	Log.i(LOGTAG_CICLO_DE_VIDA_ACTIVITY, "onStop() invocado!");
    	super.onStop();
    }
    
    @Override
    protected void onDestroy() {
    	Log.i(LOGTAG_CICLO_DE_VIDA_ACTIVITY, "onDestroy() invocado!");
    	super.onDestroy();
    }
}