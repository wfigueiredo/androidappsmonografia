package br.uff.linearlayouttest;

import android.app.Activity;
import android.os.Bundle;

public class LinearLayoutActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String orientation = getIntent().getExtras().getString("orientation");
		
		if (orientation.equals("vertical")){
			setContentView(R.layout.linearlayout_vertical);
		}
		else if (orientation.equals("horizontal")){
			setContentView(R.layout.linearlayout_horizontal);
		}
		else {
			setContentView(R.layout.linearlayout_peso);
		}
	}
}