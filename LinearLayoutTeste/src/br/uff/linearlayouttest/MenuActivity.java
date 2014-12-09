package br.uff.linearlayouttest;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuActivity extends ListActivity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String[] optionsMenu = {getResources().getString(R.string.linearlayoutHorizontal),
        						getResources().getString(R.string.linearlayoutVertical),
								getResources().getString(R.string.linearLayoutweight)};

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(MenuActivity.this, android.R.layout.simple_list_item_1, optionsMenu);
        setListAdapter(itemsAdapter);
    }
    
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent intent = new Intent(MenuActivity.this, LinearLayoutActivity.class);
		
		switch (position) 
		{
			case 0:
				intent.putExtra("orientation", "horizontal");
				break;
				
			case 1:
				intent.putExtra("orientation", "vertical");
				break;
				
			case 2:
				intent.putExtra("orientation", "null");
				break;
				
			default:
				finish();
				break;
		}
		
		startActivity(intent);
	}
}