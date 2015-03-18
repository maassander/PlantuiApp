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
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    Button Timerbtn;
    Button Bluetoothbtn;
    ImageButton HelpButton;
    Button bluebtn;
    Button redbtn;
    Button greenbtn;
    SeekBar SeekbarRed;
    SeekBar SeekbarGreen;
    SeekBar SeekbarBlue;
    TextView RedValue;
    TextView BlueValue;
    TextView GreenValue;

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
        SeekbarRed.setMax(100);
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
        SeekbarBlue.setMax(100);
        SeekbarBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                BlueValue.setText(progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekbarGreen.setMax(100);

        SeekbarGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                GreenValue.setText(progress+"");
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

        }
    }
}

