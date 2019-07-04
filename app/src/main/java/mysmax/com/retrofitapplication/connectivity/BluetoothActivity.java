package mysmax.com.retrofitapplication.connectivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import mysmax.com.retrofitapplication.R;

public class BluetoothActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private boolean isBluetoothPresent;
    private final String MSG_BLUETOOTH_NOT_PRESENT = "Your Device Doesn't Have Bluetooth";
    public static final int REQUEST_ENABLE_BT = 12321;
    private StringBuilder deviceItem;

    private AcceptThread insecureAcceptThread;
    private ConnectThread connectThread;
    private ConnectedThread connectedThread;
    private int state;
    // Constants that indicate the current connection state
    public static final int STATE_NONE = 0;
    public static final int STATE_LISTEN = 1; // listening connection
    public static final int STATE_CONNECTING = 2; // initiate outgoing
    // connection
    public static final int STATE_CONNECTED = 3; // connected to remote device
    private static final UUID MY_UUID_INSECURE = UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");
    private static final String NAME_INSECURE = "BluetoothChatInsecure";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        // Bluetooth
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        isBluetoothPresent = bluetoothAdapter != null;

        findViewById(R.id.connect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(!isBluetoothPresent)
             {
                 showToast(MSG_BLUETOOTH_NOT_PRESENT);
             } else
             {
                 if (!bluetoothAdapter.isEnabled()) {
                     // manual
                     /*
                     Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                     startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                     */
                     // automatic
                     Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                     discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
                     startActivity(discoverableIntent);

                 } else
                 {
                     onDiscover();
                 }
             }
            }
        });

        findViewById(R.id.disconnect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isBluetoothPresent)
                {
                    showToast(MSG_BLUETOOTH_NOT_PRESENT);
                } else
                {
                    onDisconnect();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Register for broadcasts when a device is discovered.
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onStop() {
        unregisterReceiver(receiver);
        bluetoothAdapter.cancelDiscovery();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void onDiscover()
    {
        deviceItem=null; // default set
        deviceItem = new StringBuilder(); // initialize
        onScan();
        showDeviceName();
    }

    private void onDiscoverPaired()
    {
        deviceItem=null; // default set
        deviceItem = new StringBuilder(); // initialize
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                deviceItem.append(deviceName+"\n");
                Log.e("Bluetooth Data","Device Name : "+ deviceName +" Address : " + deviceHardwareAddress);
            }
        }
        showDeviceName();
    }

    private void onScan()
    {
        bluetoothAdapter.startDiscovery();
    }

    private void onDisconnect()
    {
        bluetoothAdapter.cancelDiscovery();
    }

    private void showDeviceName()
    {
        ((TextView) findViewById(R.id.tvDeviceNames)).setText(""+deviceItem);
    }

    private void showToast(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                deviceItem.append(deviceName+"\n");
                Log.e("Bluetooth On Scan","Device Name : "+ deviceName +" Address : " + deviceHardwareAddress);
                showDeviceName();
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ENABLE_BT)
        {
            if (resultCode == 0)
            {
                // If the resultCode is 0, the user selected "No" when prompt to
                // allow the app to enable bluetooth.
                // You may want to display a dialog explaining what would happen if
                // the user doesn't enable bluetooth.
                Toast.makeText(this, "The user decided to deny bluetooth access", Toast.LENGTH_LONG).show();
            }
            else
            {
                //Log.i(LOG_TAG, "User allowed bluetooth access!");
                showToast("User allowed bluetooth access!");
            }

        }
    }

    private void connectDevice(String address) {

        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);
        connectThread = new ConnectThread(device, true);

    }

    private void connectionFailed() {
        /*
        Message msg = handler.obtainMessage(TripDetailActivity.MESSAGE_TOAST);
        Bundle bundle = new Bundle();
        bundle.putString(TripDetailActivity.TOAST, "Unable to connect to the Vehicle's Bluetooth. Please ensure you have boarded the cab. You may alternatively  read out your OTP to the driver.\n");
        msg.setData(bundle);
        handler.sendMessage(msg);
        */
        // Start the service over to restart listening mode
        //ChatService.this.start();
    }

    // runs while attempting to make an outgoing connection
    private class ConnectThread extends Thread {
        private final BluetoothSocket socket;
        private final BluetoothDevice device;
        private String socketType;

        public ConnectThread(BluetoothDevice device, boolean secure) {
            this.device = device;
            BluetoothSocket tmp = null;
            socketType = secure ? "Secure" : "Insecure";

            try {
                if (secure)
                {
                }
                else
                {
                    tmp = device.createInsecureRfcommSocketToServiceRecord(MY_UUID_INSECURE);
                }
            } catch (IOException e) {
            }
            socket = tmp;
        }

        public void run() {
            setName("ConnectThread" + socketType);

            // Always cancel discovery because it will slow down a connection
            bluetoothAdapter.cancelDiscovery();

            // Make a connection to the BluetoothSocket
            try {
                socket.connect();
            } catch (IOException e) {
                try {
                    socket.close();
                } catch (IOException e2) {
                }
                connectionFailed();
                return;
            }

            // Reset the ConnectThread because we're done
            synchronized (this) {
                connectThread = null;
            }

            // Start the connected thread
            connected(socket, device, socketType);
        }

        public void cancel() {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    // manage Bluetooth connection
    public synchronized void connected(BluetoothSocket socket, BluetoothDevice device, final String socketType) {
        // Cancel the thread
        if (connectThread != null) {
            connectThread.cancel();
            connectThread = null;
        }

        // Cancel running thread
        if (connectedThread != null) {
            connectedThread.cancel();
            connectedThread = null;
        }

        if (insecureAcceptThread != null) {
            insecureAcceptThread.cancel();
            insecureAcceptThread = null;
        }

        // Start the thread to manage the connection and perform transmissions
        connectedThread = new ConnectedThread(socket, socketType);
        connectedThread.start();

        // Send the name of the connected device back to the UI Activity
        /*
        Message msg = handler.obtainMessage(TripDetailActivity.MESSAGE_DEVICE_NAME);
        Bundle bundle = new Bundle();
        bundle.putString(TripDetailActivity.DEVICE_NAME, device.getName());
        msg.setData(bundle);
        handler.sendMessage(msg);
        */
        //setState(STATE_CONNECTED);
    }

    // runs while listening for incoming connections
    private class AcceptThread extends Thread {
        private final BluetoothServerSocket serverSocket;
        private String socketType;

        public AcceptThread(boolean secure) {
            BluetoothServerSocket tmp = null;
            socketType = secure ? "Secure" : "Insecure";

            try {
                if (secure) {
					/*tmp = bluetoothAdapter.listenUsingRfcommWithServiceRecord(
							NAME_SECURE, MY_UUID_SECURE);
				*/} else {
                    tmp = bluetoothAdapter
                            .listenUsingInsecureRfcommWithServiceRecord(
                                    NAME_INSECURE, MY_UUID_INSECURE);
                }
            } catch (IOException e) {
            }
            serverSocket = tmp;
        }

        public void run() {
            setName("AcceptThread" + socketType);

            BluetoothSocket socket = null;

            while (state != STATE_CONNECTED) {
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    break;
                }

                // If a connection was accepted
                if (socket != null) {
                    synchronized (this) {
                        switch (state) {
                            case STATE_LISTEN:
                            case STATE_CONNECTING:
                                // start the connected thread.
                                connected(socket, socket.getRemoteDevice(),
                                        socketType);
                                break;
                            case STATE_NONE:
                            case STATE_CONNECTED:
                                // Either not ready or already connected. Terminate
                                // new socket.
                                try {
                                    socket.close();
                                } catch (IOException e) {
                                }
                                break;
                        }
                    }
                }
            }
        }

        public void cancel() {
            try {
                serverSocket.close();
            } catch (IOException e) {
            }
        }
    }

    // runs during a connection with a remote device
    private class ConnectedThread extends Thread {
        private final BluetoothSocket bluetoothSocket;
        private final InputStream inputStream;
        private final OutputStream outputStream;

        public ConnectedThread(BluetoothSocket socket, String socketType) {
            this.bluetoothSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
            }

            inputStream = tmpIn;
            outputStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;

            // Keep listening to the InputStream
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = inputStream.read(buffer);

                    // Send the obtained bytes to the UI Activity
                    //	handler.obtainMessage(MainActivity.MESSAGE_READ, bytes, -1,
                    //		buffer).sendToTarget();
                } catch (IOException e) {
                    connectionLost();
                    // Start the service over to restart listening mode
                    //ChatService.this.start();
                    break;
                }
            }
        }

        // write to OutputStream
        public void write(byte[] buffer) {
            try {
                outputStream.write(buffer);
                //	handler.obtainMessage(MainActivity.MESSAGE_WRITE, -1, -1,
                //			buffer).sendToTarget();
            } catch (IOException e) {
            }
        }

        public void cancel() {
            try {
                bluetoothSocket.close();
            } catch (IOException e) {
            }
        }
    }

    private void connectionLost() {
		/*Message msg = handler.obtainMessage(TripDetailActivity.MESSAGE_TOAST);
		Bundle bundle = new Bundle();
		bundle.putString(TripDetailActivity.TOAST, "Device connection was lost");
		msg.setData(bundle);
		handler.sendMessage(msg);*/

        // Start the service over to restart listening mode
        //ChatService.this.start();
    }
}
