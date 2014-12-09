package br.uff.gridviewtest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImagemAdapter extends BaseAdapter {

	private Context context;
	
	private final static int[] imagens = {R.drawable.gh3_logo, R.drawable.gow3_logo,
										 R.drawable.kofxii_logo, R.drawable.kofxiii_logo,
										 R.drawable.mgs2_logo, R.drawable.mgs4_logo,
										 R.drawable.ns_logo, R.drawable.re3_logo,
										 R.drawable.re4_logo1, R.drawable.re5_logo1,
										 R.drawable.re5_logo2, R.drawable.sh3_logo};
	
	public ImagemAdapter(Context context) {
		this.context = context; 
	}
	
	@Override
	public int getCount() {
		return imagens.length;
	}

	@Override
	public Object getItem(int posicao) {
		return imagens[posicao];
	}


	@Override
	public View getView(int posicao, View viewConvertida, ViewGroup pai) {
		
		ImageView imageView = new ImageView(context);
        
		imageView.setImageResource(imagens[posicao]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(10, 10, 10, 10);
        
        return imageView;
	}
	
	@Override
	public long getItemId(int position) {
		return 0;
	}
}