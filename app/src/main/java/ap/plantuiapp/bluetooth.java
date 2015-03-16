package ap.plantuiapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class bluetooth extends ActionBarActivity
{
    private Switch OnOff;
    private Button List, Scan, Connect;
    private ArrayAdapter adapter;
    private BluetoothAdapter BA;
    private BroadcastReceiver BR = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // Whenever a remote Bluetooth device is found
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
                adapter.add(bluetoothDevice.getName() + "\n"
                        + bluetoothDevice.getAddress());
            }
        }
    };
    private BluetoothSocket BS;
    private Set<BluetoothDevice> pairedDevices;
    private BluetoothDevice BTdev;
    private final static UUID MY_UUID = UUID
            .fromString("00001101-0000-1000-8000-00805F9B34FB");
    private ListView lv;
    private TextView switchStatus;
    private static final int ENABLE_BT_REQUEST_CODE = 1;
    private static final int DISCOVERABLE_BT_REQUEST_CODE = 2;
    private static final int DISCOVERABLE_DURATION = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        List = (Button)findViewById(R.id.btnListDevices);
        Scan = (Button)findViewById(R.id.btnScan);
        Connect = (Button)findViewById(R.id.btnConnect);
        lv = (ListView)findViewById(R.id.listView);
        switchStatus = (TextView) findViewById(R.id.StatusBox);
        OnOff = (Switch)findViewById(R.id.switchBluetooth);

        OnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    switchStatus.setText("Bluetooth is currently ON");
                    if (!BA.isEnabled())
                    {
                        Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(turnOn, 0);
                    }
                    else
                    {
                        switchStatus.setText("Bluetooth is ALREADY ON");
                    }
                }
                else
                {
                    BA.disable();
                    switchStatus.setText("Bluetooth is currently OFF");
                }
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                /*Toast.makeText(getApplicationContext(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();*/
                // ListView Clicked item value
                String  itemValue = (String) lv.getItemAtPosition(position);
                String MAC = itemValue.substring(itemValue.length() - 17);
                BTdev = BA.getRemoteDevice(MAC);
            }
        });
        adapter = new ArrayAdapter
                (this,android.R.layout.simple_list_item_1);
        lv.setAdapter(adapter);
        BA = BluetoothAdapter.getDefaultAdapter();
    }

    public void list(View view)
    {
        pairedDevices = BA.getBondedDevices();

        ArrayList list = new ArrayList();
        for(BluetoothDevice bt : pairedDevices)
            list.add(bt.getName());

        Toast.makeText(getApplicationContext(), "Showing Paired Devices", Toast.LENGTH_SHORT).show();
        final ArrayAdapter adapter = new ArrayAdapter
                (this,android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
    }

    public void scan(View view)
    {
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        BA.startDiscovery();
        BR = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    adapter.add(device.getName() + "\n" + device.getAddress());
                    //BTdevMAC = device.getAddress();
                }
            }
        };
        lv.setAdapter(adapter);
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(BR, filter);
    }

    public void connect(View view)
    {
        Log.i("CONNECT", BTdev.toString());
        ConnectThread ct = new ConnectThread(BTdev);
        ct.start();
    }

    private class ConnectThread extends Thread
    {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device)
        {
            BluetoothSocket tmp = null;
            mmDevice = device;
            try
            {
                tmp = mmDevice.createRfcommSocketToServiceRecord(MY_UUID);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            mmSocket = tmp;
        }
        public void run()
        {
            BA.cancelDiscovery();
            try
            {
                Log.i("RUN", mmDevice.toString());
                Log.i("RUN", mmSocket.toString());
                mmSocket.connect();
            }
            catch (IOException connectException)
            {
                try
                {
                    Log.i("DISCONNECT", mmSocket.toString());
                    mmSocket.close();
                } catch (IOException closeException)
                {
                    closeException.printStackTrace();
                }
            }
            //manageConnectedSocket(mmSocket);
        }
        public void cancel()
        {
            try
            {
                mmSocket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /*private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);
                    // Send the obtained bytes to the UI activity
                    mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
                            .sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }

        // Call this from the main activity to send data to the remote device
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) { }
        }

        //Call this from the main activity to shutdown the connection
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }*/

    /*public void visible(View view)
    {
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bluetooth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
