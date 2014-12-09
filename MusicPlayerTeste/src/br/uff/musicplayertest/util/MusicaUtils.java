package br.uff.musicplayertest.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import br.uff.musicplayertest.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

public class MusicaUtils {
	
	public final static Uri ALBUM_ARTWORK_URI_DEFAULT = Uri.parse("content://media/external/audio/albumart");
	
	public final static int ALBUM_ART_LARGURA_MICRO_KIND = 96;
	public final static int ALBUM_ART_ALTURA_MICRO_KIND = 96;

	public final static String PARAMETRO_CAMINHO = "caminho";
	public static final String PARAMETRO_ARTISTA = "artista";
	public static final String PARAMETRO_TITULO = "titulo";
	public static final String PARAMETRO_ALBUM = "album";
	public static final String PARAMETRO_ARTWORK_URI = "artworkUri";
	
	public static Bitmap criarAlbumArtwork(Context context, Uri mediaArtworkUri) {
		
		Bitmap artwork = null;
		InputStream inputStream = null;
		
		try {
			
			inputStream = context.getContentResolver().openInputStream(mediaArtworkUri);

			if (inputStream == null) {
				artwork = BitmapFactory.decodeResource(context.getResources(), R.drawable.unknow_music_bg2);
			}
			else {
				artwork = BitmapFactory.decodeStream(inputStream);
			}
			
			inputStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		return artwork;
	}
	
	public static Bitmap criarMiniaturaAlbum(Context context, Uri midiaArtworkUri) {
		
		Bitmap artwork = null;
		Bitmap bitmapRedimensionado = null;
		InputStream inputStream = null;
		
		try {
            inputStream = context.getContentResolver().openInputStream(midiaArtworkUri);
            
            if (inputStream != null) {
            	artwork = BitmapFactory.decodeStream(inputStream);
            }
            
            inputStream.close();
            
        } catch (FileNotFoundException e) {
        	artwork = BitmapFactory.decodeResource(context.getResources(), R.drawable.unknow_music_icon);
        } catch (IOException e) {
			e.printStackTrace();
		}
		
		bitmapRedimensionado = Bitmap.createScaledBitmap(artwork, ALBUM_ART_LARGURA_MICRO_KIND, ALBUM_ART_ALTURA_MICRO_KIND, true);
        
        if (bitmapRedimensionado == null) {
        	return null;
        }

        if (bitmapRedimensionado != artwork) {
        	artwork.recycle();
        }
        
        artwork = bitmapRedimensionado;
		
		return artwork;
	}
}