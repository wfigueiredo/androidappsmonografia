package br.uff.contentprovidertest.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import br.uff.contentprovidertest.R;
import br.uff.contentprovidertest.bd.TabelaLivro;
import br.uff.contentprovidertest.provider.LivroProvider;

public class ActivityPrincipal extends Activity {
    
	private ListView bookListView;
	
	private Cursor cursor;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        bookListView = (ListView) findViewById(R.id.bookList);
		bookListView.setAdapter(criarCursorAdapter());
		
        bookListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				
				String bookId = getBookId(id);
		    	createDeleteDialog(bookId);
		
		    	return false;
			}
		});
        
        bookListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				String bookId = getBookId(id);
				Uri uri = Uri.parse(LivroProvider.CONTENT_URI + "/" + bookId);
				
				Intent intent = new Intent(ActivityPrincipal.this, CadastroLivroActivity.class);
				intent.putExtra(LivroProvider.CONTENT_ITEM_TYPE, uri);

				startActivity(intent);
				finish();
			}
		});
        
        Button addButton = (Button) findViewById(R.id.addNew);
        addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(ActivityPrincipal.this, CadastroLivroActivity.class);
				startActivity(intent);
				finish();
			}
		});
    }
	
	private CursorAdapter criarCursorAdapter() {
		
		cursor = managedQuery(LivroProvider.CONTENT_URI, TabelaLivro.TODAS_COLUNAS, null, null, null);
		startManagingCursor(cursor);
		
		CursorAdapter cursorAdapter = new SimpleCursorAdapter(ActivityPrincipal.this, 
															  android.R.layout.simple_list_item_1, 
															  cursor, 
															  new String[]{TabelaLivro.COLUNA_TITULO}, 
															  new int[]{android.R.id.text1});
		
        return cursorAdapter;
	}
	
	private void createDeleteDialog(final String bookId){
    	
		final AlertDialog.Builder adb = new AlertDialog.Builder(ActivityPrincipal.this);
		
        adb.setTitle(R.string.excluir);
        adb.setMessage(R.string.confirmarDelecao);
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				Uri uri = Uri.parse(LivroProvider.CONTENT_URI + "/" + bookId);
				getContentResolver().delete(uri, null, null);
				
				bookListView.setAdapter(criarCursorAdapter());
				
				dialog.dismiss();
				Toast.makeText(ActivityPrincipal.this, 
							   getResources().getString(R.string.delecaoSucesso), 
							   Toast.LENGTH_SHORT).show();
			}
		});
        
        adb.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
        	
        	@Override
        	public void onClick(DialogInterface dialog, int which) {
        		
        		dialog.dismiss();
        	}
        });
        
        adb.setCancelable(false);
        
        AlertDialog alertDialog = adb.create();
        alertDialog.show();		
    }
	
	private String getBookId(long adapterViewItemId) {
		
		String bookId = null;
		
		String[] projection = {TabelaLivro.COLUNA_ID};
	    cursor = getContentResolver().query(Uri.withAppendedPath(LivroProvider.CONTENT_URI,
	                    				    String.valueOf(adapterViewItemId)), 
	                    				    projection, 
	                    				    null, null, null);
	    if (cursor.moveToFirst()) {
	        
	    	bookId = cursor.getString(0);
	    }
	    
	    return bookId;
	}
}