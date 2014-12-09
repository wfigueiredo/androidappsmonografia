package br.uff.videoplayertest.adapter;

import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.uff.videoplayertest.R;

public class VideoAdapter extends BaseAdapter {

	private Context context;
	private Cursor cursor;
	
	public VideoAdapter(Context context, Cursor cursor) {
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
        	// os mesmos serão lidos e criados uma única vez.   
			viewHolder = new ViewHolder();
			
			viewHolder.icone = (ImageView) convertView.findViewById(R.id.videoThumbnail);
			viewHolder.nome = (TextView) convertView.findViewById(R.id.videoName);
			viewHolder.tamanho = (TextView) convertView.findViewById(R.id.videoSize);
			viewHolder.duracao = (TextView) convertView.findViewById(R.id.videoDuration);
			
			convertView.setTag(viewHolder);
			
        } else {
      	  	viewHolder = (ViewHolder) convertView.getTag();
        }	
        
        // Video Thumbnail
        int indiceDeInformacoes = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
	  	cursor.moveToPosition(position);
	  	String caminho = cursor.getString(indiceDeInformacoes);
	  	Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(caminho, MediaStore.Images.Thumbnails.MICRO_KIND);
        viewHolder.icone.setImageBitmap(thumbnail);
        	
        // Video name
        int indiceNome = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
        cursor.moveToPosition(position);
        String videoDisplayNome = cursor.getString(indiceNome);
        viewHolder.nome.setText(videoDisplayNome);
        
        // Video size
        int indiceTamanho = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);
        cursor.moveToPosition(position);
        String tamanhoKb = cursor.getString(indiceTamanho);
        Double tamanhoMb = (Double.parseDouble(tamanhoKb) / Math.pow(10,6));
        String tamanhoFinalMb = String.format("%.1f\n", tamanhoMb);
        String videoDisplayTamanho = "Tamanho: " + tamanhoFinalMb + "MB";
        viewHolder.tamanho.setText(videoDisplayTamanho);
        
        // Video Duration
        int indiceDuracao = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION);
        cursor.moveToPosition(position);
        String videoDisplayDuracao = "Duração: " + formatarNotacaoMilisegundos(cursor.getString(indiceDuracao));
        viewHolder.duracao.setText(videoDisplayDuracao);
        
        return convertView;
    }
    
    private String formatarNotacaoMilisegundos(String duracaoMilisegundos) {
		
		long millisegundos = Long.parseLong(duracaoMilisegundos);
		
		String notacaoFormatada = String.format("%02d:%02d:%02d",  	
												TimeUnit.MILLISECONDS.toHours(millisegundos),
			    								TimeUnit.MILLISECONDS.toMinutes(millisegundos),
			    								TimeUnit.MILLISECONDS.toSeconds(millisegundos) - 
			    								TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisegundos)));
		
		return notacaoFormatada;
	}
    
    protected static class ViewHolder {
		
		protected ImageView icone;
		protected TextView nome;
		protected TextView tamanho;
		protected TextView duracao;
	}
}