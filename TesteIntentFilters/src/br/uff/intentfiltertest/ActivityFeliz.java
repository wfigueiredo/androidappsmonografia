package br.uff.intentfiltertest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class ActivityFeliz extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_feeling);
		
		ImageView expressaoFacial = (ImageView) findViewById(R.id.sentimento);
		expressaoFacial.setImageResource(R.drawable.happy_icon);
	}
}



