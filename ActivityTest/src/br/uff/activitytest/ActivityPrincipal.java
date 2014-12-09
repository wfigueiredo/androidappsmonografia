package br.uff.activitytest;

import br.uff.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ActivityPrincipal extends Activity {
    
    private final static String LOG_TAG_ACTIVITY_TEST = "LOGTAG_ActivityTest";
    
    public final static String PARAM_SCREEN_TITLE = "screenTitle";
	
	@Override
    public void onCreate(Bundle bundle) {
		Log.i(LOG_TAG_ACTIVITY_TEST, "onCreate() invoked!");
        super.onCreate(bundle);
        setContentView(R.layout.main);
        
        ImageView androidLogo = (ImageView) findViewById(R.id.androidLogo);
    	androidLogo.setOnClickListener(new View.OnClickListener() 
    	{
    		public void onClick(View v) {
    			
    			Bundle bundle = new Bundle();
    			bundle.putString(PARAM_SCREEN_TITLE, "Presenting: Google Android!");
    			
    			Intent intent = new Intent(Intent.ACTION_VIEW);
    			intent.putExtras(bundle);
    			intent.setClassName(ActivityPrincipal.this, AndroidPosterActivity.class.getName());
    			
    			startActivity(intent);
    		}
    	});
    }
    
    @Override
    protected void onStart() {
    	Log.i(LOG_TAG_ACTIVITY_TEST, "onStart() invoked!");
    	super.onStart();
    }
    
    @Override
    protected void onRestart() {
    	Log.i(LOG_TAG_ACTIVITY_TEST, "onRestart() invoked!");
    	super.onRestart();
    }
    
    @Override
    protected void onResume() {
    	Log.i(LOG_TAG_ACTIVITY_TEST, "onResume() invoked!");
    	super.onResume();
    }
    
    @Override
    protected void onPause() {
    	Log.i(LOG_TAG_ACTIVITY_TEST, "onPause() invoked!");
    	super.onPause();
    }
    
    @Override
    protected void onStop() {
    	Log.i(LOG_TAG_ACTIVITY_TEST, "onStop() invoked!");
    	super.onStop();
    }
    
    @Override
    protected void onDestroy() {
    	Log.i(LOG_TAG_ACTIVITY_TEST, "onDestroy() invoked!");
    	super.onDestroy();
    }
    
}