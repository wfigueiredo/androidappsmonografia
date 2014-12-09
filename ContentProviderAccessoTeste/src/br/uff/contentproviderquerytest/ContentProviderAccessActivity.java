package br.uff.contentproviderquerytest;

import android.app.Activity;
import android.content.ContentProviderClient;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ContentProviderAccessActivity extends Activity {
    
    private final static String CONTENT_URI_CLIENTE = "content://br.uff.contentproviderteste.provider.LivroProvider/livros"; 
	
	private ListView livrosListView;
    private Cursor cursor;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        livrosListView = (ListView) findViewById(R.id.bookList);
        
        final Button listarEstoque = (Button) findViewById(R.id.fetchBooks);
        listarEstoque.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				CursorAdapter cursorAdapter = criarCursorAdapter();
				
				if (cursorAdapter.getCount() > 0) {
					
					// Remove some widgets to insert the Book List
					TextView descricao = (TextView) findViewById(R.id.descriptionText);
					descricao.setVisibility(View.GONE);
					
					listarEstoque.setEnabled(false);
					
					livrosListView.setAdapter(cursorAdapter);
				}
				else {
					Toast.makeText(ContentProviderAccessActivity.this, 
								   "Não há livros cadastrados.", Toast.LENGTH_LONG).show();
				}
			}
		});
    }
	
	private CursorAdapter criarCursorAdapter() {
		
		Uri clientUri = Uri.parse(CONTENT_URI_CLIENTE); 
		ContentProviderClient clientProvider = 
								getContentResolver().acquireContentProviderClient(clientUri);
		
		CursorAdapter cursorAdapter = null;

		try { 
			
			cursor = clientProvider.query(clientUri, new String[]{"_id","titulo"}, null, null, null);
			startManagingCursor(cursor);
			
			cursorAdapter = new SimpleCursorAdapter(ContentProviderAccessActivity.this, 
					  								android.R.layout.simple_list_item_1, 
					  								cursor, 
					  								new String[]{"titulo"}, 
					  								new int[]{android.R.id.text1});
		} 
		catch (RemoteException e) { 
			e.printStackTrace(); 
		} 
		finally {
			clientProvider.release();
		}
		
		return cursorAdapter;
	}
}