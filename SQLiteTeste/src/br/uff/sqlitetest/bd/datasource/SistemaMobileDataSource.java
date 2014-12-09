package br.uff.sqlitetest.bd.datasource;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.uff.sqlitetest.bd.helper.SistemasMobileDatabaseHelper;
import br.uff.sqlitetest.modelo.SistemaMobile;

public class SistemaMobileDataSource implements ISistemasMobileDataSource {
	
	private SQLiteDatabase database;
	private SistemasMobileDatabaseHelper sqLiteDatabaseHelper;
	
	public SistemaMobileDataSource(Context context){
		
		sqLiteDatabaseHelper = new SistemasMobileDatabaseHelper(context);
	}
	
	public void abrirBDParaLeitura() {
		
		database = sqLiteDatabaseHelper.getReadableDatabase();
	}
	
	public void abrirBDParaEscrita() {
		
		database = sqLiteDatabaseHelper.getWritableDatabase();
	}
	
	public void fechar() {
		
		sqLiteDatabaseHelper.close();
	}
	
	public long criar(SistemaMobile sistemaMobile) {
		
		ContentValues contentValues = new ContentValues();
		
		contentValues.put(SistemasMobileDatabaseHelper.COLUNA_NOME, 
						  sistemaMobile.getName());
		contentValues.put(SistemasMobileDatabaseHelper.COLUNA_EMPRESA, 
						  sistemaMobile.getCompany());
		
		long idTupla = database.insert(SistemasMobileDatabaseHelper.NOME_TABELA, 
									   null, contentValues);
		database.close();
		
		return idTupla;
	}
	
	public List<SistemaMobile> buscarTodos() {
		
		List<SistemaMobile> queryResults = new ArrayList<SistemaMobile>();
		
		Cursor cursor = database.query(SistemasMobileDatabaseHelper.NOME_TABELA, 
									   SistemasMobileDatabaseHelper.COLUNAS, 
									   null, null, 
									   null, null, null);
		
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast()) {
			
			String id = Long.toString(cursor.getLong(0));
			String name = cursor.getString(1);
			String company = cursor.getString(2);
			
			queryResults.add(new SistemaMobile(id, name, company));
			
			cursor.moveToNext();
		}
		
		cursor.close();
		
		return queryResults;
	}
	
	public SistemaMobile buscarPorNome(String nome) {
		
		SistemaMobile sistemaMobile = null;
		
		Cursor cursor = database.query(SistemasMobileDatabaseHelper.NOME_TABELA,
									   SistemasMobileDatabaseHelper.COLUNAS,	
									   SistemasMobileDatabaseHelper.COLUNA_NOME + "=?",
									   new String[]{nome},
									   null, null, null, null);
		
		if (cursor != null) {
			
			cursor.moveToFirst();
			
			String id = Long.toString(cursor.getLong(0));
			String nomeSistema = cursor.getString(1);
			String empresa = cursor.getString(2);
			
			sistemaMobile = new SistemaMobile(id, nomeSistema, empresa);
		}
		
		cursor.close();
		
		return sistemaMobile;
	}
	
	public void remover(String id) {
		
		database.delete(SistemasMobileDatabaseHelper.NOME_TABELA, 
						SistemasMobileDatabaseHelper.COLUNA_ID + "=?", 
						new String[]{id});
		this.fechar();
	}

	public void atualizar(SistemaMobile character) {
		
		ContentValues contentValues = new ContentValues();
		
		contentValues.put(SistemasMobileDatabaseHelper.COLUNA_NOME, character.getName());
		contentValues.put(SistemasMobileDatabaseHelper.COLUNA_EMPRESA, character.getCompany());
		
		database.update(SistemasMobileDatabaseHelper.NOME_TABELA, 
						contentValues, 
						SistemasMobileDatabaseHelper.COLUNA_ID + "=?", 
						new String[]{character.getId()});
		this.fechar();
	}
}
