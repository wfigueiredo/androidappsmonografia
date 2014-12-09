package br.uff.bluetoothtest.activity;

import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.uff.bluetoothtest.R;
import br.uff.bluetoothtest.util.BluetoothUtils;

public class DevicesListActivity extends Activity {
    
	private static final int BLUETOOTH_ENABLE_REQUEST_CODE = 100;
	
	private ArrayAdapter<String> bluetoothDevices;
	private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        bluetoothDevices = new ArrayAdapter<String>(DevicesListActivity.this, android.R.layout.simple_list_item_1);
        
        ListView listViewDevices = (ListView) findViewById(R.id.btDeviceList);
        listViewDevices.setAdapter(bluetoothDevices);
        
        checkDeviceBluetoothFeature();
        
        enableDiscoverability();
        
//        showPairedDevices();
    }

	private void checkDeviceBluetoothFeature() {
		
		if (bluetoothAdapter == null) {

			// Device does not support Bluetooth
			Toast.makeText(this, "Sorry. Bluetooth is not supported on this device.", Toast.LENGTH_LONG).show();
		}
		else {
			
			if (!bluetoothAdapter.isEnabled()) {
			    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			    startActivityForResult(enableBtIntent, BLUETOOTH_ENABLE_REQUEST_CODE);
			}
		}
	}
	
	private void showPairedDevices() {
		
		Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
		
		if (!pairedDevices.isEmpty()) {
		    
		    for (BluetoothDevice device : pairedDevices) {
		        
		    	// Add the name and address to an array adapter to show in a ListView
		        bluetoothDevices.add(device.getName() + "\n" + device.getAddress());
		    }
		}
	}
	
	private void enableDiscoverability() {
		
		Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, BluetoothUtils.MAX_DEVICE_DISCOVERABLE_TIME);
		
		startActivity(discoverableIntent);
	}
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    
    	switch (resultCode) {
		
			case RESULT_OK:
				Toast.makeText(this, "Bluetooth is now enabled!", Toast.LENGTH_LONG).show();
				break;
				
			case RESULT_CANCELED:
				Toast.makeText(this, "Operation cancelled.", Toast.LENGTH_LONG).show();
				break;
	
			default:
				Toast.makeText(this, "Operation failed.", Toast.LENGTH_LONG).show();
				break;
		}
    }
}