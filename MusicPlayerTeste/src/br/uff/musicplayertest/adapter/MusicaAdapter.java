package br.uff.musicplayertest.adapter;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.uff.musicplayertest.R;
import br.uff.musicplayertest.util.MusicaUtils;

public class MusicaAdapter extends BaseAdapter {

	private Context context;
	private Cursor cursor;
	
	public MusicaAdapter(Context context, Cursor cursor) {
    	this.context = context;
    	this.cursor = cursor;
    }

    public int getCount() {
        return cursor.getCount();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
    	return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	  ViewHolder viewHolder = null;
    	  LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          
          if (convertView == null) {
        	  
        	  convertView = inflater.inflate(R.layout.list_item_layout, null);
        	  
        	  // Cada item é mantido em seu próprio objeto ViewHolder, garantindo que 
          	  // os mesmos serao lidos e criados uma unica vez.   
  			  viewHolder = new ViewHolder();
  			  viewHolder.arteAlbum = (ImageView) convertView.findViewById(R.id.songAlbumArt);
  			  viewHolder.titulo = (TextView) convertView.findViewById(R.id.songTitle);
  			  viewHolder.artista = (TextView) convertView.findViewById(R.id.songArtist);
  			  
  			  convertView.setTag(viewHolder);
                
          } else {
        	  viewHolder = (ViewHolder) convertView.getTag();
          }
          
          Bitmap miniaturaAlbum = obterMiniaturaAlbum(position);
          viewHolder.arteAlbum.setImageBitmap(miniaturaAlbum);
          
          int indiceNome = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
          cursor.moveToPosition(position);
          String nomeDisplay = cursor.getString(indiceNome);
          viewHolder.titulo.setText(nomeDisplay);
          
          int indiceArtista = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);
          cursor.moveToPosition(position);
          String artistDisplay = cursor.getString(indiceArtista);
          viewHolder.artista.setText(artistDisplay);
          
          return convertView;
    }
    
    private Bitmap obterMiniaturaAlbum(int position) {
    	
    	int indiceAlbumId = cursor.getColumnIndexOrThrow(MediaStore.Audio.AlbumColumns.ALBUM_ID);
	  	cursor.moveToPosition(position);
	  	String albumId = cursor.getString(indiceAlbumId);
	  	
	  	Uri albumArtworkUri = ContentUris.withAppendedId(MusicaUtils.ALBUM_ARTWORK_URI_DEFAULT, Long.parseLong(albumId));
        
        return MusicaUtils.criarMiniaturaAlbum(context, albumArtworkUri);
    }
    
    protected static class ViewHolder {
		
		protected ImageView arteAlbum;
		protected TextView titulo;
		protected TextView artista;
	}
}