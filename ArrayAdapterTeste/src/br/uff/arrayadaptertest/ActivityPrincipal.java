package br.uff.arrayadaptertest;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityPrincipal extends ListActivity {
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String[] itens = new String[]{"Android", "Java", "Neo4J"};
        ArrayAdapter<String> itensAdapter = 
        		new ArrayAdapter<String>(ActivityPrincipal.this, 
        								 android.R.layout.simple_list_item_1, 
        								 itens);
        setListAdapter(itensAdapter);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	
    	super.onListItemClick(l, v, position, id);
    	
    	Object item = getListAdapter().getItem(position);
    	String itemSelecionado = item.toString();
    	
    	Toast.makeText
    			(ActivityPrincipal.this, "Item selecionado: " + itemSelecionado, 
    			 Toast.LENGTH_SHORT).show();
    }
}