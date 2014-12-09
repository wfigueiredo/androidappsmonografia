package br.uff.contentprovidertest.provider;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import br.uff.contentprovidertest.bd.TabelaLivro;
import br.uff.contentprovidertest.helper.TabelaLivroHelper;

public class LivroProvider extends ContentProvider {

	private TabelaLivroHelper tabelaLivroHelper;
	
	private static HashMap<String, String> projecaoColunas = new HashMap<String, String>();
	
	private static final int LIVROS = 1;
	private static final int LIVRO_ID = 2;
	
	// 	a URI será do tipo:
	//
	//   [ content://br.uff.contentproviderteste.provider.LivroProvider/livros ]
	//
	private static final String PATH = "livros";
	public final static String AUTHORITY = "br.uff.contentproviderteste.provider.LivroProvider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + LivroProvider.AUTHORITY + "/" + PATH);
	
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.books";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.books";

	private final static UriMatcher uriMatcher;
	
	static {
		
		// Urimatcher
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, PATH, LIVROS);
		uriMatcher.addURI(AUTHORITY, PATH + "/#", LIVRO_ID);
		
		// Projeção da tabela
		projecaoColunas.put(TabelaLivro.COLUNA_ID, TabelaLivro.COLUNA_ID);
		projecaoColunas.put(TabelaLivro.COLUNA_TITULO, TabelaLivro.COLUNA_TITULO);
		projecaoColunas.put(TabelaLivro.COLUNA_AUTOR, TabelaLivro.COLUNA_AUTOR);
		projecaoColunas.put(TabelaLivro.COLUNA_EDITORA, TabelaLivro.COLUNA_EDITORA);
	}
	
	@Override
	public boolean onCreate() {
		
		tabelaLivroHelper = new TabelaLivroHelper(getContext());
		return true;
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		SQLiteDatabase database = tabelaLivroHelper.getWritableDatabase();
		long rowId = 0;
		
		switch (uriMatcher.match(uri)) {
		
			case LIVROS: {
				rowId = database.insert(TabelaLivro.NOME_TABELA, null, values);
				break;
			}	
				
			default:
				throw new IllegalArgumentException("URI desconhecida: " + uri);
		}
		
		if (rowId > 0) {
			
			Uri livroUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(uri, null);
			
			return livroUri;
		}
		
		throw new SQLException("Falha na inserção de registro em: " + uri);
	}
	
	@Override
	public Cursor query(Uri uri, String[] projecao, String selecao, String[] argumentos, String ordenacao) {
		
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		
		queryBuilder.setTables(TabelaLivro.NOME_TABELA);
		queryBuilder.setProjectionMap(projecaoColunas);

		switch (uriMatcher.match(uri)) {
		
			case LIVROS: {
				Log.i("LOGTAG_CONTENTPROVIDER", ">>> QUERY Livros");
				break;
			}	
				
			case LIVRO_ID: {
				Log.i("LOGTAG_CONTENTPROVIDER", ">>> QUERY Livro_ID");
				// Adding the ID to the original query
				queryBuilder.appendWhere(TabelaLivro.COLUNA_ID + "=" + uri.getLastPathSegment());
				break;
			}	
				
			default:
				throw new IllegalArgumentException("URI desconhecida: " + uri);
		}
		
		SQLiteDatabase database = tabelaLivroHelper.getReadableDatabase();
		
		Cursor cursor = queryBuilder.query(database, projecao, selecao, argumentos, null, null, ordenacao);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		
		return cursor;	
	}
	
	@Override
	public int delete(Uri uri, String selecao, String[] argumentos) {
		
		SQLiteDatabase database = tabelaLivroHelper.getWritableDatabase();
		
		int registrosExcluidos = 0;
		
		switch (uriMatcher.match(uri)) {
		
			case LIVROS: {
				registrosExcluidos = database.delete(TabelaLivro.NOME_TABELA, selecao, argumentos);
				break;
			}
			
			case LIVRO_ID: {
				
				String livroId = uri.getLastPathSegment();
				String whereClause = TabelaLivro.COLUNA_ID + "=" + livroId;
				
				if (TextUtils.isEmpty(selecao)) {
					registrosExcluidos = database.delete(TabelaLivro.NOME_TABELA, whereClause, null);
					
				} else {
					registrosExcluidos = database.delete(TabelaLivro.NOME_TABELA, whereClause + " AND " + 
												  selecao, argumentos);
				}
				
				break;
			}
			
			default:
				throw new IllegalArgumentException("URI desconhecida: " + uri);
		}
		
		getContext().getContentResolver().notifyChange(uri, null);

		return registrosExcluidos;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		
		SQLiteDatabase database = tabelaLivroHelper.getWritableDatabase();
		
		int updatedRows = 0;
		
		switch (uriMatcher.match(uri)) {
		
			case LIVROS: {
				updatedRows = database.update(TabelaLivro.NOME_TABELA, values, selection, selectionArgs);
				break;
			}
			
			case LIVRO_ID: {
				String bookId = uri.getLastPathSegment();
				String whereClause = TabelaLivro.COLUNA_ID + "=" + bookId;
				
				if (TextUtils.isEmpty(selection)) {
					updatedRows = database.update(TabelaLivro.NOME_TABELA, values, whereClause, null);
					
				} else {
					updatedRows = database.update(TabelaLivro.NOME_TABELA, values, whereClause + " AND " + selection, selectionArgs);
				}
				
				break;
			}
			
			default:
				throw new IllegalArgumentException("URI desconhecida: " + uri);
		}
		
		getContext().getContentResolver().notifyChange(uri, null);

		return updatedRows;

	}
	
	@Override
	public String getType(Uri uri) {
		
		switch (uriMatcher.match(uri)) {
		
			case LIVROS:
				return CONTENT_TYPE;
				
			case LIVRO_ID:
				return CONTENT_ITEM_TYPE;
						
			default:
				throw new IllegalArgumentException("URI desconhecida: " + uri);
		}
	}
}