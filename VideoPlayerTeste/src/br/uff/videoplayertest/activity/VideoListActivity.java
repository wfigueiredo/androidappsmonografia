package br.uff.videoplayertest.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.uff.videoplayertest.R;
import br.uff.videoplayertest.adapter.VideoAdapter;
import br.uff.videoplayertest.util.VideoUtils;

public class VideoListActivity extends Activity implements OnItemClickListener {
	
	private Cursor videoCursor;
	private ListView videoList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.videolist_layout);
		
		iniciarVideoGrid();
	}
	
	private void iniciarVideoGrid() {
        
        String[] projecao = { MediaStore.Video.Media._ID,
        				  	  MediaStore.Video.Media.DATA,
        				  	  MediaStore.Video.Media.DISPLAY_NAME,
        				  	  MediaStore.Video.Media.SIZE,
        				      MediaStore.Video.Media.DURATION };
        
        String ordenacao = MediaStore.Video.Media.DATE_ADDED + " DESC";
        
        videoCursor = managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, 
        						   projecao, null, null, ordenacao);
        
        startManagingCursor(videoCursor);
        
        videoList = (ListView) findViewById(R.id.videoList);
        videoList.setAdapter(new VideoAdapter(VideoListActivity.this, videoCursor));
        videoList.setOnItemClickListener(this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View arg1, int position, long id) {
		
        int indiceDeInformacoes = videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
        videoCursor.moveToPosition(position);
        String caminhoArquivo = videoCursor.getString(indiceDeInformacoes);
              
        Intent intent = new Intent(VideoListActivity.this, VideoPlayerActivity.class);
        intent.putExtra(VideoUtils.PARAMETRO_CAMINHO_VIDEO, caminhoArquivo);
        
        startActivity(intent);
	}
}