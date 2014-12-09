package br.uff.framelayouttest;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuActivity extends ListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String[] optionsMenu = {getResources().getString(R.string.framelayoutIcone), 
								getResources().getString(R.string.frameLayoutBackground)};
		
		ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(MenuActivity.this, android.R.layout.simple_list_item_1, optionsMenu);
        setListAdapter(itemsAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		switch (position) 
		{
			case 0:
				startActivity(new Intent(MenuActivity.this, FrameLayoutIconeActivity.class));
				break;
				
			case 1:
				startActivity(new Intent(MenuActivity.this, FrameLayoutFundoActivity.class));
				break;
	
			default:
				finish();
				break;
		}
	}
}
