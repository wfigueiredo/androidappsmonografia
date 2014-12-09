package br.uff.gridviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class GridViewActivity extends Activity {
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImagemAdapter(GridViewActivity.this));

        gridview.setOnItemClickListener(new OnItemClickListener() {
        	
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
            {
                Toast.makeText(GridViewActivity.this, 
                			   "Imagem selecionada: " + position, 
                			   Toast.LENGTH_SHORT).show();
            }
        });
    }
}