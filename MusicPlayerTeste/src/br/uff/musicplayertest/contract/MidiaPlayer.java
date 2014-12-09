package br.uff.musicplayertest.contract;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.util.Log;

public class MidiaPlayer implements OnCompletionListener, OnSeekCompleteListener, OnPreparedListener {
	
	private static final int INICIADO = 1;
	private static final int PRONTO = 2;
	private static final int PAUSADO	= 3;
	private static final int PARADO = 4;
	
	private MediaPlayer player;
	
	private int status;
	private String caminhoMusica;
	
	public MidiaPlayer(String caminho) {
		
		player = new MediaPlayer();
		this.caminhoMusica = caminho;
		prepararPlayer();
		
		status = PRONTO;
		
		// Listener Callback methods
		player.setOnCompletionListener(this);
	}
	
	public void iniciar() {
		
		try {
			
			switch (status) {
			
				case INICIADO:
					player.stop();
					break;
					
				case PARADO:
					prepararPlayer();
					player.start();
					break;
					
				case PRONTO:
					player.start();
					break;
					
				case PAUSADO:
					player.start();
					break;
			}

			status = INICIADO;
			
		} catch (Exception e) {
			Log.e("LOGTAG_MUSICPLAYER", e.getMessage(), e);
		}
	}
	
	public void pausar() {
		player.pause();
		status = PAUSADO;
	}
	
	public void parar() {
		player.stop();
		player.reset();
		status = PARADO;
	}
	
	public boolean tocando() {
		return player.isPlaying();
	}
	
	private void prepararPlayer() {
		
		try {
			player.setDataSource(caminhoMusica);
			player.prepare();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the MediaPlayer in order to free system resources.
	 */
	public void fechar() {
		
		parar();
		player.release();
		player = null;
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSeekComplete(MediaPlayer mp) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
	}

	public String obterMusicaAtual() {
		return caminhoMusica;
	}

	public void setCurrentMusic(String caminhoMusicaAtual) {
		this.caminhoMusica = caminhoMusicaAtual;
	}
}