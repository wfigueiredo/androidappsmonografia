package br.uff.videoplayertest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;
import br.uff.videoplayertest.R;
import br.uff.videoplayertest.util.VideoUtils;

public class VideoPlayerActivity extends Activity {
    
	private VideoView videoView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.video_layout);
        
        String caminhoVideo = getIntent().getExtras()
        						.getString(VideoUtils.PARAMETRO_CAMINHO_VIDEO);
        
        videoView = (VideoView) findViewById(R.id.video);

        videoView.setVideoPath(caminhoVideo);
        videoView.setMediaController(new MediaController(VideoPlayerActivity.this));
        videoView.start();
    }
	
	@Override
	protected void onPause() {
		super.onPause();
		videoView.pause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		videoView.start();
	}
}