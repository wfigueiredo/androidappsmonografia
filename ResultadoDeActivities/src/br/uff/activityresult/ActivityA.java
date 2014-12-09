package br.uff.activityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivityA extends Activity {
    
	public final static String PARAMETRO_SISTEMA = "sistema";
	private final static int SISTEMA_ESCOLHIDO_REQUEST_CODE = 0;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitya_layout);
        
        ImageView iconeProximo = (ImageView) findViewById(R.id.nextLogo);
    	iconeProximo.setOnClickListener(new View.OnClickListener() {
    		
    		public void onClick(View v) {
    			Intent intent = new Intent(Intent.ACTION_VIEW);
    			intent.setClassName(ActivityA.this, ActivityB.class.getName());
    			startActivityForResult(intent, 0);      
    		}
    	});
	}
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	
    	if (requestCode == SISTEMA_ESCOLHIDO_REQUEST_CODE) {
    		
    		if (resultCode == ActivityB.OPCAO_ANDROID)
    			Toast.makeText(ActivityA.this, 
    							"Sistema selecionado: ANDROID", Toast.LENGTH_LONG).show();
    		else if (resultCode == ActivityB.OPCAO_IOS)
    			Toast.makeText(ActivityA.this, 
    							"Sistema selecionado: iOS", Toast.LENGTH_LONG).show();
    		
    		super.onActivityResult(requestCode, resultCode, intent);
    	}
    }
}