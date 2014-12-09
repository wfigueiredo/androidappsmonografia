package br.uff.intentfiltertest2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IntentFilterTestActivity2 extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button botaoIndiferente = (Button) findViewById(R.id.botaoIndiferente);
        botaoIndiferente.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		Intent intent = new Intent("ACTION_INDIFERENTE");
        		startActivity(intent);
        	}
        });
    }
}

