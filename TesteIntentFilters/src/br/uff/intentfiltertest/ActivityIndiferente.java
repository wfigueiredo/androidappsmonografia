package br.uff.intentfiltertest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class ActivityIndiferente extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_feeling);
		
		ImageView expressaoFacial = (ImageView) findViewById(R.id.sentimento);
		expressaoFacial.setImageResource(R.drawable.plain_icon);
    }
}