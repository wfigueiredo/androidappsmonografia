package br.uff.intentstest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import br.uff.android.R;

public class IntentsTestActivity extends Activity {
    
    private final static String URL_ANDROID_DEVELOPERS = "http://developer.android.com";
    private final static String PERSON_PHONE_NUMBER = "02298564724";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        criarIntentAberturaDeBrowser();							// Abre web-browser
        criarIntentEfetuarLigacao();							// Efetua uma ligacao para um numero
        criarIntentLerContatoDaAgenda();						// Retorna um contato da agenda
        criarIntentLerContatosDaAgenda();						// Retorna todos os contatos da agenda
        criarIntentBuscaEnderecoGoogleMaps();					// Busca um endereço no Google Maps
    }
    
    private void criarIntentBuscaEnderecoGoogleMaps() {
    	ImageView logotipoGoogleMaps = (ImageView) findViewById(R.id.googleMapsLogo);
    	logotipoGoogleMaps.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			Uri uri = Uri.parse("geo:0,0?q=Avenida+atlantica,brasil");
    			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
    			startActivity(intent);
    		}
    	});
	}

	private void criarIntentLerContatosDaAgenda() {
    	ImageView readContactLogo = (ImageView) findViewById(R.id.phoneContactsLogo);
    	readContactLogo.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View v) 
    		{
    			Uri uri = Uri.parse("content://com.android.contacts/contacts/");
    			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
    			startActivity(intent);
    		}
    	});
	}

	private void criarIntentLerContatoDaAgenda() {
    	ImageView logotipoLerContato = (ImageView) findViewById(R.id.phoneContactLogo);
    	logotipoLerContato.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			Uri uri = Uri.parse("content://com.android.contacts/contacts/77");
    			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
    			startActivity(intent);
    		}
    	});
	}

	private void criarIntentEfetuarLigacao() {
    	ImageView callPhoneLogo = (ImageView) findViewById(R.id.callPhoneLogo);
    	callPhoneLogo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				Uri uri = Uri.parse("tel:".concat(PERSON_PHONE_NUMBER));
				Intent intent = new Intent(Intent.ACTION_CALL, uri);
				startActivity(intent);
			}
		});
	}

	private void criarIntentAberturaDeBrowser() {
		
    	ImageView logotipoWebpage = (ImageView) findViewById(R.id.webpageLogo);
    	logotipoWebpage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse(URL_ANDROID_DEVELOPERS);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});
    }
    
    
}