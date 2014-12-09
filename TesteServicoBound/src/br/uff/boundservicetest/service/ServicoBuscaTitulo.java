package br.uff.boundservicetest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.uff.boundservicetest.java.IBuscaTitulos;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ServicoBuscaTitulo extends Service implements IBuscaTitulos {

	private boolean ativo;
	private String tituloCorrente;
	private static List<String> titulos = new ArrayList<String>();
	
	private final IBinder binder = new LocalBinder();
	
	/**
	 * Classe acessada pelos clientes do serviço. 
	 * Como ele sempre executa no mesmo processo que seus clientes, 
	 * não há a necessidade de tratar casos de IPC.
	 */
	public class LocalBinder extends Binder {
		
		// Retorna uma instância para a classe de referência
		// do Serviço, ou seja, a própria classe pública do mesmo.
		public IBuscaTitulos getService() 
		{
			return ServicoBuscaTitulo.this;
		}
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return binder;
	}
	
	@Override
	public void onCreate() {
		
		// Flag que monitora o estado do serviço 
		ativo = true;			
		
		// Criação da lista de títulos
		criarListaDeTitulos();
		
		// Processamento da lista de títulos
		iniciarContagem();
	}
	
	@Override
	public String buscarTitulo() {
		
		return tituloCorrente;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		return START_STICKY;
	}
	
	@Override
	public void onDestroy() {
		ativo = false;
	}
	
	private void criarListaDeTitulos() {
		
		String[] titles = new String[]{
				"Bleach Soul Ressurecion",
				"Devil may Cry HD Collection",
				"Devil may Cry 4",
				"God of War Collection",
				"God of War III",
				"God of War Origins Collection",
				"Infamous 2",
				"Infamous: Festival of Blood",
				"Marvel vs Capcom 3",
				"Metal Gear Solid HD Collection",
				"Metal Gear Solid 4: Guns of the Patriots",
				"Mortal Kombat",
				"Resident Evil Revival Collection",
				"Resident Evil Chronicles Collection",
				"Resident Evil 5 Gold Edition",
				"Resident Evil 6",
				"Silent Hill HD Collection",
				"Street Fighter x Tekken",
				"Super Street Fighter IV",
				"The King of Fighters XII",
				"The King of Fighters XIII"
			};
		
		titulos.addAll(Arrays.asList(titles));
	}
	
	private void iniciarContagem(){
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				int i = 0;
				while (ativo && i < titulos.size()) {
					
					tituloCorrente = titulos.get(i);
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
					
					i++;
				}
			}
		});
		
		thread.start();
		stopSelf();
	}
}