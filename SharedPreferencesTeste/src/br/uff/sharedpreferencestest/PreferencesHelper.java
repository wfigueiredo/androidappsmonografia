package br.uff.sharedpreferencestest;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {
	
	private Context context;
	
	protected final static String USUARIO_DEFAULT = "android";
	protected final static String SENHA_DEFAULT = "android";
	
	protected final static String PREFERENCE_LOGIN_AUTOMATICO = "loginAutomatico";
	protected final static String PREFERENCE_LOGADO = "logado";
	
	public PreferencesHelper(Context context) {
		
		this.context = context;
	}
	
	public void atualizarSharedPreference(String preference, boolean valor) {
		
		SharedPreferences configuracoes = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = configuracoes.edit();
		editor.putBoolean(preference, valor);
		editor.commit();
	}
}
