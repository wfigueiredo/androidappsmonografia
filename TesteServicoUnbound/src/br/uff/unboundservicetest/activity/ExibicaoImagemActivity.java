package br.uff.unboundservicetest.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import br.uff.unboundservicetest.R;

public class ExibicaoImagemActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ImageView imageView = (ImageView) findViewById(R.id.imagemDownload);
        
        Bundle bundle = getIntent().getExtras();
        byte[] fileBytes = bundle.getByteArray("fileBytes");
        
        Bitmap bitmap = BitmapFactory.decodeByteArray(fileBytes, 0, fileBytes.length);
        imageView.setImageBitmap(bitmap);
        
        NotificationManager notificationManager = 
        			(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.cancel(R.string.app_name);
    }
}