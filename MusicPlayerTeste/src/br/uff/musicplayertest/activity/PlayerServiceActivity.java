package br.uff.musicplayertest.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import br.uff.musicplayertest.R;
import br.uff.musicplayertest.contract.IMusica;
import br.uff.musicplayertest.service.ServicoMusicas;
import br.uff.musicplayertest.service.ServicoMusicas.LocalBinder;
import br.uff.musicplayertest.util.MusicaUtils;

public class PlayerServiceActivity extends Activity implements ServiceConnection, OnClickListener {

	private final static String TAG_INTENT_FILTER_MUSICA = "INTENT_FILTER_MUSICA";
	
	private ImageView botaoPlayPause;
	private ImageView botaoStop;
	private ImageView botaoProxima;
	private ImageView botaoAnterior;
	
	private boolean tocando = true;
	
	// Servico
	private IMusica servico = new ServicoMusicas();
	private Intent intentServico;
	private boolean linkado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.music_layout);
		
		iniciarListeners();
		
		final Bundle extras = getIntent().getExtras();
		iniciarDetalhesMusicaisDeExibicao(extras);
		
		intentServico = new Intent(TAG_INTENT_FILTER_MUSICA);
		intentServico = criarIntentDeMidia(getIntent().getExtras(), intentServico);
		
		// Workaround para manter o serviço vivo.
		// Primeiramente, o serviço deve ser invocado via startService() e, em seguida, 
		// realizar uma chamada para bind(). 
		// Desta forma, a Activity fica responsável por gerenciar o ciclo de vida do serviço.
		startService(intentServico);
		conectarServico(intentServico);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}

	private void iniciarListeners() {
			
		botaoPlayPause = (ImageView) findViewById(R.id.playPauseIcon);
		botaoPlayPause.setOnClickListener(this);
		
		botaoStop = (ImageView) findViewById(R.id.stopIcon);
		botaoStop.setOnClickListener(this);
		
		botaoAnterior = (ImageView) findViewById(R.id.previousIcon);
		botaoAnterior.setOnClickListener(this);
		
		botaoProxima = (ImageView) findViewById(R.id.nextIcon);
		botaoProxima.setOnClickListener(this);
	}
	
	private void iniciarDetalhesMusicaisDeExibicao(Bundle extras) {
		
		String artistDisplayName = extras.getString(MusicaUtils.PARAMETRO_ARTISTA);
		String songDisplayName = extras.getString(MusicaUtils.PARAMETRO_TITULO);
		String albumDisplayName = extras.getString(MusicaUtils.PARAMETRO_ALBUM);
		Uri mediaArtworkUri = extras.getParcelable(MusicaUtils.PARAMETRO_ARTWORK_URI);
		
		TextView artist = (TextView) findViewById(R.id.songArtist);
		artist.setText(artistDisplayName);

		TextView title = (TextView) findViewById(R.id.songName);
		title.setText(songDisplayName);
		
		TextView album = (TextView) findViewById(R.id.songAlbum);
		album.setText(albumDisplayName);
		
		ImageView albumArt = (ImageView) findViewById(R.id.albumArt);
		albumArt.setImageBitmap(MusicaUtils.criarAlbumArtwork(PlayerServiceActivity.this, mediaArtworkUri));
	}
	
	private Intent criarIntentDeMidia(Bundle extras, Intent intent) {
		
		String caminho = extras.getString(MusicaUtils.PARAMETRO_CAMINHO);
		String artista = extras.getString(MusicaUtils.PARAMETRO_ARTISTA);
		String titulo = extras.getString(MusicaUtils.PARAMETRO_TITULO);
		String album = extras.getString(MusicaUtils.PARAMETRO_ALBUM);
		Uri artworkUri = extras.getParcelable(MusicaUtils.PARAMETRO_ARTWORK_URI);
		
		intent.putExtra(MusicaUtils.PARAMETRO_CAMINHO, caminho);
		intent.putExtra(MusicaUtils.PARAMETRO_ARTISTA, artista);
		intent.putExtra(MusicaUtils.PARAMETRO_TITULO, titulo);
		intent.putExtra(MusicaUtils.PARAMETRO_ALBUM, album);
		intent.putExtra(MusicaUtils.PARAMETRO_ARTWORK_URI, artworkUri);
		
		return intent;
	}

	@Override
	public void onClick(View view) {
		
		try {
			
			if (view == botaoPlayPause) {
				
				if (tocando) {
					
					tocando = false;
					botaoPlayPause.setImageResource(R.drawable.play_icon);
					servico.pausarMidia();
				}
				else {
					
					tocando = true;
					botaoPlayPause.setImageResource(R.drawable.pause_icon);
					servico.iniciarMidia();
				}
			} 
			else if (view == botaoStop) {
				
				tocando = false;
				botaoPlayPause.setImageResource(R.drawable.play_icon);
				servico.stopMedia();
			}	
			else if (view == botaoAnterior || view == botaoProxima) {
				Toast.makeText(this, "Funcionalidade ainda não implementada.", Toast.LENGTH_SHORT).show();
			}	
		} catch (Exception e) {
			Log.e("LOGTAG_MUSIC_BG", e.getMessage(), e);
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	
	// -------------------------- Implemented Interface Methods --------------------------
	
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		
		// Invocado assim que a conexao com o servico eh estabelecida.
		// Desta forma, os cliente podem interagir com o mesmo.
		//
		// Como existe uma conexao direta para o servico, que roda no 
		// mesmo processo que o cliente, pode ser feito um cast de seu
		// IBinder para uma classe concreta.
		LocalBinder localBinder = (LocalBinder) service;
		servico = localBinder.recuperarServico();
	}
	
	@Override
	public void onServiceDisconnected(ComponentName name) {
		servico = null;
	}
	
	
	private void conectarServico(Intent serviceIntent) {
		
		bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
		linkado = true;
	}
	
	private void desconectarServico() {
		
		if (linkado) {
			
			linkado = false;
			unbindService(this);
		}
	}
	
	// -------------------------- Implemented Activity Callback Methods --------------------------

	/**
	 * Usado em casos como uma possivel rotacao do dispositivo, que nao deve chamar
	 * bindService() novamente, pois assim onCreate() seria invocado novamente na Activity.
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		desconectarServico();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		conectarServico(intentServico);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		desconectarServico();
	}
}