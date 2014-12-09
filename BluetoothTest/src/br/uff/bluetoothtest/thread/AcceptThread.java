package br.uff.bluetoothtest.thread;

import java.io.IOException;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import br.uff.bluetoothtest.util.BluetoothUtils;

public class AcceptThread extends Thread {

	private final BluetoothServerSocket serverSocket;
	 
    public AcceptThread(BluetoothAdapter bluetoothAdapter, UUID uuid) {
        
    	// Use a temporary object that is later assigned to serverSocket, because mmServerSocket is final.
        BluetoothServerSocket tempServerSocket = null;
        
        try {
        	// UUID is the app's UUID string, also used by the client code.
            tempServerSocket = bluetoothAdapter.listenUsingRfcommWithServiceRecord(BluetoothUtils.APP_NAME_SDP, uuid);
        } 
        catch (IOException e) { }
        
        serverSocket = tempServerSocket;
    }
 
    public void run() {
    	
        BluetoothSocket socket = null;
        
        // Keep listening until exception occurs or a socket is returned.
        while (true) {
            
        	try {
                socket = serverSocket.accept();

                // If a connection was accepted
                if (socket != null) {
                	
                	// Do work to manage the connection (in a separate thread)
//                manageConnectedSocket(socket);
//                	
//                >>> YOUR CODE GOES HERE! <<<
                	
                	
                	serverSocket.close();
                }	
            } 
        	catch (IOException e) {
                break;
            }
        }
    }
 
    /** Will cancel the listening socket, and cause the thread to finish */
    public void cancel() {
        
    	try {
            serverSocket.close();
        } 
    	catch (IOException e) { }
    }
}
