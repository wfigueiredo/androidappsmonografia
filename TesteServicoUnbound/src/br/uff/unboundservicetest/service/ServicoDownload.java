package br.uff.unboundservicetest.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import br.uff.unboundservicetest.R;
import br.uff.unboundservicetest.activity.ExibicaoImagemActivity;

public class ServicoDownload extends Service {

	private static final String IMAGE_URL = "http://mobilezonegt.com/graphics1/Android-Wallpapers-960x854/android_black_wall.jpg";
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		downloadImage(IMAGE_URL);
		return START_NOT_STICKY;
	}

	private void downloadImage(final String imageUrl) 
	{
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					
					URL url = new URL(imageUrl);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					
					connection.setRequestProperty("Request-Method", "GET");
					connection.setDoInput(true);
					connection.setDoOutput(false);
					connection.connect();
					
					InputStream inputStream = connection.getInputStream();
					
					byte[] fileBytes = readBytes(inputStream);
					
					if (fileBytes.length > 0) {
						createNotification(fileBytes);
					}
					
					connection.disconnect();
				} 
				catch (MalformedURLException e) {
					e.printStackTrace();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		thread.start();
		
		stopSelf();		
	}
	
	private byte[] readBytes(InputStream inputStream) {
		
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		try 
		{
			int length;
			byte[] buffer = new byte[1024];
			
			while ((length = inputStream.read(buffer)) > 0) 
			{
				bos.write(buffer, 0, length);
			}
			
			bytes = bos.toByteArray();
			return bytes;
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally {
			try 
			{
				bos.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return bytes;
	}
	
	private void createNotification(byte[] fileBytes) {
		
		// Notification text on Status Bar
		String tickerText = "Download Completado";		
		
		// Notification details
		CharSequence title = "Download Completado";
		CharSequence message = "Seu arquivo está pronto para ser visualizado.";
		
		iniciarActivityNotificacao(tickerText, title, message, fileBytes);
		
	}
	
	private void iniciarActivityNotificacao(String textoTicker, CharSequence titulo, CharSequence mensagem, byte[] fileBytes) 
	{
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.android_mini_icon, textoTicker, System.currentTimeMillis());

		Intent intent = new Intent(ServicoDownload.this, ExibicaoImagemActivity.class);
		intent.putExtra("fileBytes", fileBytes);   
		
		PendingIntent pendingIntent = PendingIntent.getActivity(ServicoDownload.this, 0, intent, 0);

		notification.setLatestEventInfo(ServicoDownload.this, titulo, mensagem, pendingIntent);

		notification.vibrate = new long[] {300, 400, 500, 700};
		notification.defaults |= Notification.DEFAULT_SOUND;
		notification.defaults |= Notification.DEFAULT_LIGHTS;
		
		notificationManager.notify(R.string.app_name, notification);
	}
}