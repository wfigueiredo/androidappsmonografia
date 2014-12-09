package br.uff.androidappbasics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class FundamentosAppAndroidActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout linearLayout = new LinearLayout(FundamentosAppAndroidActivity.this);
        
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        linearLayout.setPadding(10, 10, 10, 10);
        
        // TextView
        TextView textView = new TextView(FundamentosAppAndroidActivity.this);
        textView.setText("Android");
        
        // EditText
        EditText editText = new EditText(FundamentosAppAndroidActivity.this);
        editText.setHint(R.string.email);
        
        // Button
        Button button = new Button(FundamentosAppAndroidActivity.this);
        button.setText(getResources().getString(R.string.ok));
        
        // CheckBox
        CheckBox checkBox = new CheckBox(FundamentosAppAndroidActivity.this);
        checkBox.setText(getResources().getString(R.string.anexarArquivo));
        
        // ToggleButton
        ToggleButton toggleButton = new ToggleButton(FundamentosAppAndroidActivity.this);
        toggleButton.setText(getResources().getString(R.string.wifi_habilitado));
        toggleButton.setTextOn(getResources().getString(R.string.wifi_habilitado));
        toggleButton.setTextOff(getResources().getString(R.string.wifi_desabilitado));
        
        // RadioButton
        RadioButton radioButtonAndroid = new RadioButton(FundamentosAppAndroidActivity.this);
        radioButtonAndroid.setText(getResources().getString(R.string.android));
        
        RadioButton radioButtonIos = new RadioButton(FundamentosAppAndroidActivity.this);
        radioButtonIos.setText(getResources().getString(R.string.ios));
        
        // Spinner
        ArrayAdapter<CharSequence> spinnerAdapter = 
        			ArrayAdapter.createFromResource
        							(FundamentosAppAndroidActivity.this, 
        							 R.array.sistemasMobile,
        							 android.R.layout.simple_spinner_dropdown_item);
        
        final Spinner spinner = new Spinner(FundamentosAppAndroidActivity.this);
        spinner.setAdapter(spinnerAdapter);
        
        // Dialog
        final AlertDialog.Builder adb = 
        			new AlertDialog.Builder(FundamentosAppAndroidActivity.this);
        adb.setTitle("AlertDialog");
        adb.setMessage("Este é um exemplo de AlertDialog.");
        adb.setPositiveButton("OK", new OnClickListener() {
        	
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
        adb.setIcon(R.drawable.android_logo);
        
        AlertDialog alertDialog = adb.create();
        alertDialog.show();
        
        // Toast
        Toast.makeText(FundamentosAppAndroidActivity.this, 
        			   "Esta é uma mensagem do tipo TOAST.", 
        			   Toast.LENGTH_SHORT).show();
        
        // Adding all child views
        linearLayout.addView(textView);
        linearLayout.addView(editText);
        linearLayout.addView(button);
        linearLayout.addView(checkBox);
        linearLayout.addView(toggleButton);
        linearLayout.addView(radioButtonAndroid);
        linearLayout.addView(radioButtonIos);
        linearLayout.addView(spinner);
        
        setContentView(R.layout.main);			// Draw views via XML file.
//        setContentView(linearLayout);			// Draw views via Java code.
    }
}