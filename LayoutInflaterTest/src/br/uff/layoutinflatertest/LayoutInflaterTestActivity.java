package br.uff.layoutinflatertest;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LayoutInflaterTestActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout linearLayout = new LinearLayout(LayoutInflaterTestActivity.this);
        
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        
        ImageView googleLogo = new ImageView(LayoutInflaterTestActivity.this);
        googleLogo.setImageResource(R.drawable.google_logo);
        googleLogo.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View xmlLayout = inflater.inflate(R.layout.main_layout, null);
        
        linearLayout.addView(googleLogo);
        linearLayout.addView(xmlLayout);
        
        setContentView(linearLayout);
    }
}