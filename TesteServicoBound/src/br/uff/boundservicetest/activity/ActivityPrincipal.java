package br.uff.boundservicetest.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import br.uff.boundservicetest.R;
import br.uff.boundservicetest.java.IBuscaTitulos;
import br.uff.boundservicetest.service.ServicoBuscaTitulo.LocalBinder;

public class ActivityPrincipal extends Activity implements ServiceConnection {
	
	final ServiceConnection conexao = ActivityPrincipal.this;
	
	private boolean conectadoAoServico;
	private IBuscaTitulos buscaTitulos;
	
	private final Intent intent = new Intent("SERVICO_BUSCA_TITULO");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ImageView iniciarServico = (ImageView) findViewById(R.id.iniciar);
		iniciarServico.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				conectarServico(conexao);
				Toast.makeText
					(ActivityPrincipal.this, 
					 "Serviço iniciado com successo!", 
					 Toast.LENGTH_SHORT).show();
			}
		});
		
		ImageView pararServico = (ImageView) findViewById(R.id.parar);
		pararServico.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				desconectarServico(conexao);
				Toast.makeText
					(ActivityPrincipal.this, 
					 "Serviço interrompido com succeso.", 
					 Toast.LENGTH_SHORT).show();
			}
		});
		
		ImageView buscaServico = (ImageView) findViewById(R.id.buscar);
		buscaServico.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String tituloAtual = buscaTitulos.buscarTitulo();
				Toast.makeText
					(ActivityPrincipal.this, 
					 "[Busca] Titulo atual: " + tituloAtual, 
					 Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		desconectarServico(conexao);
	}

	@Override
	public void onServiceConnected(ComponentName nomeComponente, IBinder servico) {
		
		LocalBinder localBinder = (LocalBinder) servico;
		buscaTitulos = localBinder.getService();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		buscaTitulos = null;
	}
	
	private void conectarServico(ServiceConnection connection){
		conectadoAoServico = true;
		bindService(intent, connection, Context.BIND_AUTO_CREATE);
	}
	
	private void desconectarServico(ServiceConnection connection) {
		
		if (conectadoAoServico){
			
			conectadoAoServico = false;
            unbindService(connection);   
		}
	}
}