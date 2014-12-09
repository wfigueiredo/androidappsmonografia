package br.uff.camerateste.camera;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class CameraUtils {
	
	public static final int TIPO_MIDIA_IMAGEM = 1;
	public static final int TIPO_MIDIA_VIDEO = 2;
	
	private static final String EXTENSAO_IMAGEM = ".jpg";
	private static final String EXTENSAO_VIDEO = ".mp4";
	
	private static final String NOME_APLICACAO = "CameraTeste";

	public static Uri obterUriDeArquivoGerado(int tipo){
		
		return Uri.fromFile(obterArquivoDeSaida(tipo));
	}

	/** 
	 * Cria um arquivo de saída para uma imagem ou um video. 
	 */
	private static File obterArquivoDeSaida(int tipo){
		
		// Deve ser assegurado o fato de o SD Card estar devidamente montado no dispositivo.
		//
		// Alem disso, o local (Galeria) é o mais indicado para imagens e videos criados serem
		// compartilhados entre aplicativos. Assim, quando persistidos, eles permanecem no 
		// dispositivo, mesmo se o aplicativo original que os criou for removido.
	    File diretorioArmazenamento = new 
	    		File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), NOME_APLICACAO);

	    // Criação do diretório de armazenamento, caso o mesmo não exista.
	    if (!diretorioArmazenamento.exists()){
	 
	    	if (!diretorioArmazenamento.mkdirs()){
	    		Log.d(NOME_APLICACAO, "Falha na criação do diretório.");
	            return null;
	        }
	    }

	    // Criação do nome do arquivo.
	    File arquivoDeMidia;
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    
	    if (tipo == TIPO_MIDIA_IMAGEM){
	    	arquivoDeMidia = new File(diretorioArmazenamento.getPath() + File.separator + "IMG_"+ timeStamp + EXTENSAO_IMAGEM);
	    } 
	    else if (tipo == TIPO_MIDIA_VIDEO) {
	    	// ATUALMENTE NÃO FUNCIONA, devido a uma inconsistencia de caminhos gerados entre a aplicacao 
	    	// e a Aplicacao Nativa da Camera do Android.
	        arquivoDeMidia = new File(diretorioArmazenamento.getPath() + File.separator + "VID_"+ timeStamp + EXTENSAO_VIDEO);
	    } 
	    else {
	    	return null;
	    }

	    return arquivoDeMidia;
	}
}