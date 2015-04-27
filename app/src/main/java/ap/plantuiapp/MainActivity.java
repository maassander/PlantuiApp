package ap.plantuiapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    Button Timerbtn, Bluetoothbtn, bluebtn, redbtn, greenbtn, LoadValues;

    SeekBar SeekbarRed ,SeekbarGreen ,SeekbarBlue;

    TextView RedValue, BlueValue, GreenValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timerbtn = (Button) findViewById(R.id.ButtonGoToTimerMenu);
        Timerbtn.setOnClickListener(this);
        bluebtn = (Button)findViewById(R.id.Btn_Blue);
        bluebtn.setOnClickListener(this);
        Bluetoothbtn = (Button) findViewById(R.id.BtnGoToBluetooth);
        Bluetoothbtn.setOnClickListener(this);
        redbtn = (Button)findViewById(R.id.Btn_Red);
        redbtn.setOnClickListener(this);
        greenbtn = (Button)findViewById(R.id.Btn_Green);
        greenbtn.setOnClickListener(this);
        SeekbarRed = (SeekBar)findViewById(R.id.SeekbarRed);
        SeekbarGreen = (SeekBar)findViewById(R.id.SeekbarGreen);
        SeekbarBlue = (SeekBar)findViewById(R.id.SeekbarBlue);
        SeekbarRed.setMax(255);
        LoadValues = (Button)findViewById(R.id.ButtonLoadValues);
        LoadValues.setOnClickListener(this);
        SeekbarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                RedValue.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekbarBlue.setMax(255);

        SeekbarBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                BlueValue.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekbarGreen.setMax(255);

        SeekbarGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                GreenValue.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        RedValue = (TextView)findViewById(R.id.TextViewSeekBarRed);
        BlueValue =(TextView)findViewById(R.id.TextViewSeekBarBlue);
        GreenValue =(TextView)findViewById(R.id.TextViewSeekBarGreen);




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
            final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setMessage("Need help at main screen?\n" +
                    "\n" +
                    "What can you see from main screen?\n" +
                    "\t- Visual values of lights\n" +
                    "\n" +
                    "What can you set up from main screen?\n" +
                    "\t- Go to bluetooth and connect to device\n" +
                    "\t- Go to clock screen and set sleep timings\n" +
                    "\t- Go to led screen to adjust lights\n" +
                    "\n" +
                    "Still having problems?\n" +
                    "Go to plantui.com/support/application/main\n" +
                    "\n" +
                    "Or send feedback or support request directly to us from\n" +
                    "plantui.com/support");
            alert.setTitle("Help");
            alert.setIcon(R.drawable.help);
            alert.create();
            alert.show();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ButtonGoToTimerMenu:
                startActivity(new Intent("ap.plantuiapp.timermenu"));
                break;
            case R.id.BtnGoToBluetooth:
                startActivity(new Intent("ap.plantuiapp.bluetooth"));
                break;
            case R.id.Btn_Blue:
                startActivity(new Intent("ap.plantuiapp.blueled"));
                break;
            case R.id.Btn_Red:
                startActivity(new Intent("ap.plantuiapp.redled"));
                break;
            case R.id.Btn_Green:
                startActivity(new Intent("ap.plantuiapp.greenled"));
            case R.id.ButtonLoadValues:
                LoadValues();
                break;
        }
    }
    public void LoadValues()
    {

        try {
              FileInputStream  fileRedLed =  openFileInput("ValueRed.txt");
              FileInputStream fisGreenLed = openFileInput("ValueGreen.txt");
            FileInputStream fisBlueLed = openFileInput("ValueBlue.txt");
            int read;
            //a while because otherwise its only the first byte he read and then stops.
            /*
            *
            *what u see when u open a file?
            *255
            * what it actually contained!
            *32 40 40 for example
             */

            while ((read = fileRedLed.read()) != -1)
            {
                SeekbarRed.setProgress((char)read);
            }
            while((read = fisGreenLed.read())!=-1)
            {
                SeekbarGreen.setProgress((char)read);
            }
            while((read = fisBlueLed.read())!=-1)
            {
                SeekbarBlue.setProgress((char)read);
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

