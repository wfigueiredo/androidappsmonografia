package br.uff.alarmtest;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class DefinicaoAlarmeActivity extends Activity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_set);
        
        Button submitAlarme = (Button) findViewById(R.id.submitAlarme);
        submitAlarme.setOnClickListener(new OnClickListener() {
			
        	@Override
			public void onClick(View arg0) {
				
				Spinner temposAlarme = (Spinner) findViewById(R.id.temposAlarme);
				String minutos = (String) temposAlarme.getSelectedItem();
				
				Spinner intervalosAlarme = (Spinner) findViewById(R.id.intervalosAlarme);
				String intervalo = (String) intervalosAlarme.getSelectedItem();
				
				Toast.makeText(DefinicaoAlarmeActivity.this, 
								"Este alarme irá disparar em " + minutos + " minutos.", 
								Toast.LENGTH_SHORT).show();
				
				agendarAlarme(Integer.parseInt(minutos), Integer.parseInt(intervalo));
				finish();
			}
		});
    }
    
    private void agendarAlarme(int minutesFromNow, int intervaloRepeticao){
    	
    	Intent intent = new Intent("AGENDAR_ALARME");
    	
    	PendingIntent pendingIntent = 
    			PendingIntent.getBroadcast(DefinicaoAlarmeActivity.this, 0, intent, 0);
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.MINUTE, minutesFromNow);
    	
    	AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
    	alarme.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 
    					   intervaloRepeticao * 1000, pendingIntent);
    }
}