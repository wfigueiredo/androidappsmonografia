package br.uff.boundservicestest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class BoundServicesTestActivity extends Activity {
    
    private static final String URL_BG_SOUNDTRACK = "http://www.fileden.com/files/2012/4/8/3289515/04%20wild%20street%20%28fatal%20fury%20team%20kof%20xiii%29.mp3";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bgplay);
        createBackgroundMusicPlay();
    }

	private void createBackgroundMusicPlay() 
	{
		Uri link = Uri.parse(URL_BG_SOUNDTRACK);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(link, "audio/*");
		startActivity(intent);
	}
}