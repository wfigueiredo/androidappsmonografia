package br.uff.bluetoothtest.receiver;

import br.uff.bluetoothtest.util.BluetoothUtils;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

public class FoundDeviceReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		String action = intent.getAction();
        
        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
        	
        	// When discovery finds a device get the BluetoothDevice object from the Intent.
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            ArrayAdapter<String> devices = intent.getParcelableExtra(BluetoothUtils.PARAMETER_BLUETOOTH_DEVICES_ADAPTER);
            
            // Add the name and address to an array adapter to show in a ListView
            devices.add(device.getName() + "\n" + device.getAddress());
        }
	}
	
	
}
