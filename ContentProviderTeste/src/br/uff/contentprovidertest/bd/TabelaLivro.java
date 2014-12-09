package br.uff.contentprovidertest.bd;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.uff.contentprovidertest.helper.TabelaLivroHelper;

public class TabelaLivro {
	
	public final static String NOME_TABELA = "livro";
	
	public final static String COLUNA_ID = "_id"; 
	public final static String COLUNA_TITULO = "titulo"; 
	public final static String COLUNA_AUTOR = "autor";
	public final static String COLUNA_EDITORA = "editora";
	
	public final static String[] TODAS_COLUNAS = {COLUNA_ID, COLUNA_TITULO, COLUNA_AUTOR, COLUNA_EDITORA};
	
	public static final String SQL_CREATE_TABLE = "CREATE TABLE " + NOME_TABELA + "(" +
										COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
										COLUNA_TITULO + " TEXT NOT NULL, " +
										COLUNA_AUTOR + " TEXT, " +
										COLUNA_EDITORA + " TEXT);";

	public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + NOME_TABELA;

	public static void onCreate(SQLiteDatabase database) {
		
		database.execSQL(SQL_CREATE_TABLE);
	}
	
	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		
		Log.w(TabelaLivroHelper.class.getName(), 
						"Atualizando a base dados. Saindo da versão " + oldVersion + 
						" e migrando para a versão " + newVersion + ". " +
					    "Este processo irá destruir todas as informações antigas.");
		
		database.execSQL(SQL_DROP_TABLE);
		
		onCreate(database);
	}
}