package br.uff.intentfiltertest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IntentFilterTesteActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button botaoFeliz = (Button) findViewById(R.id.botaoFeliz);
        botaoFeliz.setOnClickListener(new OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("ACTION_FELIZ");
				startActivity(intent);
			}
		});
        
        Button botaoTriste = (Button) findViewById(R.id.botaoTriste);
        botaoTriste.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		Intent intent = new Intent("ACTION_TRISTE");
        		startActivity(intent);
        	}
        });
    }
}

