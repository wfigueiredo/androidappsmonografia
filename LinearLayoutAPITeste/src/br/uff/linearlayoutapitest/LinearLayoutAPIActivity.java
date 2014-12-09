package br.uff.linearlayoutapitest;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LinearLayoutAPIActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout linearLayout = new LinearLayout(LinearLayoutAPIActivity.this);
        
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 
        										      LayoutParams.FILL_PARENT));
        linearLayout.setPadding(10, 10, 10, 10);
        
        ImageView imagem1 = new ImageView(LinearLayoutAPIActivity.this);
        imagem1.setImageResource(R.drawable.re3_logo);
        imagem1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 
        										LayoutParams.WRAP_CONTENT));

        ImageView imagem2 = new ImageView(LinearLayoutAPIActivity.this);
        imagem2.setImageResource(R.drawable.re4_logo);
        imagem2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 
        										LayoutParams.WRAP_CONTENT));
        
        ImageView imagem3 = new ImageView(LinearLayoutAPIActivity.this);
        imagem3.setImageResource(R.drawable.re5_logo);
        imagem3.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 
        										LayoutParams.WRAP_CONTENT));
        
        linearLayout.addView(imagem1);
        linearLayout.addView(imagem2);
        linearLayout.addView(imagem3);
        
        setContentView(linearLayout);
    }
}