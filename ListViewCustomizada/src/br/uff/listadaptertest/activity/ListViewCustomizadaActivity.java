package br.uff.listadaptertest.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import br.uff.listadaptertest.adapter.EmpresaAdapter;
import br.uff.listadaptertest.model.Empresa;

public class ListViewCustomizadaActivity extends ListActivity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Empresa> enterprises = createEnterprises();
        setListAdapter(new EmpresaAdapter(ListViewCustomizadaActivity.this, android.R.layout.simple_list_item_1, enterprises));
    }

	private ArrayList<Empresa> createEnterprises() {
	
		ArrayList<Empresa> enterprises = new ArrayList<Empresa>();
		
		enterprises.add(new Empresa(Empresa.ANDROID_ICONE, "Android", "Google"));
		enterprises.add(new Empresa(Empresa.IOS_ICONE, "iOS", "Apple"));
		enterprises.add(new Empresa(Empresa.WINDOWS_ICONE, "Windows Phone 7", "Microsoft"));
		enterprises.add(new Empresa(Empresa.BLACKBERRY_ICONE, "RIM", "Blackberry"));
		
		return enterprises;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		super.onListItemClick(l, v, position, id);
		Empresa selectedEnterprise = (Empresa) getListAdapter().getItem(position);
		Toast.makeText(ListViewCustomizadaActivity.this, selectedEnterprise.toString(), Toast.LENGTH_SHORT).show();
	}
}