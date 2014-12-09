package br.uff.sqlitetest.bd.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SistemasMobileDatabaseHelper extends SQLiteOpenHelper {
	
	private final static String BD_NOME = "sqlitetest.db"; 
	
	public final static int BD_VERSAO = 2;
	
	public final static String NOME_TABELA = "mobileoperationalsystem";
	
	public final static String COLUNA_ID = "_id"; 
	public final static String COLUNA_NOME = "nome"; 
	public final static String COLUNA_EMPRESA = "empresa";
	
	public final static String[] COLUNAS = {COLUNA_ID, COLUNA_NOME, COLUNA_EMPRESA};
	
	public static final String SQL_CREATE_TABLE = 
										"CREATE TABLE " + NOME_TABELA + "(" +
										COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
										COLUNA_NOME + " TEXT NOT NULL, " +
										COLUNA_EMPRESA + " TEXT);";

	public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + NOME_TABELA;

	public SistemasMobileDatabaseHelper(Context context){
		super(context, BD_NOME, null, BD_VERSAO);  
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(SQL_CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int versaoAntiga, int novaVersao) {
		
		Log.w(SistemasMobileDatabaseHelper.class.getName(), 
						"Atualizando a base de dados da versão " + versaoAntiga + 
						" para a versão " + novaVersao + ". Este processo " +
						"irá destruir todas as informações antigas.");
		
		database.execSQL(SQL_DROP_TABLE);
		
		onCreate(database);
	}
}