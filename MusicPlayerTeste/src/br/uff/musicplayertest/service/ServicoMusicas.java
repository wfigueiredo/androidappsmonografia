package br.uff.musicplayertest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import br.uff.musicplayertest.contract.IMusica;
import br.uff.musicplayertest.contract.MidiaPlayer;
import br.uff.musicplayertest.util.MusicaUtils;

public class ServicoMusicas extends Service implements IMusica {

	private MidiaPlayer musicPlayer;
	
	// Objeto que recebe interação dos Clientes.
	private final IBinder binder = new LocalBinder();
	
	/**
	 * Classe acessada pelos clientes.
	 * 
	 * Este serviço executa no mesmo processo que os clientes, 
	 * por isso, nao existe a necessidade de tratar questoes de IPC. 
	 */
	public class LocalBinder extends Binder {
		
		/**
		 * Retorna uma instancia para a classe de referencia do Servico, 
		 * que representa a propria classe MusicService.
		 */
		public IMusica recuperarServico() {
			return ServicoMusicas.this;
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		realizarOperacao(intent);

		// Flag que denota o desejo de continuidade de execucao do servico
		// ate que o mesmo seja explicitamente parado.
		return START_STICKY;
	}
	
	private void realizarOperacao(Intent intent) {
		
		Bundle extras = intent.getExtras();
		String caminho = extras.getString(MusicaUtils.PARAMETRO_CAMINHO);
		
		if (musicPlayer == null) {
			musicPlayer = new MidiaPlayer(caminho);
			iniciarMidia();
		} else {
			
			String musicaAtual = musicPlayer.obterMusicaAtual();
			
			if (musicaAtual != null) {
				
				// Usuario troca a faixa
				if (!musicaAtual.equals(caminho)) {
					
					stopMedia();
					musicPlayer.setCurrentMusic(caminho);
					iniciarMidia();
				}
			}
		}
	}

	@Override
	public void iniciarMidia() {
		musicPlayer.iniciar();
	}

	@Override
	public void pausarMidia() {
		musicPlayer.pausar();
	}

	@Override
	public void stopMedia() {
		musicPlayer.parar();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		musicPlayer.fechar();
	}
}