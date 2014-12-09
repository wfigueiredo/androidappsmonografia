package br.uff.contentprovidertest.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.uff.contentprovidertest.bd.TabelaLivro;

public class TabelaLivroHelper extends SQLiteOpenHelper {
	
	private final static String DB_NAME = "contentproviderteste.db"; 
	private final static int DB_VERSION = 2;
	
	public TabelaLivroHelper(Context context){
		
		// Uses Android default CursorFactory 
		super(context, DB_NAME, null, DB_VERSION);  
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		TabelaLivro.onCreate(database);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int versaoAntiga, int novaVersao) {
		TabelaLivro.onUpgrade(database, versaoAntiga, novaVersao);
	}
}