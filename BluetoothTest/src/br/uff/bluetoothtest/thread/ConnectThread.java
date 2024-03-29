package br.uff.bluetoothtest.thread;

import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class ConnectThread extends Thread {
	
	private final BluetoothAdapter bluetoothAdapter;
	private final BluetoothSocket mmSocket;
    private final BluetoothDevice bluetoothDevice;
 
    public ConnectThread(BluetoothAdapter bluetoothClientAdapter, BluetoothDevice bluetoothClientDevice, UUID uuid) {
        
    	// Use a temporary object that is later assigned to mmSocket, because mmSocket is final.
        BluetoothSocket tmp = null;
        bluetoothDevice = bluetoothClientDevice;
        
        bluetoothAdapter = bluetoothClientAdapter;
 
        // Get a BluetoothSocket to connect with the given BluetoothDevice
        try {
            // MY_UUID is the app's UUID string, also used by the server code
            tmp = bluetoothClientDevice.createRfcommSocketToServiceRecord(uuid);
        } 
        catch (IOException e) { 
        	
        }
        
        mmSocket = tmp;
    }
 
    public void run() {
    	
        // Cancel discovery because it will slow down the connection
        bluetoothAdapter.cancelDiscovery();
 
        try {
            // Connect the device through the socket. This will block
            // until it succeeds or throws an exception
            mmSocket.connect();
        } catch (IOException connectException) {
            // Unable to connect; close the socket and get out
            try {
                mmSocket.close();
            } catch (IOException closeException) { }
            return;
        }
 
        // Do work to manage the connection (in a separate thread)
//        manageConnectedSocket(mmSocket);
//        
//        	>>> YOUR CODE GOES HERE <<<
//        
        
    }
 
    /** 
     * Will cancel an in-progress connection, and close the socket 
     */
    public void cancel() {
        
    	try {
            mmSocket.close();
        } 
    	catch (IOException e) { 
    		
    	}
    }
}