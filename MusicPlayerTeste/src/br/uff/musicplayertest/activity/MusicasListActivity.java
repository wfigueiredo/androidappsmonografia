package br.uff.musicplayertest.activity;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.uff.musicplayertest.R;
import br.uff.musicplayertest.adapter.MusicaAdapter;
import br.uff.musicplayertest.util.MusicaUtils;

public class MusicasListActivity extends Activity implements OnItemClickListener {
	
	private Cursor audioCursor;
	private ListView audioList;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.audiolist_layout);
		
		iniciarAudioGrid();
	}
	
	private void iniciarAudioGrid() {
        
		Uri audioContentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		
		String[] projecao = { MediaStore.Audio.Media._ID,
								MediaStore.Audio.Media.ARTIST,
								MediaStore.Audio.Media.TITLE,
								MediaStore.Audio.Media.DATA,
								MediaStore.Audio.Media.DISPLAY_NAME,
        						MediaStore.Audio.AlbumColumns.ALBUM,
        				  		MediaStore.Audio.AlbumColumns.ALBUM_ID };
        
		String selecao = MediaStore.Audio.Media.IS_MUSIC + " != 0";
		String ordenacao = MediaStore.Audio.Media.ARTIST;
		
		audioCursor = managedQuery(audioContentUri, projecao, selecao, null, ordenacao);
        startManagingCursor(audioCursor);
        
        audioList = (ListView) findViewById(R.id.audioList);
        audioList.setAdapter(new MusicaAdapter(MusicasListActivity.this, audioCursor));
        audioList.setOnItemClickListener(this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View arg1, int position, long id) {
		
        int indiceDeDados = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        audioCursor.moveToPosition(position);
        String arquivo = audioCursor.getString(indiceDeDados);
        
	  	int indiceNome = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
        audioCursor.moveToPosition(position);
        String nome = audioCursor.getString(indiceNome);
        
        int indiceArtista = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);
        audioCursor.moveToPosition(position);
		String artista = audioCursor.getString(indiceArtista);
		
		int indiceAlbum = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.AlbumColumns.ALBUM);
		audioCursor.moveToPosition(position);
		String album = audioCursor.getString(indiceAlbum);
		
		int indiceAlbumId = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.AlbumColumns.ALBUM_ID);
	  	audioCursor.moveToPosition(position);
	  	String albumId = audioCursor.getString(indiceAlbumId);
        Uri artworkUri = ContentUris.withAppendedId(MusicaUtils.ALBUM_ARTWORK_URI_DEFAULT, Long.parseLong(albumId));
              
        Intent intent = new Intent(MusicasListActivity.this, PlayerServiceActivity.class);
        intent.putExtra(MusicaUtils.PARAMETRO_CAMINHO, arquivo);
        intent.putExtra(MusicaUtils.PARAMETRO_ARTISTA, artista);
        intent.putExtra(MusicaUtils.PARAMETRO_TITULO, nome);
        intent.putExtra(MusicaUtils.PARAMETRO_ALBUM, album);
		intent.putExtra(MusicaUtils.PARAMETRO_ARTWORK_URI, artworkUri);
        
        startActivity(intent);
	}
}