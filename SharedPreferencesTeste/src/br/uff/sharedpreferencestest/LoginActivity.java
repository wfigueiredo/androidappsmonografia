package br.uff.sharedpreferencestest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends Activity {
	
	private CheckBox checkboxManterLogado;
	
	private final static String MAIN_ACTIVITY = "MAIN_ACTIVITY";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		checkboxManterLogado = (CheckBox) findViewById(R.id.keepLoggedIn);
		
		SharedPreferences preferenceAutoLogin = getSharedPreferences(
												 PreferencesHelper.PREFERENCE_LOGIN_AUTOMATICO, 
												 MODE_PRIVATE);
		
		boolean loginAutomatico = preferenceAutoLogin.getBoolean(
												  PreferencesHelper.PREFERENCE_LOGIN_AUTOMATICO, 
												  false);
		
		SharedPreferences preferenceLogado = getSharedPreferences(
												  PreferencesHelper.PREFERENCE_LOGADO, 
												  MODE_PRIVATE);
		
		boolean usuarioLogado = preferenceLogado.getBoolean(
												   PreferencesHelper.PREFERENCE_LOGADO, 
												   false);
		
		if (loginAutomatico) {
			
			checkboxManterLogado.setChecked(true);
		}
		
		if (loginAutomatico && usuarioLogado) {
			
			startActivity(new Intent(MAIN_ACTIVITY));
		}
		else {
			
			final EditText inputUsuario = (EditText) findViewById(R.id.usernameInput);
			final EditText inputSenha = (EditText) findViewById(R.id.passwordInput);
			
			Button botaoLogin = (Button) findViewById(R.id.submitLogin);
			botaoLogin.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					String username = (String) inputUsuario.getText().toString();
					String password = (String) inputSenha.getText().toString();
					
					if (PreferencesHelper.USUARIO_DEFAULT.equals(username) && 
						PreferencesHelper.SENHA_DEFAULT.equals(password)) {
						
						PreferencesHelper preferencesHelper = new PreferencesHelper(LoginActivity.this);
						boolean autoLoginEnabled = checkboxManterLogado.isChecked(); 
						
						if (autoLoginEnabled){
							
							preferencesHelper.atualizarSharedPreference(PreferencesHelper.PREFERENCE_LOGIN_AUTOMATICO, true);
						}
						else {
							
							preferencesHelper.atualizarSharedPreference(PreferencesHelper.PREFERENCE_LOGIN_AUTOMATICO, false);
						}
						
						preferencesHelper.atualizarSharedPreference(PreferencesHelper.PREFERENCE_LOGADO, true);
						startActivity(new Intent(MAIN_ACTIVITY));
					}
					else {
						createLoginErrorDialog();
					}
				}
			});
		}
	}
	
	private void createLoginErrorDialog(){
    	
		final AlertDialog.Builder adb = new AlertDialog.Builder(LoginActivity.this);
		
        adb.setTitle(R.string.loginErro);
        adb.setMessage(R.string.loginErrorCausa);
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.setCancelable(true);
        
        AlertDialog alertDialog = adb.create();
        alertDialog.show();		
    }
}