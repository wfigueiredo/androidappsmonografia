package br.uff.contentprovidertest.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.uff.contentprovidertest.R;
import br.uff.contentprovidertest.bd.TabelaLivro;
import br.uff.contentprovidertest.modelo.Livro;
import br.uff.contentprovidertest.provider.LivroProvider;

public class CadastroLivroActivity extends Activity {
	
	private EditText titleInput;
	private EditText publisherInput;
	private EditText aboutInput;
	
	private Uri contentUri;
	
	private boolean editOperation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form_layout);
		
		titleInput = (EditText) findViewById(R.id.titleInput);
		publisherInput = (EditText) findViewById(R.id.publisherInput);
		aboutInput = (EditText) findViewById(R.id.aboutInput);
		
		Bundle extras = getIntent().getExtras();
		contentUri = null;
		
		// EDIT
		if (extras != null) {
			
			contentUri = extras.getParcelable(LivroProvider.CONTENT_ITEM_TYPE);
			fillForm(contentUri);
			
			editOperation = true;
		}
		

		Button submitButton = (Button) findViewById(R.id.submit);
		submitButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String title = titleInput.getText().toString();
				String publisher = publisherInput.getText().toString();
				String about = aboutInput.getText().toString();
				
				ContentValues contentValues = createBookContentValues(title, publisher, about);
				
				if (editOperation) {
					
					getContentResolver().update(contentUri, contentValues, null, null);
				}
				else { 
					
					getContentResolver().insert(LivroProvider.CONTENT_URI, contentValues);
				}
				
				Intent intent = new Intent(CadastroLivroActivity.this, ActivityPrincipal.class);
				startActivity(intent);
				
				finish();
			}
		});
	}
	
	private ContentValues createBookContentValues(String title, String publisher, String about) {
		
		ContentValues contentValues = new ContentValues();
		
		contentValues.put(TabelaLivro.COLUNA_TITULO, title);
		contentValues.put(TabelaLivro.COLUNA_AUTOR, publisher);
		contentValues.put(TabelaLivro.COLUNA_EDITORA, about);
		
		return contentValues;
	}
	
	private void fillForm(Uri uri) {
		
		Livro book = null;
		
		String[] projection = TabelaLivro.TODAS_COLUNAS;
	    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
	    
	    if (cursor.moveToFirst()) {
	        
	    	book = new Livro();
	    	
	    	book.setId(cursor.getString(0));
	    	book.setTitle(cursor.getString(1));
	    	book.setPublisher(cursor.getString(2));
	    	book.setAbout(cursor.getString(3));
	    	
	    	titleInput.setText(book.getTitle());
	    	publisherInput.setText(book.getPublisher());
	    	aboutInput.setText(book.getAbout());
	    }
	    
	    cursor.close();
	}
}