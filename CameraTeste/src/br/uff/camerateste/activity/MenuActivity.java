package br.uff.camerateste.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.uff.camerateste.R;
import br.uff.camerateste.camera.CameraUtils;

public class MenuActivity extends Activity {
    
	private static final int CODIGO_REQUISICAO_CAPTURAR_IMAGEM = 100;
	private static final int CODIGO_REQUISICAO_CAPTURAR_VIDEO = 200;
	
	private Uri uriArquivo;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Resources resources = getResources();
        
        String[] opcoesMenu = {resources.getString(R.string.tirarFotos),
        					   resources.getString(R.string.capturarVideo)};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MenuActivity.this, android.R.layout.simple_list_item_1, opcoesMenu);
        
        ListView menu = (ListView) findViewById(R.id.optionList);
        menu.setAdapter(adapter);
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				
				switch (position) 
				{
					case 0:
						obterIntentCapturaDeFotos();
						break;
						
					case 1:
						obterIntentCapturaDeVideos();
						break;
						
					default:
						finish();
						break;
					}
				}
		});
	}
	
	private void obterIntentCapturaDeFotos() {
		
		// Cria uma Intent para a captura da foto que, em seguida, retorna o 
		// controle do fluxo de execução para a aplicação
	    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

	    // Criação do arquivo que representa a imagem persistida
	    uriArquivo = CameraUtils.obterUriDeArquivoGerado(CameraUtils.TIPO_MIDIA_IMAGEM); 

	    intent.putExtra(MediaStore.EXTRA_OUTPUT, uriArquivo); 

	    // Inicio da Intent de captura da imagem
	    startActivityForResult(intent, CODIGO_REQUISICAO_CAPTURAR_IMAGEM);
	}
	
	private void obterIntentCapturaDeVideos() {
		
		// Cria uma Intent para a captura do video que, em seguida, retorna o 
		// controle do fluxo de execução para a aplicação
	    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
	    
	    // definição de alta qualidade na captura do video
	    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); 

	    // Inicio da Intent de captura do video
	    startActivityForResult(intent, CODIGO_REQUISICAO_CAPTURAR_VIDEO);
	}
	
	@Override
	protected void onActivityResult(int codigoRequisicao, int codigoResultado, Intent dados) {
	    
		switch (codigoResultado) {
		
			case RESULT_OK:
				
				if (codigoRequisicao == CODIGO_REQUISICAO_CAPTURAR_IMAGEM) {
					Toast.makeText(this, "Imagem salva com sucesso!", Toast.LENGTH_LONG).show();
				}
				else if (codigoRequisicao == CODIGO_REQUISICAO_CAPTURAR_VIDEO) {
					Toast.makeText(this, "Video salvo com successo!", Toast.LENGTH_LONG).show();
				}
				break;
				
			case RESULT_CANCELED:
				// Usuario cancela a captura do video/foto
				Toast.makeText(this, "Operação cancelada.", Toast.LENGTH_LONG).show();
				break;
	
			default:
				// Erros genericos
				Toast.makeText(this, "Falha na Operação.", Toast.LENGTH_LONG).show();
				break;
		}
	}
}