package br.uff.sqlitetest.activity;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import br.uff.sqlitetest.R;
import br.uff.sqlitetest.bd.datasource.DataSourceFactory;
import br.uff.sqlitetest.bd.datasource.SistemaMobileDataSource;
import br.uff.sqlitetest.bd.helper.SistemasMobileDatabaseHelper;
import br.uff.sqlitetest.modelo.SistemaMobile;

public class SQLiteActivityPrincipal extends Activity {
    
	private SistemaMobileDataSource operationalSystemDataSource;
	
	private ListView mobileSystemsListView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        mobileSystemsListView = (ListView) findViewById(R.id.systemsList);
		mobileSystemsListView.setAdapter(createAdapter());
		
        mobileSystemsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				
				Object item = mobileSystemsListView.getAdapter().getItem(position);
		    	String selectedItem = item.toString();
				createDeleteDialog(selectedItem);
				return false;
			}
		});
        
        mobileSystemsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				Object item = mobileSystemsListView.getAdapter().getItem(position);
				String selectedItem = item.toString();
				
				Intent intent = new Intent(SQLiteActivityPrincipal.this, SistemaMobileActivity.class);
				intent.putExtra(SistemasMobileDatabaseHelper.COLUNA_NOME, selectedItem);

				startActivity(intent);
				finish();
			}
		});
        
        Button addButton = (Button) findViewById(R.id.addNew);
        addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(SQLiteActivityPrincipal.this, SistemaMobileActivity.class);
				startActivity(intent);
				finish();
			}
		});
    }
    
	private void createDeleteDialog(final String name){
    	
		final AlertDialog.Builder adb = new AlertDialog.Builder(SQLiteActivityPrincipal.this);
		
        adb.setTitle(R.string.delete);
        adb.setMessage(R.string.confirmDelete);
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				// Delete selected list item
				SistemaMobile character = operationalSystemDataSource.buscarPorNome(name);
				operationalSystemDataSource.remover(character.getId());
				
				// Refresh list
				mobileSystemsListView.setAdapter(createAdapter());
				
				dialog.dismiss();
				Toast.makeText(SQLiteActivityPrincipal.this, getResources().getString(R.string.deleteSuccessful), Toast.LENGTH_SHORT).show();
			}
		});
        
        adb.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
        	
        	@Override
        	public void onClick(DialogInterface dialog, int which) {
        		
        		dialog.dismiss();
        	}
        });
        
        
        adb.setCancelable(false);
        
        AlertDialog alertDialog = adb.create();
        alertDialog.show();		
    }
	
	private ArrayAdapter<SistemaMobile> createAdapter() {
		
		operationalSystemDataSource = DataSourceFactory.createOperationSystemDataSource(SQLiteActivityPrincipal.this);
        operationalSystemDataSource.abrirBDParaLeitura();
		
		List<SistemaMobile> list = operationalSystemDataSource.buscarTodos();
        SistemaMobile[] mobileSystems = list.toArray(new SistemaMobile[list.size()]);
        
        ArrayAdapter<SistemaMobile> arrayAdapter = new ArrayAdapter<SistemaMobile>(SQLiteActivityPrincipal.this, 
				 android.R.layout.simple_list_item_1, 
				 mobileSystems);
        
        return arrayAdapter;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		operationalSystemDataSource.fechar();
	}
}