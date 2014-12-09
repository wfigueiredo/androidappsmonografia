package br.uff.handlertest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class HandlerActivity extends Activity {
    
	private final static int PROGRESSO_MAXIMO = 100;
	
	private int porcentagem;
	private ProgressDialog progressDialog; 
	private Handler handler;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        progressDialog = criarProgressDialog();
        progressDialog.show();
        iniciarProgresso();
    }
    
	private ProgressDialog criarProgressDialog(){
    	
    	ProgressDialog progressDialog = new ProgressDialog(HandlerActivity.this);
    	
    	progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    	progressDialog.setIcon(R.drawable.android_logo);
    	progressDialog.setTitle(R.string.startingApp);
    	progressDialog.setMessage(getResources().getString(R.string.willTakeSeconds));
    	progressDialog.setIndeterminate(false);
    	progressDialog.setCancelable(false);		
    	progressDialog.setMax(PROGRESSO_MAXIMO);
    	
    	return progressDialog;
    }
    
    /**
     * Inicia a thread de progresso, independente da UI-thread.
     * 
     * Quando o progresso termina, a ProgressDialog é dispensada. 
     */
	private void iniciarProgresso() {
    	
    	handler = new Handler();
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				
				if (porcentagem <= PROGRESSO_MAXIMO){
					
					porcentagem++;
					progressDialog.incrementProgressBy(1);
					handler.postDelayed(this, 50);
				}
				else {
					executarPosProgresso();
				}	
			}
		});
	}
    
	 /**
     * Método invocado assim que a thread de progresso termina.
     */
    private void executarPosProgresso() {
		
    	handler.post(new Runnable()  
    	{  
    		@Override  
    		public void run() {
    			
    			progressDialog.dismiss();  
    			startActivity(new Intent(
    							HandlerActivity.this, ActivityPrincipal.class));
    			finish();
    		}  
    	});  
	}
}