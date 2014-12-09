package br.uff.sqlitetest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.uff.sqlitetest.R;
import br.uff.sqlitetest.bd.datasource.DataSourceFactory;
import br.uff.sqlitetest.bd.datasource.SistemaMobileDataSource;
import br.uff.sqlitetest.bd.helper.SistemasMobileDatabaseHelper;
import br.uff.sqlitetest.modelo.SistemaMobile;

public class SistemaMobileActivity extends Activity {
	
	private EditText nameInput;
	private EditText companyInput;

	private SistemaMobile operationalSystem;
	
	boolean editOperation;
	
	private SistemaMobileDataSource operationalSystemDataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form_layout);
		
		nameInput = (EditText) findViewById(R.id.nameInput);
		companyInput = (EditText) findViewById(R.id.companyInput);

		operationalSystemDataSource = DataSourceFactory.createOperationSystemDataSource(SistemaMobileActivity.this);
		operationalSystemDataSource.abrirBDParaEscrita();
		
		String name = getIntent().getStringExtra(SistemasMobileDatabaseHelper.COLUNA_NOME);
		
		// EDIT operation
		if (name != null) {
			
			editOperation = true;
			operationalSystem = operationalSystemDataSource.buscarPorNome(name);
			nameInput.setText(operationalSystem.getName());
			companyInput.setText(operationalSystem.getCompany());
		}

		
		Button submitButton = (Button) findViewById(R.id.submit);
		submitButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String name = nameInput.getText().toString();
				String title = companyInput.getText().toString();
				
				SistemaMobile newOperationalSystem = new SistemaMobile();
				newOperationalSystem.setName(name);
				newOperationalSystem.setGameTitle(title);
				
				if (!editOperation){
					
					operationalSystemDataSource.criar(newOperationalSystem);
				}
				else {
					newOperationalSystem.setId(operationalSystem.getId());
					operationalSystemDataSource.atualizar(newOperationalSystem);
				}
				
				Intent intent = new Intent(SistemaMobileActivity.this, SQLiteActivityPrincipal.class);
				startActivity(intent);
				
				finish();
			}
		});
	}
}