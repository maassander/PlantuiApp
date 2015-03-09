package ap.plantuiapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    Button Timerbtn;
    Button Bluetoothbtn;
    ImageButton HelpButton;
    Button bluebtn;
    Button redbtn;
    Button greenbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timerbtn = (Button) findViewById(R.id.ButtonGoToTimer);
        Timerbtn.setOnClickListener(this);
        bluebtn = (Button)findViewById(R.id.Btn_Blue);
        bluebtn.setOnClickListener(this);
        Bluetoothbtn = (Button) findViewById(R.id.BtnGoToBluetooth);
        Bluetoothbtn.setOnClickListener(this);
        redbtn = (Button)findViewById(R.id.Btn_Red);
        redbtn.setOnClickListener(this);
        greenbtn = (Button)findViewById(R.id.Btn_Green);
        greenbtn.setOnClickListener(this);
        HelpButton = (ImageButton) findViewById(R.id.Imghelpbutton);
        HelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, HelpButton);
                popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());
                popup.show();

            }


        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,menu );
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

    public void goToTimerActivity(View view) {
            Intent intent = new Intent(MainActivity.this, timermenu.class);
            startActivity(intent);
    }

    public void goToBluetoothActivity(View view) {
            Intent intent = new Intent(MainActivity.this, bluetooth.class);
            startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ButtonGoToTimer:
                startActivity(new Intent("ap.plantuiapp.Timer"));
                break;
            case R.id.BtnGoToBluetooth:
                startActivity(new Intent("ap.plantuiapp.grid"));
                break;
            case R.id.Btn_Blue:
                startActivity(new Intent("ap.plantuiapp.blueled"));
                break;
            case R.id.Btn_Red:
                startActivity(new Intent("ap.plantuiapp.redled"));
                break;
            case R.id.Btn_Green:
                startActivity(new Intent("ap.plantuiapp.greenled"));

        }
    }
}

