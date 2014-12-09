package br.uff.boundservicestest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ImageView pageWallpaper = (ImageView) findViewById(R.id.kofxiiiwallpaper1);
    	pageWallpaper.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setClassName(MainActivity.this, BoundServicesTestActivity.class.getName());
				startActivity(intent);
			}
		});
	}
	
}
